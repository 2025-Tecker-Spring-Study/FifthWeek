package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscount_policy implements Discount_policy{
    static int discount_price = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return discount_price;
        }
        return 0;
    }
}
