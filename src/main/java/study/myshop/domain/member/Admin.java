package study.myshop.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("admin")
@ToString(callSuper = true) // Member 변수들도 나오도록
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends Member{
    
    // 별명
    @Column(nullable = false)
    private String nickName;


    public static Admin createAdmin(String username, String password, String name, String phoneNumber, String nickName) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setName(name);
        admin.setPhoneNumber(phoneNumber);
        admin.nickName = nickName;

        // 기본 설정
        admin.setCreateDate(LocalDateTime.now());
        return admin;
    }

    // TODO
    public void update() {

    }

}
