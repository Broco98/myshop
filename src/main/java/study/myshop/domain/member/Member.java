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
@ToString
@Getter
@Setter
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
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();


    @Column(nullable = false)
    private LocalDateTime createDate;   // 가입일
    private LocalDateTime suspendDate;     // 정지일 -> 정지일이 존재할 경우 ~ 정지일 까지 정지 상태
    private LocalDateTime withdrawDate;   // 탈퇴일 -> 탈퇴일이 존재할 경우 탈퇴상태


    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    // 정지
    public void stopUntil(LocalDateTime dateTime) {
        suspendDate = dateTime;
    }
    
    // 회원 탈퇴
    public void withdraw() {
        withdrawDate = LocalDateTime.now();
    }

}
