package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread1 : 사용자 A의 주문
        statefulService1.order("userA", 10000);
        // Thread2 : 사용자 B의 주문 -> 스레드1이 침범당함
        statefulService2.order("userB", 20000);
        // 같은 인스턴스를 사용하기 때문에 price 상태가 바뀐 것!

//        int price = statefulService1.getPrice();

        int userAPrice = statefulService1.order("userA", 10000);
        int userbPrice = statefulService1.order("userb", 20000);



        System.out.println("price : " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
