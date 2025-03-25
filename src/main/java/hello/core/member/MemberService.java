package hello.core.member;

public interface MemberService {
    public void signUp(Member member);
    public Member findMember(Long member_id);
}
