package study.myshop.service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class LoginMember {

    private Long id;
    private String DTYPE;

    public LoginMember(Long id, String DTYPE) {
        this.id = id;
        this.DTYPE = DTYPE;
    }
}
