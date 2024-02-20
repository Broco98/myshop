package study.myshop.repository.regular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.regular.Regular;

@Repository
public interface RegularRepository extends JpaRepository<Regular, Long>{
}
