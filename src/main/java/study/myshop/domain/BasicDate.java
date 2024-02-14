package study.myshop.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Data // embeddable 클래스 이기 때문에 @Data 어노테이션 사용 -> 핵심 도메인이 아님
public class BasicDate {

    private LocalDateTime createDate;   // 생성일
    private LocalDateTime updateDate;   // 수정일
    private LocalDateTime deleteDate;   // 삭제일

    // == 생성자 ==
    public static BasicDate createBasicDate() {
        BasicDate basicDate = new BasicDate();
        basicDate.createDate = LocalDateTime.now();
        return basicDate;
    }

}
