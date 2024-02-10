package study.myshop.web.dto.member;

import lombok.Data;

@Data
public class CustomerJoinForm {

    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String nickName;

}
