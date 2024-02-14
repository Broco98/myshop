package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.Item;
import study.myshop.domain.item.Marker;
import study.myshop.domain.member.Seller;
import study.myshop.repository.ItemRepository;
import study.myshop.repository.member.MemberRepository;
import study.myshop.repository.member.SellerRepository;
import study.myshop.web.dto.item.ItemAddForm;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;
    
    // TODO -> 페이징 추가 예정, search 추가 예정
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Long addItem(Long sellerId, String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        Seller seller = sellerRepository.findById(sellerId);
        Item item = Item.createItem(seller, name, salesQuantityGram, salesQuantityNum, originalPrice, stock, description);
        itemRepository.save(item);
        return item.getId();
    }

    public void updateItem(Long itemId, String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        Item item = itemRepository.findById(itemId);
        item.update(name, salesQuantityGram, salesQuantityNum, originalPrice, stock, description);

    }
    
    // 하나의 마커를 추가한다
    public void addItemMarker(Long itemId, Marker marker) {
        Item item = itemRepository.findById(itemId);
    }

}
