package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //클래스 레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {//얘가 붙은건 컴포넌트 스캔에 추가할거임MyIncludeComponent
}
