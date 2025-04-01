package hello.core.member;

import org.springframework.stereotype.Component;


public interface MemberRepository {
    public void save(Member member);
    public Member findById(Long member_id);
}
