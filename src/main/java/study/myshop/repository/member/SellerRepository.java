package study.myshop.repository.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Seller;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
