//package hello.core.beandefinition;
//
//
////import hello.core.AppConfig;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//
//public class BeanDefinitionTest {
////    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//
//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
//
//    @Test
//    @DisplayName("빈 설정 메타 정보 확인")
//    void findApplicationBean() {
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
//
//            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
//                System.out.println("beanDefitniionName" + beanDefinitionName + " beanDefinition : " + beanDefinition);
//            }
//        }
//    }
//
//}
//
//
////beanDefitniionNameappConfig beanDefinition : Generic bean: class=hello.core.AppConfig$$SpringCGLIB$$0; scope=singleton; abstract=false; lazyInit=null; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=null; factoryMethodName=null; initMethodNames=null; destroyMethodNames=null
////beanDefitniionNamememberService beanDefinition : Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=memberService; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig
////beanDefitniionNamememberRepository beanDefinition : Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=memberRepository; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig
////beanDefitniionNameorderService beanDefinition : Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=orderService; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig
////beanDefitniionNamediscountPolicy beanDefinition : Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=discountPolicy; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig
