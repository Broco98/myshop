package study.myshop.repository.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.myshop.domain.member.Seller;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SellerRepository {

    private final EntityManager em;

    public Seller save(Seller Seller) {
        em.persist(Seller);
        return Seller;
    }

    public List<Seller> findAll() {
        return em.createQuery("select s from Seller s", Seller.class)
                .getResultList();
    }

    public Seller findById(Long id) {
        return em.find(Seller.class, id);
    }

}
