package study.myshop.domain.item;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemMarker {

    @Id @GeneratedValue
    @Column(name = "item_marker_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private Marker marker;

    // == 생성자 ==
    public static ItemMarker craeteItemMarker(Marker marker) {
        ItemMarker itemMarker = new ItemMarker();
        itemMarker.marker = marker;
        return itemMarker;
    }
}
