package study.myshop.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.BasicDate;
import study.myshop.domain.item.Item;
import study.myshop.domain.member.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders") // order는 예약어 이므로
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BasicDate{

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    
    // 주문한 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Customer customer;

    // 주문 아이템
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 배송 정보
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private Integer totalPrice = 0;
    private OrderStatus status;


    public static Order createOrder(Customer customer, Delivery delivery, List<OrderItem> orderItems) {
        Order order = new Order();
        order.customer = customer;
        order.delivery = delivery;
        order.setCreateDate(LocalDateTime.now());
        delivery.setOrder(order);

        for (OrderItem orderItem : orderItems) {
            order.orderItems.add(orderItem);
            order.totalPrice += orderItem.getOrderPrice() * orderItem.getCount();
        }
        return order;
    }

    public void cancel() {
        if (this.status.equals(OrderStatus.COMPLETE)) {
            throw new RuntimeException("완료된 배송은 취소할 수 없습니다.");
        }

        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
        this.status = OrderStatus.CANCEL;
    }

}
