package study.myshop.domain.like;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.myshop.domain.item.Item;
import study.myshop.domain.member.Customer;
import study.myshop.domain.order.OrderItem;

@Entity
@Getter
public class LikeItem {

    @Id
    @GeneratedValue
    @Column(name = "like_item_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static LikeItem createLikeItem(Item item) {
        LikeItem newLikeItem = new LikeItem();
        newLikeItem.item = item;
        item.increaseLikes();
        return newLikeItem;
    }

    public void cancel() {
        this.item.decreaseLikes();
    }
}
