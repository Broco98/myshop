package study.myshop.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Seller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Seller seller;

    private String name;                    // 상품명
    
    // TODO 수정 예정
    private Integer salesQuantityGram;      // 판매 수량 (단위: gram)
    private Integer salesQuantityNum;       // 판매 수량 (단위: 갯수)

    private Integer originalPrice;          // 원래 가격
    private Integer salesPrice;             // 판매 가격
    private Integer stock;                  // 재고

    @Enumerated(EnumType.STRING)
    private ItemStatus status;              // 판매, 중단

    private String description;             // 설명

    private Integer likes;                  // 좋아요
    private Integer views;                  // 조회수

    @Embedded
    private BasicDate date;                 // 생성, 수정, 삭제일


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemMarker> markers = new ArrayList<>();       // 마크들

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemHashTag> hashtags = new ArrayList<>();     // 태그들
    
    // TODO Image 추가 예정

    protected Item() {}

    public Item(Seller seller, String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        this.seller = seller;
        this.name = name;
        this.salesQuantityGram = salesQuantityGram;
        this.salesQuantityNum = salesQuantityNum;
        this.originalPrice = originalPrice;
        this.stock = stock;
        this.description = description;
        this.status = ItemStatus.STOP;
        this.salesPrice = originalPrice;
        this.likes = 0;
        this.views = 0;
        this.date = new BasicDate();
        this.date.setCreateDate(LocalDateTime.now());
    }

}
