package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.Item;
import study.myshop.domain.item.ItemHashTag;
import study.myshop.domain.item.ItemMarker;
import study.myshop.domain.item.Marker;
import study.myshop.domain.member.Seller;
import study.myshop.dto.item.ItemUpdateParam;
import study.myshop.repository.item.ItemHashTagRepository;
import study.myshop.repository.item.ItemMarkerRepository;
import study.myshop.repository.item.ItemRepository;
import study.myshop.repository.member.SellerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMarkerRepository itemMarkerRepository;
    private final ItemHashTagRepository itemHashTagRepository;
    private final SellerRepository sellerRepository;
    
    // TODO -> 페이징 추가 예정, search 추가 예정
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Transactional
    public Long addItem(Long sellerId, Item item, List<Marker> markers, List<String> hashtags) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow();
        item.setSeller(seller);

        Set<ItemMarker> itemMarkers = markers.stream()
                .map(ItemMarker::craeteItemMarker)
                .collect(Collectors.toSet());
        item.changeItemMarkers(itemMarkers);

        Set<ItemHashTag> itemHashTags = hashtags.stream()
                .map(ItemHashTag::craeteItemHashTag)
                .collect(Collectors.toSet());
        item.changeItemHashTags(itemHashTags);

        itemRepository.save(item); // persist 일 때 id가 생성됨 (전략)
        return item.getId();
    }

    @Transactional
    public void updateItem(Long sellerId, Long itemId, ItemUpdateParam updateParam, List<Marker> markers, List<String> hashtags) {
        Item findItem = itemRepository.findByIdWithSeller(itemId);

        if (findItem.getSeller().getId() != sellerId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findItem.update(
                updateParam.getName(),
                updateParam.getSalesQuantityGram(),
                updateParam.getSalesQuantityNum(),
                updateParam.getOriginalPrice(),
                updateParam.getStock(),
                updateParam.getDescription());

        Set<ItemMarker> itemMarkers = markers.stream()
                .map(ItemMarker::craeteItemMarker)
                .collect(Collectors.toSet());
        findItem.changeItemMarkers(itemMarkers);

        Set<ItemHashTag> itemHashTags = hashtags.stream()
                .map(ItemHashTag::craeteItemHashTag)
                .collect(Collectors.toSet());
        findItem.changeItemHashTags(itemHashTags);
    }

    @Transactional
    public void updateItemStock(Long sellerId, Long itemId, int stock) {
        Item findItem = itemRepository.findByIdWithSeller(itemId);

        if (findItem.getSeller().getId() != sellerId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findItem.updateStock(stock);
    }

    @Transactional
    public void deleteItem(Long sellerId, Long itemId) {
        Item findItem = itemRepository.findByIdWithSeller(itemId);

        if (findItem.getSeller().getId() != sellerId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findItem.remove();
    }

    @Transactional
    public void startSale(Long itemId) {
        Item item = itemRepository.findById(itemId);
        item.startSale();;
    }

    @Transactional void stopSale(Long itemId) {
        Item item = itemRepository.findById(itemId);
        item.stopSale();
    }

}
