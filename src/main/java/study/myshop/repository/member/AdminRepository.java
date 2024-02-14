package study.myshop.repository.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Admin;
import study.myshop.domain.member.Member;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
