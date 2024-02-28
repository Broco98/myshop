package study.myshop.domain;

import jakarta.persistence.*;
import lombok.*;
import study.myshop.domain.member.Member;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String address;


    public static Address createAddress(Member member, String address) {
        Address addressEntity = new Address();
        addressEntity.member = member;
        addressEntity.address = address;

        return addressEntity;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

}
