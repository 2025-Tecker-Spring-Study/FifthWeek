package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", // 컴포넌트 스캔할 패키지 지정
//      basePackages = {"hello.core.discount", "hello.core.service"}

        // 지정한 클래스가 담긴 패키지를 탐색 시작 위치로 지정
//        basePackageClasses = AutoAppConfig.class,

        // filter : 컴포넌트 스캔에서 뺄 컴포넌트 선정
        // Configuration.class, AppConfig 환경 설정 클래스는 제외한다.
        // @Configuration에 @Component가 있기 때문 -> 수동 등록
        excludeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = Configuration.class))
public class AutoAppConfig {


//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
    }


// 기존과 달리 @Bean을 이용해 빈을 등록하지 않았다.