package hello.core.member;

public class MemberServiceImpl implements MemberService {

    MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void signUp(Member member) {
        memberRepository.save(member);
    }
    @Override
    public Member findMember(Long member_id) {
        return memberRepository.findById(member_id);
    }

//    테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
