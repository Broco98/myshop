package study.myshop.domain.order;

import jakarta.persistence.*;
import lombok.*;
import study.myshop.domain.member.Address;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    private String address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


    public static Delivery createDelivery(String address) {
        Delivery delivery = new Delivery();
        delivery.address = address;
        delivery.status = DeliveryStatus.READY;

        return delivery;
    }

    public void updateStatus(DeliveryStatus status) {
        this.status = status;
    }

    // TODO
    public void update() {

    }


}
