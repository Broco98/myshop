package study.myshop.domain.regular;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.member.Customer;
import study.myshop.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Regular {

    @Id
    @GeneratedValue
    @Column(name = "regular_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "regularOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegularOrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regular_delivery_id")
    private RegularDelivery delivery;

    // 실제 주문 정보
    @OneToMany(mappedBy = "regular", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegularOrder> orders = new ArrayList<>();

    private Integer totalPrice = 0;


    public static Regular createRegular(Customer customer, RegularDelivery delivery, List<RegularOrderItem> orderItems) {
        Regular newRegular = new Regular();
        newRegular.customer = customer;
        newRegular.delivery = delivery;
        newRegular.orderItems.addAll(orderItems);

        for (RegularOrderItem orderItem : orderItems) {
            newRegular.totalPrice += orderItem.getOrderPrice() * orderItem.getCount();
        }

        return newRegular;
    }
}
