package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component // 이 클래스를 스프링 빈으로 등록 (자동 등록됨)
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 이 객체는 HTTP 요청마다 새로 생성됨 (요청 범위)
public class MyLogger {

    private String uuid; // 각 요청마다 고유한 식별자 (UUID)
    private String requestURL; // 요청이 들어온 URL 저장

    public void setRequestURL(String requestURL) {
        // 컨트롤러나 필터 등에서 요청 URL을 받아서 설정해줌
        this.requestURL = requestURL;
    }

    public void log(String message) {
        // 로그를 출력할 때 UUID와 요청 URL을 함께 보여줌
        System.out.println("[" + uuid + "]" + "[" +requestURL + "]" + message);
    }

    @PostConstruct
    public void init(){  // 객체가 생성되고 나서 자동으로 실행됨
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scoper been create:" + this );
    }

    @PreDestroy
    public void close(){  // 객체가 사라지기 직전에 실행됨 (요청 끝날 때 호출됨)
        System.out.println("[" + uuid + "] request scoper been close:" + this);
    }
}
