package study.myshop.domain.regular;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Regular extends BasicDate {

    @Id
    @GeneratedValue
    @Column(name = "regular_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    // 주문 상품들
    @OneToMany(mappedBy = "regular", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegularItem> orderItems = new ArrayList<>();

    private String address;

    // 실제 주문 정보
    @OneToMany(mappedBy = "regular", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegularOrder> orders = new ArrayList<>();

    private Integer totalPrice = 0;

    // 정기 배송일
    private LocalDateTime regularDeliveryDate;


    public static Regular createRegular(Customer customer, String address, List<RegularItem> orderItems, LocalDateTime regularDeliveryDate) {
        Regular regular = new Regular();
        regular.customer = customer;
        regular.address = address;
        regular.orderItems.addAll(orderItems);
        regular.regularDeliveryDate = regularDeliveryDate;
        regular.setCreateDate(LocalDateTime.now());

        for (RegularItem orderItem : orderItems) {
            regular.totalPrice += orderItem.getOrderPrice() * orderItem.getCount();
        }

        return regular;
    }

    public void update(String address, List<RegularItem> orderItems, LocalDateTime regularDeliveryDate) {
        this.address = address;
        this.orderItems = orderItems;
        this.regularDeliveryDate = regularDeliveryDate;
        this.setUpdateDate(LocalDateTime.now());

        this.totalPrice = 0; // 초기화
        for (RegularItem orderItem : orderItems) {
            this.totalPrice += orderItem.getOrderPrice() * orderItem.getCount();
        }
    }

    public void cancel() {
        this.setDeleteDate(LocalDateTime.now());
    }
}
