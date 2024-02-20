package study.myshop.domain.regular;

import jakarta.persistence.*;
import lombok.Getter;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Customer;

import java.time.LocalDateTime;

@Entity
@Getter
public class RegularDelivery {

    @Id @GeneratedValue
    @Column(name = "regular_delivery_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    private RegularOrder regularOrder;
    
    // 정기 배송 시작일
    private LocalDateTime startDate;

    // 결제 정보
    private PayInfo payInfo;

    private BasicDate date = BasicDate.createBasicDate();
    private Integer totalPrice = 0;
}
