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
@DiscriminatorValue("customer")
@ToString(callSuper = true) // Member의 변수들도 나오도록
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends Member{

    @Column(nullable = false)
    private String nickName;        // 별명


    // == 생성 메서드 ==
    public static Customer createCustomer(String username, String password, String name, String phoneNumber, String nickName) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.nickName = nickName;

        // 기본 설정
        customer.setCreateDate(LocalDateTime.now());
        customer.setStopDate(null);
        customer.setDeleteDate(null);
        return customer;
    }
}
