package study.myshop.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.myshop.domain.item.ItemHashTag;
import study.myshop.domain.item.ItemMarker;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemMarkerRepository extends JpaRepository<ItemMarker, Long> {
    Set<ItemMarker> findByItemId(Long itemId); // FK로 조회
}
