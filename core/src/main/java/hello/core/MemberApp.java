package hello.core;


import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); //app컨피그에서 멤버서비스 줌 여기에는 MemberServiceImpl이 들어있음
        //MemberService memberService = new MemberServiceImpl(); 기존에는 MemberServiceImpl직접 생성해줬음 MemberServiceImpl에서 메모리 멤버 리파지토리를 또 생성 이제는 앱컨피그에서 결정

        //ApplicationContext는 스프링 컨테이너 모든 객체들을 관리 @Bean이라는 걸 관리
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
