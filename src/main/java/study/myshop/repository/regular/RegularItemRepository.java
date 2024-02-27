package study.myshop.repository.regular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.regular.RegularItem;
import study.myshop.domain.regular.RegularOrder;

@Repository
public interface RegularItemRepository extends JpaRepository<RegularItem, Long> {
}
