package hello.core.order;

import hello.core.annotationpackage.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
//import hello.core.member.MemoryMemberRepository;

@Component
//@RequiredArgsConstructor // @Autowired 이하 생성자 코드 내용과 똑같다.
public class OrderServiceImpl implements OrderService {

    // 1. 생성자 주입법
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    // lombok으로 생성자를 주입해서 임시 주석처리

    // DiscountPolicy에 주입되는 구현체가 2개일 때 (같은 타입의 빈이 2개 일때)
    // 1. 필드 주입 - 필드명을 빈 이름으로 추가 매칭
    // Ex. private DiscountPolicy rateDiscountPolicy


    // 2. 생성자 주입 - 매개변수 이름을 변경하여 빈과 매칭
    // Ex. 주입되는 구현체가 rateDiscountPolicy라고 명명
    @Autowired
    // 생성자 주입을 통해 구현체를 할당해준다.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }


    // 3. @Qualifier 추가 구분자 이용
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    // 하지만, 다른 구현체가 늘어날수록 @Qualifier가 헷갈린다.

    // 4. @Primary 우선순위 사용
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



//    // 2. 수정자 주입법
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository : " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("DiscountPolicy : " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }


    // 3. 필드 주입
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
//
//
//


    // 4. 일반 메서드 주입
//    @Autowired
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy)



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice =  discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // 테스트용 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
