package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션의 환경 설정
// 인터페이스가 참조할 구현 객체를 여기서 모두 지정
// Dependency Injection을 활용!

//public class AppConfig {
//
//    // 멤버 서비스 객체를 생성하며, MemoryMemberRepository 객체를 사용하기 위해 주입
//    // 참조가 연결된 형태
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(
//                new MemoryMemberRepository(),
//                new FixDiscountPolicy());
//    }
//}



// 다만, 역할에 따른 구현이 잘 드러나야 하는데 위 코드에서는 역할이 보이지 않는다.
// 따라서 리팩토링을 해보자.

//public class AppConfig {
//
//    // 멤버 서비스 객체를 생성하며, MemoryMemberRepository 객체를 사용하기 위해 주입
//    // 참조가 연결된 형태
//
//    // 이때 메서드들을 보면, 어떤 객체를 주입하는지 잘 드러난다.
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    // 만일 나중에 DB가 정해진다면,
//    // return 문에서 MemoryMemberRepository객체만 바꾸어주면 된다!
//    private MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(
//                memberRepository(),
//                 discountPolicy());
//    }
//
//    public DiscountPolicy discountPolicy() {
//        // 기존의 고정 할인 구현체
////          return new FixDiscountPolicy();
//
//        // 정률 할인으로 바꾸어야 한다면,
//        // 반환되는 구현체만 바꾸면 된다!
//        return new RateDiscountPolicy();
//    }


// 이제 스프링을 이용하여 개발해보자.

// Note. 아래와 같이 @Bean을 이용하여 직접 등록하는 방식을 팩토리 메서드라고 한다.
// appConfig.xml은 직접 스프링 빈을 컨테이너에 그대로 등록해주는 방식이다.

@Configuration
public class AppConfig {

    // @Bean 코드에서 참조 관계
    // memberService -> MemberServiceImpl -> memberRepository -> MemoryMemberRepository
    // orderService -> MemoryMemberRepository
    // 이때 new 연산자를 통해 객체들이 생성과 동시에 주입됨

    // 즉, memberService -> MemoryMemberService()
    //    orderService -> MemoryMemberService
    // Q. 생성을 여러번 하니 싱글톤이 깨지는게 아닌가?
    // A. 확인해보기 위해 테스트 코드를 짜보자.

    // 스프링 컨테이너에 등록하기 위해 @Bean
    @Bean
    public MemberService memberService() {
        // 호출 시 참조관계를 보기 위해 print를 찍어봤다.
        // 이때 참조 관계가 체인처럼 얽혀있으므로, memberRepository에서 정의한 문장은 3번 출력될 것이다.

        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}


// 어? 멤버레포지토리는 중복적으로 참조관계에 있어 그 이상 호출되어야 하는것 아닌가!
// -> 스프링이 싱글톤을 자동으로 보장해주는 덕분이다!