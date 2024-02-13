package study.myshop.repository.member;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Member;
import study.myshop.domain.member.Seller;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public List<Member> findByUsernameAndPassword(String username, String password) {
        return em.createQuery("select m from Member m where username=:username and password=:password", Member.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

}
