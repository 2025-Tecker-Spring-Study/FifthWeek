package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    // 두 객체가 똑같다!
    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = memberService.getMemberRepository();

    System.out.println(memberRepository1);
    System.out.println(memberRepository2);
//  hello.core.member.MemoryMemberRepository@62d0ac62
//  hello.core.member.MemoryMemberRepository@62d0ac62


    System.out.println("memberService -> memberRepository" + memberService.getMemberRepository());
    System.out.println("orderService -> memberRepository" + orderService.getMemberRepository());
    System.out.println("memberRepository = " + memberRepository);

//  memberService -> memberRepositoryhello.core.member.MemoryMemberRepository@62d0ac62
//  orderService -> memberRepositoryhello.core.member.MemoryMemberRepository@62d0ac62
//  memberRepository = hello.core.member.MemoryMemberRepository@62d0ac62

    Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }


    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean : " + bean.getClass());
//      bean : class hello.core.AppConfig$$SpringCGLIB$$0
        // 뒤에 붙은 $$SpringCGLIB$$0은 뭐지!?
        // 이는 내가 만든 클래스 코드가 아닌
        // 스프링이 CGLIB이라는 바이트코드 조작 라이브러리를 이용해
        // AppConfig 클래스를 상속받는 임의의 클래스를 만들어 그것을 빈으로 등록한 것!
        // 따라서 AppConfig@GCLIB이 빈으로 등록된다.
        
    }

}
