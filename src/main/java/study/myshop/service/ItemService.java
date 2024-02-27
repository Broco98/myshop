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

import java.util.List;
import java.util.Set;

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
    public Long addItem(Long sellerId, Item item) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow();
        item.setSeller(seller);
        itemRepository.save(item); // persist 일 때 id가 생성됨 (전략)
        return item.getId();
    }

    @Transactional
    // TODO
    public void updateItem(Long sellerId, Long itemId, ItemUpdateParam updateParam) {

    }

    @Transactional
    public void deleteItem(Long sellerId, Long itemId) {
        Item findItem = itemRepository.findById(itemId);
        findItem.remove();
    }

    @Transactional
    // TODO -> 만약에 marker를 한번에 업데이트 하도록 수정한다면, updateItem에 포함시키자
    public void updateItem(Long itemId, String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        Item item = itemRepository.findById(itemId);
        item.update(name, salesQuantityGram, salesQuantityNum, originalPrice, stock, description);
    }

    @Transactional
    // 하나의 마커를 추가
    // TODO -> 만약에 marker를 한번에 업데이트 하도록 수정한다면, updateItem에 포함시키자
    public void addMarker(Long itemId, Marker marker) {
        Item item = itemRepository.findById(itemId);
        ItemMarker itemMarker = ItemMarker.craeteItemMarker(item, marker);
//        item.addMarker(marker);
    }

    @Transactional
    // TODO -> 만약에 marker를 한번에 업데이트 하도록 수정한다면, updateItem에 포함시키자
    public void removeMarker(Long itemId, Marker marker) {
        Item item = itemRepository.findById(itemId);
        item.removeMarker(marker);
    }

    @Transactional
    // 하나의 해시태그 추가
    public void addHashTag(Long itemId, String tag) {
        Item item = itemRepository.findById(itemId);
        item.addHashTag(tag);
    }

    @Transactional
    public void removeHashTag(Long itemId, String tag) {
        Item item = itemRepository.findById(itemId);
        item.addHashTag(tag);
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
