package hello.core.order;

public class Order {
    private Long id;
    private String item_name;
    private int item_price;
    private int discount_price;


    public Order(Long id, String item_name, int item_price, int discount_price) {
        this.id = id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.discount_price = discount_price;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public int getDiscount_price(){
        return discount_price;
    }

    public Long getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public int getItem_price() {
        return item_price;
    }

    @Override
    public String toString(){
        return "Order{" +
                "memberId=" + id +
                ", itemName='" + item_name + '\'' +
                ", itemPrice=" + item_price +
                ", discountPrice=" + discount_price +
                '}';
    }
}
