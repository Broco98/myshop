package study.myshop.web.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerJoinForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String nickName;

}
