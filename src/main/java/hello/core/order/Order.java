package hello.core.order;

public class Order {

    // 주문 시 생성되는 필드들
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    // Order 클래스의 생성자
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    // 할인금액을 정가에서 뺀 기능(메소드)
    public int calculatePrice() {
        return itemPrice - discountPrice;
    }


    public Long getMemberId() {
        return memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId = " + memberId + '\'' +
                ", itemName = " + itemName + '\'' +
                ", discountPrice = " + discountPrice +
                '}';
    }
}
