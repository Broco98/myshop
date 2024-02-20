package study.myshop.domain.regular;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.item.Item;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegularOrderItem {

    @Id
    @GeneratedValue
    @Column(name = "regular_order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regular_order_id")
    private RegularOrder regularOrder;

    private Integer orderPrice;

    private Integer count;

    public static RegularOrderItem createRegularOrderItem(Item item, int orderPrice, int count) {
        RegularOrderItem newRegularOrderItem = new RegularOrderItem();
        newRegularOrderItem.orderPrice = orderPrice;
        newRegularOrderItem.count = count;
        newRegularOrderItem.item = item;

        return newRegularOrderItem;
    }
}
