package study.myshop.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> children = new ArrayList<>();

    @Lob
    private String content;


    public static Comment createComment(Member member, Post post, String content) {
        Comment comment = new Comment();
        comment.member = member;
        comment.post = post;
        comment.content = content;
        comment.setCreateDate(LocalDateTime.now());

        return comment;
    }

    public void update(String content) {
        this.content = content;
        this.setUpdateDate(LocalDateTime.now());
    }

    public void reply(Comment comment) {
        comment.setParent(this);
        children.add(comment);
    }

    public void remove() {
        this.setDeleteDate(LocalDateTime.now());
    }

}
