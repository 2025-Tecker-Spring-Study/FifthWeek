// 간단 서비스 테스트
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//public class MemberApp {
//
//    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//
//
//        MemberService memberService = new MemberServiceImpl();
//        // 테스트용 멤버 생성
//        Member member = new Member(1L, "memberA", Grade.VIP);
//        // Note. id가 Long형이기 때문에 1L으로 지정.
//        // L이 없으면 컴파일 에러가 난다.
//        memberService.join(member);
//
//        Member findMember = memberService.findMember(1L);
//        System.out.println("new member = " + member.getName());
//        System.out.println("find Member = " + findMember.getName());
//        // 실행시 콘솔에 내가 생성한 member의 이름이 나타난다.
//        // 이 코드들은 순수 자바 코드로만 작성한 것.
//        // JUnit으로 테스트하기 위해 test폴더에서 새로 작성하자.
//
//        // Note. test 폴더에서 작성된 코드들은 빌드 후 배포 시 포함되지 않는다.
//        // 정말 테스트 용 코드인것
//    }
//}




// Spring을 이용한 개발
public class MemberApp {

    public static void main(String[] args) {

        // AppConfig에서 관리하는 객체를 호출
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // name: Bean의 이름, MemberService : 반환타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
