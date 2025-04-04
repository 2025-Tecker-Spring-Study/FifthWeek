package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class AllBeanTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // 고정할인정책과 매칭
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "daiseek", Grade.VIP);

        int fisDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fisDiscountPrice).isEqualTo(1000);


        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }


    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap,
                               List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap : " + policyMap);
            System.out.println("policies : " + policies);

//            policyMap : {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@375b5b7f, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@1813f3e9}
//            policies : [hello.core.discount.FixDiscountPolicy@375b5b7f, hello.core.discount.RateDiscountPolicy@1813f3e9]

        }

        public int discount(Member member, int price, String discountCode) {
            // 할인 코드를 디스카운트정책과 매칭
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member, price);
        }
    }
}
