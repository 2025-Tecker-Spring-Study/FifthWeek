package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }


    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = applicationContext.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        ClientBean clientBean2 = applicationContext.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count2).isEqualTo(1); // 테스트 성공!

        // ClientBean은 싱글톤이다.
        // 다만, ClientBean 클래스를 보면, PrototypeBean은 생성과 동시에 빈 주입도 끝난다.
        // 즉, ClientBean이 Application 클래스에 의해 생성되어 빈으로 등록될때
        // 프로토타입 빈도 같이 생성되어 주입까지 끝낸다.
        // 따라서 clientBean1, 2는 같은 protoypeBean을 사용해야 한다!
        // 싱글톤은 아닌데 싱글톤처럼 사용되고 있어서 문제?


    }



    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }


    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean;

        // 1. ObjectProvider 방법
//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;


        // 2. JSR-330 Provider
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;



//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();

            // Test Results
//            PrototypeBean.init hello.core.scope.SingletonWithPrototypeTest1$PrototypeBean@1ad926d3
//            PrototypeBean.init hello.core.scope.SingletonWithPrototypeTest1$PrototypeBean@39ce27f2
            // 각각 다른 프로토타입 빈이 생성됨을 확인

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

}
