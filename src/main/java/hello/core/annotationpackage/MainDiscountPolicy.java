package hello.core.annotationpackage;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// 애노테이션을 적용하는 위치를 정의
// ElementType.FIELD : 필드에 적용
// 이외에 FIELD, METHOD, PARAMETER, TYPE, ANNOTATION TYPE에 적용될 수 있다.
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
ElementType.TYPE, ElementType.ANNOTATION_TYPE})

// 애노테이션의 유효 범위를 정의
// RatentionPolicy.RUNTIME : 애노테이션이 런타임까지 유지됨
// 즉, 애노테이션 정보를 런타임 때 사용할 수 있다.
@Retention(RetentionPolicy.RUNTIME)

// 해당 애노테이션이 Javadoc 문서에 포함될지 지정
// @MainDiscountPolicy가 문서화되게 만들 때 사용한다.
@Documented

// 빈 주입 시 특정 빈을 지정
@Qualifier("mainDiscountPolicy")

// @interface : 애노테이션을 정의할 때 명시
public @interface MainDiscountPolicy {

    // 위 코드에서 @Qualifier + @interface 로 합쳐진다.
    // 따라서 public @interface MainDiscountPolicy는
    // @Qualifier("mainDiscountPolicy")와 효과가 동일해진다.



}
