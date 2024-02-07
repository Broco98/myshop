package study.myshop.domain.member;


import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity @Getter
public class Seller extends Member{

    private String businessName;
    private String businessInfo;
    private String address;
    private Integer likeNum;

    public Seller() {}

    public Seller(String username, String password, String name, String phoneNumber) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setPhoneNumber(phoneNumber);
        setJoinDate(LocalDateTime.now());
        setWithdrawalDate(null);
        setStopDate(null);
    }

}
