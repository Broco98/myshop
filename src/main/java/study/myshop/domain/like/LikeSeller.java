package study.myshop.domain.like;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.myshop.domain.member.Customer;
import study.myshop.domain.member.Seller;

@Entity
@Getter
public class LikeSeller {

    @Id
    @GeneratedValue
    @Column(name = "like_seller_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public static LikeSeller createLikeSeller(Seller seller) {
        LikeSeller newLikeSeller = new LikeSeller();
        newLikeSeller.seller = seller;
        seller.increaseLikes();
        return newLikeSeller;
    }

    public void cancel() {
        this.seller.decreaseLikes();
    }

}
