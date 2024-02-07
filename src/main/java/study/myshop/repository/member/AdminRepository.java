package study.myshop.repository.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Admin;
import study.myshop.domain.member.Member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager em;

    public Admin save(Admin admin) {
        em.persist(admin);
        return admin;
    }

    public List<Admin> findAll() {
        return em.createQuery("select a from Admin a", Admin.class)
                .getResultList();
    }

    public Admin findById(Long id) {
        return em.find(Admin.class, id);
    }

}
