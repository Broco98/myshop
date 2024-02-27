package study.myshop.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BasicDate {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne
    private Reply reply;

    @Lob
    private String content;


    public static Comment createComment(Member member, Post post, String content) {
        Comment comment = new Comment();
        comment.member = member;
        comment.post = post;
        comment.content = content;
        comment.reply = Reply.createReply(comment);
        comment.setCreateDate(LocalDateTime.now());

        return comment;
    }

    public void update(String content) {
        this.content = content;
        this.setUpdateDate(LocalDateTime.now());
    }

    public void remove(Comment comment) {
        this.reply.remove(comment);
        comment.remove();
    }

    public void remove() {
        this.reply.removeAll();
        this.setDeleteDate(LocalDateTime.now());
    }

}
