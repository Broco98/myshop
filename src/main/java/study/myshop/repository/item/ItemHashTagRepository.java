package study.myshop.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.item.Item;
import study.myshop.domain.item.ItemHashTag;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemHashTagRepository extends JpaRepository<ItemHashTag, Long> {
    Set<ItemHashTag> findByItemId(Long itemId); // FK로 조회
}
