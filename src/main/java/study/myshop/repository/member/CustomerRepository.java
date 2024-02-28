package study.myshop.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c join fetch c.addresses where c.id = :customerId")
    Customer findCustomerByIdWithAddresses(@Param("customerId") Long customerId);
}
