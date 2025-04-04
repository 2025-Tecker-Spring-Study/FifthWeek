//package hello.core.beanfind;
//
//import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class ApplicationContextExtendsFindTest {
//
//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
//
//    @Test
//    @DisplayName("부모 타입으로 조회시 자식이 둘 이상이면 중복 오류 발생")
//    void findBeanByParentTypeDuplicate() {
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
//        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
//// 실행 시 다음과 같은 오류 발생
////        No qualifying bean of type 'hello.core.discount.DiscountPolicy' available: expected single matching bean but found 2: rateDiscountPolicy,fixDiscountPolicy
////        org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'hello.core.discount.DiscountPolicy' available: expected single matching bean but found 2: rateDiscountPolicy,fixDiscountPolicy
//
//    }
//
//
//
//    @Test
//    @DisplayName("부모 타입으로 조회시 자식이 둘 이상이면, 빈 이름을 지정하자.")
//    void findBeanByParentTypeBeanName() {
//        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
//        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
//    }
//
//
//    @Test
//    @DisplayName("특정 하위 타입으로 조회하기")
//    // 내가 등록한 빈 말고 모든 빈이 조회된다.
//    // 자바는 객체 언어이기 때문
//    void findBeanBySubType() {
//        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
//        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
//    }
//
//
//    @Test
//    @DisplayName("부모 타입으로 모두 조회하기")
//    void findAllBeanByParentType() {
//        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
//        assertThat(beansOfType.size()).isEqualTo(2);
//        for (String key : beansOfType.keySet()) {
//            System.out.println("key : " + key + "value" + beansOfType.get(key));
//        }
//    }
//
//    @Test
//    @DisplayName("부모 타입으로 모두 조회하기 - Object")
//    void findAllBeanByObjectType() {
//        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
//        for (String key : beansOfType.keySet()) {
//            System.out.println("key : " + key + "value" + beansOfType.get(key));
//        }
//    }
//
//
//    @Configuration
//    static class TestConfig {
//
//        // 부모인 DiscountPolicy 타입에 두 가지 자식 구현체가 존재
//        @Bean
//        public DiscountPolicy rateDiscountPolicy() {
//            return new RateDiscountPolicy();
//        }
//
//        @Bean DiscountPolicy fixDiscountPolicy() {
//            return new FixDiscountPolicy();
//        }
//    }
//
//}
//
//
