package study.myshop.web.dto.member;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginMember {

    private Long id;
    private String dtype;

    public LoginMember(Long id, String dtype) {
        this.id = id;
        this.dtype = dtype;
    }
}
