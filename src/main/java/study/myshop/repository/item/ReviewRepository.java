package study.myshop.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.item.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
