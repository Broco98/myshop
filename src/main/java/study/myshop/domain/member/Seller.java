package study.myshop.domain.member;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("seller")
@ToString(callSuper = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller extends Member{

    // 상호명
    private String businessName;

    // 상호정보
    private String businessInfo;

    // 좋아요 수
    private int likes;


    public static Seller createSeller(String username, String password, String name, String phoneNumber) {
        Seller seller = new Seller();
        seller.setUsername(username);
        seller.setPassword(password);
        seller.setName(name);
        seller.setPhoneNumber(phoneNumber);
        
        // 기본 설정
        seller.setCreateDate(LocalDateTime.now());
        seller.likes = 0;
        return seller;
    }

    // 상호명, 상호정보 설정
    public void updateBusiness(String businessName, String businessInfo) {
        this.businessName = businessName;
        this.businessInfo = businessInfo;
    }

    // TODO
    public void update(String name, String phoneNumber) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
    }

    public void increaseLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        this.likes--;
    }

}
