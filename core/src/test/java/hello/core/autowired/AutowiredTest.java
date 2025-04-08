package hello.core.autowired;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //TestBean이 스프링 빈으로 등록됨
    }

    @Configuration
    static class TestConfig {

        @Bean
        public TestBean testBean() {
            return new TestBean();
        }
    }

    static class TestBean{

        @Autowired(required = false) //false지우면 오류 터짐 빈이 없기 때문에 멤버가 빈으로 등록되는게 아님
        public void setNoBean1(Member noBean1){ //Member는 스프링 컨테이너에서 관리되는게 없음
            System.out.println("noBean1 = " + noBean1);

        }
        @Autowired(required = false)
        public void setNoBean2(@Nullable Member noBean2){ //Member는 스프링 컨테이너에서 관리되는게 없음
            System.out.println("noBean1 = " + noBean2);

        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);

        }

    }
}
