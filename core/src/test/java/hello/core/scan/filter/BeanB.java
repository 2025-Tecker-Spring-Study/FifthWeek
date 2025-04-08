package hello.core.scan.filter;

// @MyExcludeComponent 애노테이션이 적용된 클래스
@MyexcludeComponent  // 이 클래스는 스프링 컴포넌트 스캔에서 제외됨
public class BeanB {
    // 빈으로 등록되지 않음
}
