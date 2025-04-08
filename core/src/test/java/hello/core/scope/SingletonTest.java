package hello.core.scope;

import jakarta.annotation.PostConstruct; // 빈이 생성되고 초기화 직후 실행되는 메서드에 붙이는 어노테이션
import jakarta.annotation.PreDestroy;   // 빈이 소멸되기 직전에 실행되는 메서드에 붙이는 어노테이션
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        // AnnotationConfigApplicationContext는 스프링 컨테이너 역할을 하는 클래스
        // 이 예제에서는 SingletonBean.class를 직접 등록하면서 시작
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        // 스프링 컨테이너에서 SingletonBean을 두 번 조회함
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        // 두 객체의 주소값을 출력
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        // 두 객체가 같은 인스턴스인지(싱글톤인지) 확인
        assertThat(singletonBean1).isSameAs(singletonBean2);

        // 스프링 컨테이너 종료 → @PreDestroy 메서드 호출됨
        ac.close();
    }

    // 싱글톤 스코프 지정 (사실 @Scope("singleton")는 생략해도 기본값이라 동작은 동일함)
    @Scope("singleton")
    static class SingletonBean{

        // 빈이 생성되고 의존관계 주입이 완료된 후 호출되는 초기화 메서드
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        // 스프링 컨테이너 종료 시 호출되는 종료 메서드
        @PreDestroy
        public void destory(){
            System.out.println("SingletonBean.destory");
        }
    }
}
