package hello.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    //자기 자신을 내부의 private으로 하나 static으로 가지고 있음 이렇게 하면 클래스 레벨에 올라가기 때문에 하나만 존재하게 됨
    private static final SingletonService instance = new SingletonService(); //내부적으로 실행해서 이 객체를 생성한 다음에 자기자신을 생성해서 인스턴스에 참조를 넣어놓움
    //자기 자신을 인스턴스 객체 하나 딱 생성해서 여기 안에만 딱 들어감


    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한
    //조회
    public  static SingletonService getInstance(){
        return instance; //인스턴스안에 있는 참조를 꺼낼 수 있는 방법은 얘밖에 없음
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다
   private SingletonService(){ //private이여서 아무도 new로 객체생성 못함

   }

   public void logic(){
       System.out.println("싱글톤 객체 로직 호출");
   }
}
