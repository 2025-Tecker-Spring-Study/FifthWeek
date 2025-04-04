package hello.core.member;
// Note. 구현체가 하나뿐일때 관례상 인터페이스이름+Impl을 이용한다.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)와 같은 역할!
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

// Q. 위 코드가 DIP, OCP를 위반한 이유?
// A. 10줄에서 MemberRepository에 Memory~ 객체를 자동 타입 변환을 통해 대입함.
// -> MemberRepository 객체에는 실제로 Memory~ 객체가 대입된 것

// 따라서 Memory ~ 클래스가 부모 클래스처럼 작동하고 있다.
// 여기서 메소드를 재정의하여 행위를 실행하고 있는데, 추후에 DB가 설정되면 변경해야 할 가능성이 있다.