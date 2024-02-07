package study.myshop.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter @Setter(AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // id

    @Column(nullable = false)
    private String password; // password

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    private LocalDateTime joinDate;         // 가입일
    private LocalDateTime withdrawalDate;   // 탈퇴일
    private LocalDateTime stopDate;         // 정지일

}
