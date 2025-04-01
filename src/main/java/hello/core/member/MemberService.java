package hello.core.member;

import org.springframework.stereotype.Component;


public interface MemberService {
    public void signUp(Member member);
    public Member findMember(Long member_id);
}
