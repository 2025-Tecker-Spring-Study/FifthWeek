package hello.core.order;

import hello.core.discount.Discount_policy;
import hello.core.discount.FixDiscount_policy;
import hello.core.discount.RateDiscount_policy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    // Discount_policy discount_policy = new FixDiscount_policy();
    // Discount_policy discount_policy = new RateDiscount_policy();
    private final Discount_policy discount_policy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(Discount_policy discount_policy, MemberRepository memberRepository) {
        this.discount_policy = discount_policy;
        this.memberRepository = memberRepository;
    }


    @Override
    public Order createOrder(Long member_id, String item_name, int item_price) {
        Member member = memberRepository.findById(member_id);
        int discount_price = discount_policy.discount(member,item_price);
        return new Order(member_id,item_name, item_price, discount_price);
    }
//  테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
