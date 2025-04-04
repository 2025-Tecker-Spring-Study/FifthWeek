package hello.core.member;

// 멤버 클래스 정의
// 멤버의 필드, 메소드 정의
public class Member {

    // 회원의 필드(속성) 3가지
    private Long id;
    private String name;
    private Grade grade;

    // Member class의 생성자
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Member class의 필드들을 수정, 가져오는 getter(), setter() 메소드
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    // enum 클래스에서 상수를 매개값으로 받았을 때 값을 설정
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
