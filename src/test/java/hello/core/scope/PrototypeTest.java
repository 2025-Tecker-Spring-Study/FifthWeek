package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("Find PrototypeBean1");
        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
        System.out.println("Find PrototypeBean2");
        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);

        System.out.println("ProtoTypeBean1 : " + prototypeBean1);
        System.out.println("ProtoTypeBean2 : " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        applicationContext.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("ProtoTypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("ProtoTypeBean.destroy");
        }
    }
}


// Test Result
// Find PrototypeBean1 -> 테스트 메서드에서 print구문
// ProtoTypeBean.init -> Bean1을 생성하면서 init메서드 내 print문 출력됨
// Find PrototypeBean2
// ProtoTypeBean.init
// ProtoTypeBean1 : hello.core.scope.PrototypeTest$PrototypeBean@6b5f8707 -> 생성 이후 객체 주입까지 이루어진 상태
// ProtoTypeBean2 : hello.core.scope.PrototypeTest$PrototypeBean@772485dd

// 라이프사이클 종료 메서드가 있으나, 사용되지 않는다.
// 프로토타입 빈은 생성, DI, 초기화까지만 관리되므로 종료 메서드가 실행되지 않는다.

// 만약 별도로 닫는 작업이 필요하면
// prototypeBean1.close(); 로 수동으로 닫아줘야한다.

// Note. AnnotationConfigApplicationContext 클래스를 이용해 설정 파일을 주입하면
// @Configuration 없이 파일을 컴포넌트로 등록해줄 수 있다.

