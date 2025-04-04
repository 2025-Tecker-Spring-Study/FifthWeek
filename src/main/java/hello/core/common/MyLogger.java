package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + " [" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}

// coreApplication 실행 결과
// Error creating bean with name 'myLogger': Scope 'request' is not active for the current thread
// 스프링 서버를 띄우는 단계에서 고객에게 HTTP 요청이 들어오지 않았다.
// 이를 해결하기 위해 provider를 이용한다!

// coreApplication 실행 결과 - 스프링 서버 띄우기 성공!
// [372666a5-b59c-43d0-8e27-789822236a74] request scope bean create: hello.core.common.MyLogger@1a4e96f7
// [372666a5-b59c-43d0-8e27-789822236a74] [/log-demo] controller test
// [372666a5-b59c-43d0-8e27-789822236a74] [/log-demo] service id = testId


// 프록시 객체 사용시
//myLogger : class hello.core.common.MyLogger$$SpringCGLIB$$0
