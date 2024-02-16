package study.myshop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.order.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
