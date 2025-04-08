package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //클래스 레벨에 붙음  이 애노테이션을 적용할 대상을 지정 (TYPE → 클래스, 인터페이스, enum에 적용 가능)
@Retention(RetentionPolicy.RUNTIME) //이 애노테이션의 유지 기간을 지정 (RUNTIME → 실행 시간에도 유지됨)
@Documented //Javadoc에서 이 애노테이션을 문서화할 수 있도록 설정
public @interface MyexcludeComponent {//얘가 붙은건 컴포넌트 스캔에 제외할거임MyIncludeComponent
    // @interface: 새로운 애노테이션을 정의하는 키워드
    // 이 애노테이션이 붙은 클래스는 컴포넌트 스캔에서 제외됨
}
