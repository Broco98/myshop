package study.myshop.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Seller;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Item extends BasicDate{

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Seller seller;

    @Column(nullable = false)
    private String name;                    // 상품명
    
    // TODO -> [판매수량 / 판매가격] 세트로 만들 순 없나? 보통 쇼핑몰을 보면 그렇게 진행하는 것 같은대
    // ex [1개 3만원], [2개, 5만원] 이런 식으로 선택하도록 -> 고민해 봅시다
    private Integer salesQuantityGram;      // 판매 수량 (단위: gram)
    private Integer salesQuantityNum;       // 판매 수량 (단위: 갯수)

    private Integer originalPrice;          // 가격
    private Integer salesPrice;             // 판매 가격
    private Integer stock;                  // 재고

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.STOP; // 판매, 중단

    private String description;             // 설명

    private Integer likes = 0;                  // 좋아요
    private Integer views = 0;                  // 조회수

    @ToString.Exclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemMarker> itemMarkers = new HashSet<>();       // 마크

    @ToString.Exclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemHashTag> itemHashtags = new HashSet<>();     // 태그

    @ToString.Exclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    // TODO -> image 추가 예정
    
    // TODO -> item 삭제 추가 예정
    
    // == 생성자 ===
    // TODO -> image 추가 예정
    public static Item createItem(Seller seller, String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        Item item = new Item();
        item.seller = seller;
        item.name = name;
        item.salesQuantityGram = salesQuantityGram;
        item.salesQuantityNum = salesQuantityNum;
        item.originalPrice = originalPrice;
        item.salesPrice = originalPrice;
        item.stock = stock;
        item.description = description;

        item.setCreateDate(LocalDateTime.now());

        return item;
    }

    // == 로직 ==
    public void update(String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        this.name = name;
        this.salesQuantityGram = salesQuantityGram;
        this.salesQuantityNum = salesQuantityNum;
        this.originalPrice = originalPrice;
        this.stock = stock;
        this.description = description;
        this.salesPrice = originalPrice;

        this.setUpdateDate(LocalDateTime.now()); // updateDate 최신화
    }

    public void remove() {
        this.setDeleteDate(LocalDateTime.now());
    }

    public void stopSale() {
        this.status = ItemStatus.STOP;
        this.setUpdateDate(LocalDateTime.now());
    }

    public void startSale() {
        this.status = ItemStatus.SALE;
        this.setUpdateDate(LocalDateTime.now());
    }

    public void addStock(int num) {
        stock += num;
    }

    public void removeStock(int num) {
        int result = stock - num;
        if (result < 0) {
            throw new RuntimeException("재고 부족");
        }
        stock -= num;
    }

    public void changeSalesPrice(int salesPrice) {
        if (salesPrice < 0 && salesPrice > originalPrice) {
            throw new RuntimeException("잘못된 가격");
        }
        this.salesPrice = salesPrice;
    }

    public void changeOriginalPrice(int originalPrice) {
        if (salesPrice < 0) {
            throw new RuntimeException("잘못된 가격");
        }
        this.originalPrice = originalPrice;
    }

    public void increaseViews() {
        this.views++;
    }

    public void increaseLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        this.likes--;
    }

    // == 연관관계 메서드 ==
    // TODO -> marker는 고정된 갯수이므로 addMarker가 필요할가? updateMarker로 한번에 최신화 하는게 맞는것 같다.
    public void addMarker(Marker marker) {
        ItemMarker itemMarker = ItemMarker.craeteItemMarker(this, marker);
        itemMarkers.add(itemMarker);
        this.setUpdateDate(LocalDateTime.now());
    }

    // TODO -> marker는 고정된 갯수이므로 addMarker가 필요할가? updateMarker로 한번에 최신화 하는게 맞는것 같다.
    public void removeMarker(Marker marker) {
        itemMarkers.removeIf(itemMarker -> itemMarker.getMarker() == marker);
//        for (ItemMarker itemMarker : itemMarkers) {
//            if (itemMarker.getMarker() == marker) {
//                itemMarkers.remove(itemMarker);
//            }
//        }
        this.setUpdateDate(LocalDateTime.now());
    }

    public void addHashTag(String tag) {
        ItemHashTag itemHashTag = ItemHashTag.craeteItemHashTag(tag);
        itemHashtags.add(itemHashTag);
        itemHashTag.setItem(this);
        this.setUpdateDate(LocalDateTime.now());
    }

    public void removeHashTag(String tag) {
        itemHashtags.removeIf(itemHashTag -> itemHashTag.getTag().equals(tag));
        this.setUpdateDate(LocalDateTime.now());
    }


}