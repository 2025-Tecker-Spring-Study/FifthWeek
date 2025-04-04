package hello.core.member;

public interface MemberService {
    // 회원가입, 회원조회 기능 필요

    // 회원가입
    void join(Member member);
    // 리턴 값이 없고, 매개값으로 멤버 객체가 필요하다.

    // 회원조회
    Member findMember(Long memberId);
    // 리턴 값이 멤버 객체이고, long형의 memberId가 매개값으로 필요하다.
}
