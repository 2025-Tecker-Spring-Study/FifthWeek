package hello.core.autowired;

// 필요한 클래스들을 import (가져오는 것)
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

// 테스트 클래스
public class AllBeanTest {

    @Test // 테스트할 메서드 표시
    void findAllBean() {
        // 스프링 컨테이너 생성 (AutoAppConfig 안의 빈들을 등록해줌)
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountPolicy.class);

        // 스프링 컨테이너에서 DiscountService 객체 꺼내오기 (빈으로 등록된 상태)
        DiscountService discountService = ac.getBean(DiscountService.class);

        // 테스트용 회원 생성 (VIP 등급)
        Member member = new Member(1L, "userA", Grade.VIP);

        // 고정 할인 정책 테스트 (10000원 중 1000원 할인됨)
        int discountPrice = discountService.discount(member, 10000, "fixDisountPolicy");

        // DiscountService 객체가 잘 생성되었는지 확인
        assertThat(discountService).isInstanceOf(DiscountService.class);

        // 1000원이 맞는지 확인
        assertThat(discountPrice).isEqualTo(1000);

        // 비율 할인 정책 테스트 (2000원 중 10%인 200원 할인)
        int rateDiscountPrice = discountService.discount(member, 2000, "rateDiscountPolicy");

        // 200원이 맞는지 확인
        assertThat(rateDiscountPrice).isEqualTo(200);
    }

    // 내부 클래스: 실제로 할인 정책을 실행해주는 서비스 클래스
    static class DiscountService {

        // 모든 DiscountPolicy 빈을 <이름, 객체>로 담는 Map
        private final Map<String, DiscountPolicy> policyMap;

        // 모든 DiscountPolicy 빈을 담는 리스트
        private final List<DiscountPolicy> policies;

        // 생성자에 @Autowired → 스프링이 자동으로 주입해줌
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            // 디버깅용 출력 (콘솔에 Map 내용 출력)
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        // 할인 계산 메서드
        public int discount(Member member, int price, String discountCode) {
            // 전달받은 코드로 Map에서 해당 할인 정책 찾기
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            // 찾은 정책으로 할인 계산해서 반환
            return discountPolicy.discount(member, price);
        }
    }

}
