package study.myshop.domain.item;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ItemHashTag {

    @Id @GeneratedValue
    @Column(name = "item_hashtag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String tag;
    
    // TODO -> 생성
}
