package study.myshop.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.like.LikeSeller;

import java.util.Set;

@Repository
public interface LikeSellerRepository extends JpaRepository<LikeSeller, Long> {

    Set<LikeSeller> findByCustomerId(Long customerId);
}
