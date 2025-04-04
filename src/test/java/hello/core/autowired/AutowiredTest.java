package hello.core.autowired;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac =  new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 의존관계가 없으면 수정자 자체가 호출되지 않음
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("setNoBean1 : " + noBean1);
        }

        // 호출은 되지만, null이 빈에 할당됨
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("setNoBean2 : " + noBean2);
        }

        // 빈이 없으면 옵션을 Optional.empty로 할당
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("setNoBean3 : " + noBean3);
        }
    }

}
