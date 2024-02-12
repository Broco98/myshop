package study.myshop.domain.item;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class ItemMarker {

    @Id @GeneratedValue
    @Column(name = "item_marker_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String marker;
    
    // TODO -> 생성
}
