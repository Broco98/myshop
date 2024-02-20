package study.myshop.repository.regular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.regular.RegularOrder;
import study.myshop.domain.regular.RegularOrderItem;

@Repository
public interface RegularOrderItemRepository extends JpaRepository<RegularOrderItem, Long> {
}
