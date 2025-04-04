//package hello.core.beanfind;
//
//import hello.core.member.MemberRepository;
//import hello.core.member.MemoryMemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//
//public class ApplicationContextSameBeanFindTest {
//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
//
//    @Test
//    @DisplayName("타입으로 조회할 때 같은 타입이 둘 이상이면, 중복 에러")
//    void findBeanByTypeDuplicate() {
//        MemberRepository bean = ac.getBean(MemberRepository.class);
////        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
//    }
//
//
//    @Test
//    @DisplayName("타입으로 조회시 같은 타입이 둘 이상이면, 빈 이름을 지정하자.")
//    void findByName() {
//        // memberRepository1을 지정하여 가져오기
//        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
//        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
//    }
//
//    @Test
//    @DisplayName("특정 타입으로 모두 조회하기")
//    void findAllBeanByType() {
//        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
//        for (String key : beansOfType.keySet()) {
//            System.out.println("key : " + key + " value : " + beansOfType.get(key));
//        }
//        System.out.println("beansOfType : " + beansOfType);
//        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
//    }
//
//
//
//    @Configuration
//    static class SameBeanConfig {
//
//        // 빈의 이름만 다르고, 인스턴스 타입이 같은 경우
//        @Bean
//        public MemberRepository memberRepository1() {
//            return new MemoryMemberRepository();
//        }
//
//        @Bean
//        public MemberRepository memberRepository2() {
//            return new MemoryMemberRepository();
//        }
//    }
//
//}
//
