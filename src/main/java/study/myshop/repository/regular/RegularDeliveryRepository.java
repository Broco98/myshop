package study.myshop.repository.regular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.regular.RegularDelivery;

@Repository
public interface RegularDeliveryRepository extends JpaRepository<RegularDelivery, Long> {
}
