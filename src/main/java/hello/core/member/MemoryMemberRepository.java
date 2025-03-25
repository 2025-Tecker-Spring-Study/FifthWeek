package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> db = new HashMap<>();
    @Override
    public void save(Member member) {
        db.put(member.getId(), member);
    }

    @Override
    public Member findById(Long member_id) {
        return db.get(member_id);
    }
}
