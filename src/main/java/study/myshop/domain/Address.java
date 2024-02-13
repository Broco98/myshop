package study.myshop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.myshop.domain.member.Member;

@Entity
@ToString
@Getter
public class Address {

    @Id @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String address;

    // == 생성 메서드 ==
    public static Address createAddress(String address) {
        Address addressEntity = new Address();
        addressEntity.address = address;

        return addressEntity;
    }

}
