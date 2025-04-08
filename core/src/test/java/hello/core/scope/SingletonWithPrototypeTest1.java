package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    // ✅ prototype 스코프의 동작 확인 테스트
    @Test
    void prototypeFind(){
        // 스프링 컨테이너 생성 시 PrototypeBean만 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 첫 번째 prototype 빈을 요청해서 사용
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount(); // count 값을 1 증가시킴
        assertThat(prototypeBean1.getCount()).isEqualTo(1); // 첫 번째 객체의 count는 1

        // 두 번째 prototype 빈을 요청해서 사용
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount(); // 두 번째 객체의 count 값을 1 증가
        assertThat(prototypeBean2.getCount()).isEqualTo(1); // 또다시 1인지 확인

        // ✅ 각각 요청할 때마다 새로운 인스턴스가 생성되므로 count 값이 초기화됨
    }

    // ✅ Singleton 빈(ClientBean) 내부에서 Prototype 빈을 사용할 때 문제를 보여주는 테스트
    @Test
    void singletonClientUsePrototype() {
        // 스프링 컨테이너에 ClientBean과 PrototypeBean 등록
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // ClientBean을 처음으로 꺼내서 logic() 실행
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic(); // prototypeBean의 count를 1 증가
        assertThat(count1).isEqualTo(1); // count는 1이어야 함

        // ClientBean을 다시 꺼냄 (하지만 싱글톤이기 때문에 같은 객체임)
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic(); // 이미 주입된 같은 prototypeBean 사용 → count = 2
        assertThat(count2).isEqualTo(1); // ❗ 예상과 다르게 1이 아니라 2가 됨

        // ✅ 문제점: prototypeBean이 매번 새로 생성되는 것이 아니라,
        // ClientBean 생성 시점에 한번 주입된 인스턴스를 계속 사용함
    }

    // ✅ ClientBean은 singleton 스코프 - 컨테이너에서 딱 하나만 생성되고 계속 재사용됨
    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
           prototypeBean.addCount();
           int count = prototypeBean.getCount();
           return count;
        }
    }

    // ✅ PrototypeBean 정의 - prototype 스코프
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0; // 내부 상태 필드

        public void addCount(){
            count++; // 호출될 때마다 count 증가
        }

        public int getCount(){
            return count; // 현재 count 값 반환
        }

        @PostConstruct
        public void init(){
            // 빈이 생성되고 의존관계 주입이 끝난 직후 실행됨
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            // ❗ prototype 스코프는 스프링이 관리하지 않기 때문에 이 메서드는 호출되지 않음
            // (직접 호출해줘야 함)
            System.out.println("PrototypeBean.destroy");
        }
    }
}
