package hello.core.web;


import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 이 클래스를 웹 요청을 처리하는 컨트롤러로 스프링에 등록
@RequiredArgsConstructor // final이 붙은 필드를 자동으로 생성자 주입해줌
public class LogDemoController {

    private final LogDemoService logDemoService; // 서비스 계층 클래스, 로그 찍는 로직이 들어 있음

    //지금 바로 MyLogger를 만들지 말고, 나중에 내가 필요할 때 꺼내쓸게
    private final MyLogger myLogger;  // 마이 로거를 주입 받는게 아니라 마이로거를 찾을 수 있는 dependency lookup할 수 있는 애가 주입이 됨


    @RequestMapping("log-demo") // 브라우저에서 /log-demo로 요청하면 이 메서드 실행됨
    @ResponseBody // 뷰(html)를 리턴하는 게 아니라, 문자열 "OK"를 HTTP 응답으로 직접 내려줌
    public String logDemo(HttpServletRequest request){
        // 요청 URL 가져오기 (예: http://localhost:8080/log-demo)
        String requestURL = request.getRequestURL().toString();

        System.out.println("my Logger = " + myLogger.getClass());
        // 현재 요청 URL을 MyLogger에 저장 (나중에 로그 찍을 때 보여주려고)
        myLogger.setRequestURL(requestURL);

        // 로그 출력 (UUID, 요청 URL, 메시지 같이 출력됨)
        myLogger.log("controller test");

        // 서비스 계층 로직 실행 (여기서도 로그 찍음)
        logDemoService.logic("testId");


        return "OK";
    }
}
