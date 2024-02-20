package study.myshop.domain.regular;

import jakarta.persistence.*;
import lombok.Getter;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Customer;
import study.myshop.domain.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class RegularDelivery {

    @Id @GeneratedValue
    @Column(name = "regular_delivery_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Customer customer;
    
    // 정기 배송 시작일
    private LocalDateTime startDate;

    // 결제 정보
    private PayInfo payInfo;

    private BasicDate date = BasicDate.createBasicDate();
    private Integer totalPrice = 0;
}
