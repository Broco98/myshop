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
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Seller seller;

    @Column(nullable = false)
    private String name;                    // 상품명
    
    // TODO 수정 예정
    private Integer salesQuantityGram;      // 판매 수량 (단위: gram)
    private Integer salesQuantityNum;       // 판매 수량 (단위: 갯수)

    private Integer originalPrice;          // 가격
    private Integer salesPrice;             // 판매 가격
    private Integer stock;                  // 재고

    @Enumerated(EnumType.STRING)
    private ItemStatus status;              // 판매, 중단

    private String description;             // 설명

    private Integer likes;                  // 좋아요
    private Integer views;                  // 조회수

    @Embedded
    private BasicDate date;                 // 생성, 수정, 삭제일

    @ToString.Exclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemMarker> markers = new HashSet<>();       // 마크

    @ToString.Exclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemHashTag> hashtags = new HashSet<>();     // 태그

    // == 생성자 ===
    // TODO Image 추가 예정
    public static Item createItem(Seller seller, String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        Item itemEntity = new Item();
        itemEntity.seller = seller;
        itemEntity.name = name;
        itemEntity.salesQuantityGram = salesQuantityGram;
        itemEntity.salesQuantityNum = salesQuantityNum;
        itemEntity.originalPrice = originalPrice;
        itemEntity.salesPrice = originalPrice;
        itemEntity.stock = stock;
        itemEntity.description = description;

        // 기본 설정
        itemEntity.likes = 0;
        itemEntity.views = 0;
        itemEntity.date.setCreateDate(LocalDateTime.now());
        itemEntity.date = BasicDate.createBasicDate();
        itemEntity.status = ItemStatus.STOP;

        return itemEntity;
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

        this.date.setUpdateDate(LocalDateTime.now()); // updateDate 최신화
    }

    public void stopSale() {
        this.status = ItemStatus.STOP;
        this.date.setUpdateDate(LocalDateTime.now());
    }

    public void startSale() {
        this.status = ItemStatus.SALE;
        this.date.setUpdateDate(LocalDateTime.now());
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

    public void increaseOneView() {
        this.views++;
    }

    public void increaseOneLike() {
        this.likes++;
    }

    // == 연관관계 메서드 ==
    public void addMarker(ItemMarker itemMarker) {
        markers.add(itemMarker);
        itemMarker.setItem(this);
        this.date.setUpdateDate(LocalDateTime.now());
    }

    public void removeMarker(ItemMarker itemMarker) {
        if (!markers.isEmpty() && markers.contains(itemMarker)) {
            markers.remove(itemMarker);
            this.date.setUpdateDate(LocalDateTime.now());
        }
    }

    public void addHashTag(ItemHashTag itemHashTag) {
        hashtags.add(itemHashTag);
        itemHashTag.setItem(this);
        this.date.setUpdateDate(LocalDateTime.now());
    }

    public void removeHashTag(ItemHashTag itemHashTag) {
        if (!hashtags.isEmpty() && hashtags.contains(itemHashTag)) {
            hashtags.remove(itemHashTag);
            this.date.setUpdateDate(LocalDateTime.now());
        }
    }
}