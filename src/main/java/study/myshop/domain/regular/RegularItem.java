package study.myshop.domain.regular;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.item.Item;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegularItem {

    @Id
    @GeneratedValue
    @Column(name = "regular_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regular_id")
    private Regular regular;

    private int orderPrice;

    private int count;

    public static RegularItem createRegularItem(Item item, int orderPrice, int count) {
        RegularItem regularItem = new RegularItem();
        regularItem.orderPrice = orderPrice;
        regularItem.count = count;
        regularItem.item = item;

        return regularItem;
    }
}
