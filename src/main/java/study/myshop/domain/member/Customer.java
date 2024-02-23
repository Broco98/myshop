package study.myshop.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.myshop.domain.item.Item;
import study.myshop.domain.like.LikeItem;
import study.myshop.domain.like.LikeSeller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("customer")
@ToString(callSuper = true) // Member의 변수들도 나오도록
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends Member{

    @Column(nullable = false)
    private String nickName;
    
    // 좋아요-상품
    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LikeItem> likeItems = new HashSet<>();

    // 좋아요-브랜드
    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LikeSeller> likeSellers = new HashSet<>();

    public static Customer createCustomer(String username, String password, String name, String phoneNumber, String nickName) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.nickName = nickName;

        // 기본 설정
        customer.setCreateDate(LocalDateTime.now());
        return customer;
    }


    public void addLikeItem(LikeItem likeItem) {
        this.likeItems.add(likeItem);
        likeItem.setCustomer(this);
    }

    public void removeLikeItem(LikeItem likeItem) {
        this.likeItems.remove(likeItem);
        likeItem.cancel();
    }

    public void addLikeSeller(LikeSeller likeSeller) {
        this.likeSellers.add(likeSeller);
        likeSeller.setCustomer(this);
    }

    public void removeLikeSeller(LikeSeller likeSeller) {
        this.likeSellers.remove(likeSeller);
        likeSeller.cancel();
    }

    // TODO
    public void update() {

    }

}
