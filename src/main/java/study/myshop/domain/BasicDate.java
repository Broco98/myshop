package study.myshop.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable @Getter @Setter
public class BasicDate {

    private LocalDateTime createDate;   // 생성일
    private LocalDateTime updateDate;   // 수정일
    private LocalDateTime deleteDate;   // 삭제일

}
