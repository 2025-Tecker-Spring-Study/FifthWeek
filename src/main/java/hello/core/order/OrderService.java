package hello.core.order;

public interface OrderService {
    public Order createOrder(Long member_id, String item_name, int item_price);
}
