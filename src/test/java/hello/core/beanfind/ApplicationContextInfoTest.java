package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("All Beans should be printed.")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name : " + beanDefinitionName + " , object :" + bean);
        }
    }

    @Test
    @DisplayName("All Application beans should be printed.")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
        BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

        // Role ROLE_APPLICATION : 내가 직접 등록한 애플리케이션 빈이나 외부 라이브러리
        // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

        if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name : " + beanDefinitionName + " , object : " + bean);
        }
    }
    }
}


// 출력 결과
//name : memberService , object : hello.core.member.MemberServiceImpl@304a9d7b
// name : 등록된 bean 이름 (key)
// object : 클래스로 인해 생성된 인스턴스들(value)