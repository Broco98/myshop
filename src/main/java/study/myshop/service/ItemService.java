package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.Item;
import study.myshop.domain.member.Seller;
import study.myshop.repository.ItemRepository;
import study.myshop.web.dto.item.ItemAddForm;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item add(Seller seller, String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        Item item = new Item(seller, name, salesQuantityGram, salesQuantityNum, originalPrice, stock, description);
        itemRepository.save(item);
        return item;
    }

}
