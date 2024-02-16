package study.myshop.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.BasicDate;
import study.myshop.domain.item.Item;
import study.myshop.domain.member.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders") // order는 예약어 이므로
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    
    // 주문한 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("member_id")
    private Customer customer;

    // 주문 아이템
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 배송 정보
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private Integer totalPrice = 0;
    private BasicDate date = BasicDate.createBasicDate();
    private OrderStatus status;


    public static Order createOrder(Customer customer, Delivery delivery, List<OrderItem> orderItems) {
        Order newOrder = new Order();
        newOrder.customer = customer;
        newOrder.delivery = delivery;
        delivery.setOrder(newOrder);

        for (OrderItem orderItem : orderItems) {
            newOrder.orderItems.add(orderItem);
            newOrder.totalPrice += orderItem.getOrderPrice() * orderItem.getCount();
        }
        return newOrder;
    }

    public void cancel() {
        if (this.status.equals(OrderStatus.COMP)) {
            // TODO -> error 생성
            throw new RuntimeException("완료된 배송은 취소할 수 없습니다.");
        }

        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
        this.status = OrderStatus.CANCEL;
    }

}
