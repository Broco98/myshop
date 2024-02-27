package study.myshop.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany
    private List<Comment> child = new LinkedList<>();


    public static Reply createReply(Comment parent) {
        Reply reply = new Reply();
        reply.parent = parent;

        return reply;
    }

    // TODO Reply는 좀 더 고민해 볼 것
    public void remove(Comment comment) {
        if (!child.contains(comment))
            throw new RuntimeException("comment 없음");
        child.remove(comment);
    }

    public void removeAll() {
        for (Comment comment : child) {
            comment.remove();
        }
    }

    public void addComment(Comment comment) {
        child.add(comment);
    }
}
