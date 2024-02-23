package study.myshop.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.myshop.domain.BasicDate;
import study.myshop.domain.member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BasicDate {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    
    @Lob // 다른 DB를 사용한다면 @Column의 definition을 사용
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ToString.Exclude
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    public static Post createPost(String title, String content, Member member) {
        Post post = new Post();
        post.title = title;
        post.content = content;
        post.member = member;
        post.setCreateDate(LocalDateTime.now());

        return post;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        if (comments.isEmpty() || !comments.contains(comment))
            return;
        comment.remove();
        comments.remove(comment);
    }
}
