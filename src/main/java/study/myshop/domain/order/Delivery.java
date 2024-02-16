package study.myshop.domain.order;

import jakarta.persistence.*;
import lombok.*;
import study.myshop.domain.Address;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
    @Setter
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


    public static Delivery createDelivery(Address address) {
        Delivery newDelivery = new Delivery();
        newDelivery.address = address;
        newDelivery.status = DeliveryStatus.READY;

        return newDelivery;
    }

}
