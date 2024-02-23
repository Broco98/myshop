package study.myshop.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Admin;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BasicDate {

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne
    private Admin admin;

    private String title;

    @Lob
    private String content;


    public static Notice createNotice(Admin admin, String title, String content) {
        Notice notice = new Notice();
        notice.admin = admin;
        notice.title = title;
        notice.content = content;
        notice.setCreateDate(LocalDateTime.now());

        return notice;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.setUpdateDate(LocalDateTime.now());
    }
}
