package study.myshop.domain.member;


import jakarta.persistence.*;
import lombok.*;
import study.myshop.domain.Address;
import study.myshop.domain.item.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("seller")
@ToString(callSuper = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller extends Member{

    // 상호명
//    @Column(nullable = false)
    private String businessName;

    // 상호정보
    private String businessInfo;

    // 좋아요 수
    private Integer likes;
    
    // == 생성 메서드 ==
    public static Seller createSeller(String username, String password, String name, String phoneNumber) {
        Seller seller = new Seller();
        seller.setUsername(username);
        seller.setPassword(password);
        seller.setName(name);
        seller.setPhoneNumber(phoneNumber);
        
        // 기본 설정
        seller.setCreateDate(LocalDateTime.now());
        seller.setStopDate(null);
        seller.setDeleteDate(null);
        seller.likes = 0;
        return seller;
    }

    // == 비즈니스 로직 ==
    public void setBusiness(String businessName, String businessInfo) {
        this.businessName = businessName;
        this.businessInfo = businessInfo;
    }

    public void increaseLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        this.likes--;
    }

}
