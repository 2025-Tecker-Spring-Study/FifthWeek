package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @RequiredArgsConstructor
@Component
public class OrderServiceImpl implements OrderService{
    // discountPolicy discountPolicy = new FixdiscountPolicy();
    // discountPolicy discountPolicy = new RatediscountPolicy();
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    @Autowired
    public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }


    @Override
    public Order createOrder(Long member_id, String item_name, int item_price) {
        Member member = memberRepository.findById(member_id);
        int discount_price = discountPolicy.discount(member,item_price);
        return new Order(member_id,item_name, item_price, discount_price);
    }
//  테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
