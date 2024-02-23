package study.myshop.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BasicDate {

    private LocalDateTime createDate;   // 생성일
    private LocalDateTime updateDate;   // 수정일
    private LocalDateTime deleteDate;   // 삭제일

}
