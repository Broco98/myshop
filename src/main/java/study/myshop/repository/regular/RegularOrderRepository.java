package study.myshop.repository.regular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.regular.RegularOrder;

@Repository
public interface RegularOrderRepository extends JpaRepository<RegularOrder, Long> {
}
