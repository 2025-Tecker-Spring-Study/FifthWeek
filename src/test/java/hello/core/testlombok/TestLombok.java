package hello.core.testlombok;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class TestLombok {

    private String name;
    private int age;

    private static void main(String[] args) {
        TestLombok testLombok = new TestLombok();
        testLombok.setName("daiseek");
        testLombok.setAge(25);

        String name = testLombok.getName();
        int age = testLombok.getAge();
        System.out.println("name : "+name);
    }
}
