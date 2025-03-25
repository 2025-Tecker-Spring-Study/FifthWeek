package hello.core;

import hello.core.discount.Discount_policy;
import hello.core.discount.FixDiscount_policy;
import hello.core.discount.RateDiscount_policy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public static MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    private static Discount_policy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        // return new FixDiscount_policy();
        return new RateDiscount_policy();
    }
}
