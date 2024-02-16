package study.myshop.domain.item;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemHashTag {

    @Id @GeneratedValue
    @Column(name = "item_hashtag_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private String tag;

    // == 생성자 ==
    public static ItemHashTag craeteItemHashTag(String tag) {
        ItemHashTag itemHashTag = new ItemHashTag();
        itemHashTag.tag = tag;
        return itemHashTag;
    }
}
