package study.myshop.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myshop.domain.board.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
