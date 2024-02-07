package study.myshop.domain.member;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity @Getter
public class Customer extends Member{

    private String nickName;

    public Customer() {}

    public Customer(String username, String password, String name, String phoneNumber, String nickName) {
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
