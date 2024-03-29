package study.myshop.repository.item;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.myshop.domain.item.Item;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findByIdIn(Long[] ids) {
        return em.createQuery("select i from Item i where i.id in :ids", Item.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    public Item findByIdWithSeller(Long id) {
        return em.createQuery(
                "select i from Item i" +
                        " join fetch i.seller s" +
                        " where i.id=:id", Item.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
