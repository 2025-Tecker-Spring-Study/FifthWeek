package hello.core.member;

import org.springframework.stereotype.Component;

@Component
public interface MemberRepository {

    // 회원 저장 메소드
    void save(Member member);
    // Member class에 의해 생성된 member 객체를 매개값으로 넣는다.
    // 실행될 기능은 구현 클래스에서 오버라이딩 될 예정이다.


    // 회원 조회 메소드
    Member findById(Long memberId);
    // Wrapper 클래스의 Long 타입으로 포장된 memberId를 매개값으로 넣는다.
    // 이 메소드도 오버라이딩되어 사용될 것이다.
}

// Q. 두 메소드의 선언 키워드가 다른 이유
// A. 리턴타입을 생각하자!
// 1번째 메소드 : 리턴될 값이 없어서 void로 선언했다.
// 내부적으로 데이터를 저장하기 때문에 별다른 반환값이 필요없다.
// 2번째 메소드 : 메소드 실행 후 Member 객체를 반환해야 한다.

// Note. Member 클래스를 별도의 import 없이 사용할 수 있는 이유
