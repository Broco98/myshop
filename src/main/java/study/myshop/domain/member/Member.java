package study.myshop.domain.member;

import jakarta.persistence.*;
import lombok.*;
import study.myshop.domain.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@ToString @Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    // id
    @Column(nullable = false, unique = true)
    private String username;

    // password
    @Column(nullable = false)
    private String password;

    // 이름
    @Column(nullable = false)
    private String name;

    // 전화번호
    @Column(nullable = false)
    private String phoneNumber;

    // 주소
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();


    @Column(nullable = false)
    private LocalDateTime createDate;   // 가입일
    private LocalDateTime stopDate;     // 정지일 -> 정지일이 존재할 경우 ~ 정지일 까지 정지 상태
    private LocalDateTime deleteDate;   // 탈퇴일 -> 탈퇴일이 존재할 경우 탈퇴상태


    // == 연관관계 메서드 ==
    public void addAddress(Address address) {
        this.address.add(address);
        address.setMember(this);
    }
}
