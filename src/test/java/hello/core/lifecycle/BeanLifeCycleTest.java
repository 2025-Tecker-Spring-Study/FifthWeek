package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient ClientA = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // 초기화, 소멸 메소드를 지정
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
// Test Result
// 생성자 호출, url : null
// connect : null
// call : nullmessage : 초기화 연결 메시지


// NetWorkClient에 인터페이스 InitializingBean, DisposableBean을 적용한 결과
// 생성자 호출, url : null
// connect : http://hello-spring.dev
// call : http://hello-spring.devmessage : 초기화 연결 메시지


// NetworkClient.afterPropertiesSet
// connect : http://hello-spring.dev
// call : http://hello-spring.devmessage : 초기화 연결 메시지
//        15:51:32.589 [Test worker] DEBUG o.s.c.a.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@41813449, started on Sat Mar 29 15:51:32 KST 2025
// NetworkClient.destroy

// 컨테이너에서 빈이 내려가기 전에 등록한다.