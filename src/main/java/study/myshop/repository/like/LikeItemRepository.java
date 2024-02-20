package study.myshop.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.like.LikeItem;

import java.util.Set;

@Repository
public interface LikeItemRepository extends JpaRepository<LikeItem, Long> {

    Set<LikeItem> findByCustomerId(Long customerId);

}
