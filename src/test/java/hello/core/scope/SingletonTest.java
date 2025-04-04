package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.Annotation;

public class SingletonTest {

    @Test
    public void singletonBeanTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = applicationContext.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = applicationContext.getBean(SingletonBean.class);

        System.out.println("singletonBean1 : " + singletonBean1);
        System.out.println("singletonBean2 : " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        applicationContext.close();
    }


    // default이지만, 싱글톤임을 알려주기 위해 명시함
    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }



}
