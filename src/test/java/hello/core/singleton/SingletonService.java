package hello.core.singleton;

public class SingletonService {

    // 1.  static 영역에 객체를 하나만 생성한다. -> static 키워드의 영향!
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 선언하여 외부 파일에서 해당 인스턴스가 필요하면 조회만 하도록 허용
    // static으로 선언된 메서드를 이용하여 조회
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private 키워드로 선언하여 외부 파일에서 객체를 생성하지 못하도록 막아버림
    private SingletonService() {

    }

    //
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
