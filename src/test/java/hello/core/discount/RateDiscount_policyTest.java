package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscount_policyTest {

    DiscountPolicy discount_policy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o(){
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // when
        int discount = discount_policy.discount(member, 10000);
        Assertions.assertEquals(discount,1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인 적용 x")
    void vip_x(){
        // given
        Member member = new Member(2L, "memberVIP", Grade.Basic);
        // when
        int discount = discount_policy.discount(member, 10000);
        Assertions.assertEquals(discount,0);
    }
}