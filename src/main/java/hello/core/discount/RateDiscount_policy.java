package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscount_policy implements Discount_policy{
    private int discount_percent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discount_percent / 100;
        }
        return 0;
    }
}
