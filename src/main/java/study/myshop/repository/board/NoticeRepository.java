package study.myshop.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.board.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
