package study.myshop.service.dto;

import lombok.Data;

@Data
public class LoginMember {

    private Long id;
    private String DTYPE;

    protected LoginMember() {}

    public LoginMember(Long id, String DTYPE) {
        this.id = id;
        this.DTYPE = DTYPE;
    }
}
