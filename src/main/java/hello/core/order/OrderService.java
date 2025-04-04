package hello.core.order;

public interface OrderService {
    // 주문 서비스의 역할
    // 필요한 매개변수를 넣어줌
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
