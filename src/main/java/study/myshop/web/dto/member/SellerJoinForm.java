package study.myshop.web.dto.member;

import lombok.Data;

@Data
public class SellerJoinForm {

    private String username;
    private String password;
    private String name;
    private String phoneNumber;

}
