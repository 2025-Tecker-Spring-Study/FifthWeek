package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//구현체가 하나만 있을 떄에는 엔터페이스 명뒤에 Impl이라고 적음 멤버서비스 구현체
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    //ac.getBean(MemberRepository.class)
    @Autowired //MemberRepository에 타입에 맞는 애를 찾아와서 의존관계주입을 자동으로 연결해서 주입해줌
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

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return  memberRepository;
    }
}
