package study.myshop.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.member.Customer;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String content;

    private int score;


    public static Review createReview(Customer customer, Item item, String content, int score) {
        Review review = new Review();
        review.customer = customer;
        review.item = item;
        review.content = content;
        review.score = score;

        return review;
    }

    public void update(String content, int score) {
        this.content = content;
        this.score = score;
    }

}
