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
public class RegularOrder {

    @Id
    @GeneratedValue
    @Column(name = "regular_order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regular_id")
    private Regular regular;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public static RegularOrder createRegularOrder(Order order, Regular regular) {
        RegularOrder newRegularOrder = new RegularOrder();
        newRegularOrder.regular = regular;
        newRegularOrder.order = order;
        return newRegularOrder;
    }

}
