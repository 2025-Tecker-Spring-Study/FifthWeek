package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
//         기존의 AppConfig 파일과 달리 AutoConfig 파일을 설정 파일로 전달
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);

//         각 구현체에서 클래스에 @Component 빈을 등록하고
//         생성자에 @Autowired를 이용하여 의존관계를 설정함.
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
//         memberService 구현체가 MemberService 빈에 주입된 구현체임을 확인!
    }
}
