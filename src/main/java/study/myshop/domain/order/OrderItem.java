package study.myshop.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.myshop.domain.item.Item;

@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    
    // 주문 상품
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("order_id")
    private Order order;
    
    // 주문시 가격
    private Integer orderPrice;

    // 주문 갯수
    private Integer count;


    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.item = item;
        newOrderItem.orderPrice = orderPrice;
        newOrderItem.count = count;

        item.removeStock(orderPrice);
        // TODO order의 totalPrice 올리가

        return newOrderItem;
    }

    public void cancel() {
        this.item.addStock(this.count);
    }
}
