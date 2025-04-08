package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect"); //커넥트해서 이 url에 붙어
    }

    //실제 콜 연결이 된 상태에서 이제 콜을 부를 수 있다고 가정 그래서 메시지를 던질 수도 있게 할거임
    public void call(String message){
        System.out.println("call: " + url + "message = " + message);//어떤 url에 대해 콜을 했고 다음 메시지는 뭐야 라고해서 출력
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close" + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
