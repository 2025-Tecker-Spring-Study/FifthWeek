package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {

        AppConfig appConfig = new AppConfig();

        // 1. 조회(호출)할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 2. 참조값이 다름을 확인
        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);

//        스프링 빈 객체가 달리 등록된다.
//        memberService1 : hello.core.member.MemberServiceImpl@35b74c5c
//        memberService2 : hello.core.member.MemberServiceImpl@163370c2

        // memberService1 != memberService2 임을 보여준다.
        // 또, 멤버 서비스 안에 멤버 레포지토리도 주입되므로 멤버가 많아질수록
        // 생성되는 빈들은 무수히 많아진다.
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

        // 이를 방지하기 위해 싱글턴 패턴을 사용한다.
        // 객체를 하나만 생성하고, 모두가 이 객체를 공유하여 참조하는 방식으로 사용해야 한다!

    }


    // 싱글톤 객체의 로직 확인
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {

        // 객체를 생성하려고 하면, 에러 표시
        // 'SingletonService()' has private access in 'hello.core.singleton.SingletonService'
        // private 키워드를 통해 생성이 막힘
//        new SingletonService();


        // 1. 조회(호출)할 때마다 같은 객체를 반환하여 사용함.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 2. 참조값이 같은지 확인해봄
        System.out.println("singletonService1 : " + singletonService1);
        System.out.println("singletonService2 : " + singletonService2);
//        참조하는 객체 주소
//        singletonService1 : hello.core.singleton.SingletonService@389b0789
//        singletonService2 : hello.core.singleton.SingletonService@389b0789


        // 3. 두 참조값이 같은지 메서드로 확인
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();


//        Note. same VS Equal
//        same : == 참조어 비교
//        equals : equals 메소드 비교
    }


    @Test
    @DisplayName("스프링 컨테이너로 싱글턴 적용하기")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        // 1. 호출할 때 같은 객체를 반환
        MemberService memberService11 = ac.getBean("memberService", MemberService.class);
        MemberService memberService22 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService11 : " + memberService11);
        System.out.println("memberService22 : " + memberService22);

//        memberService11 : hello.core.member.MemberServiceImpl@6bfdb014
//        memberService22 : hello.core.member.MemberServiceImpl@6bfdb014

        Assertions.assertThat(memberService11).isSameAs(memberService22);

    }

}
