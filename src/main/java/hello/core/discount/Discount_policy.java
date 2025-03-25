package hello.core.discount;

import hello.core.member.Member;

public interface Discount_policy {
    public int discount(Member member,int price);
}
