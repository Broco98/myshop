package study.myshop.domain.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity @Getter
public class Admin extends Member{

    private String nickName;

    public Admin() {}

    public Admin(String username, String password, String name, String phoneNumber, String nickName) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setPhoneNumber(phoneNumber);
        setJoinDate(LocalDateTime.now());
        setWithdrawalDate(null);
        setStopDate(null);
        this.nickName = nickName;
    }

}
