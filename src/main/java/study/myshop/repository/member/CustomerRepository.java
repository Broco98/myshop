package study.myshop.repository.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Address;
import study.myshop.domain.member.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c join fetch c.addresses where c.id = :customerId")
    Customer findCustomerByIdWithAddresses(@Param("customerId") Long customerId);
}
