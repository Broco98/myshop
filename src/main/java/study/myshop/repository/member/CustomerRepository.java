package study.myshop.repository.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Customer;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final EntityManager em;

    public Customer save(Customer Customer) {
        em.persist(Customer);
        return Customer;
    }

    public List<Customer> findAll() {
        return em.createQuery("select a from Customer a", Customer.class)
                .getResultList();
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }
}
