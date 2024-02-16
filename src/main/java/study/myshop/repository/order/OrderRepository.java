package study.myshop.repository.order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.myshop.domain.order.Order;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public List<Order> findByCustomerId(Long customerId) {
        return em.createQuery("select o from Order o where o.customer = :customerId", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
