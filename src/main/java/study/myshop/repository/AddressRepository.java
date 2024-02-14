package study.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
