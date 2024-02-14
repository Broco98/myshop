package study.myshop.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.Item;
import study.myshop.domain.item.ItemMarker;
import study.myshop.domain.member.Seller;
import study.myshop.repository.item.ItemHashTagRepository;
import study.myshop.repository.item.ItemMarkerRepository;
import study.myshop.repository.item.ItemRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemHashTagRepository itemHashTagRepository;

    @Autowired
    ItemMarkerRepository itemMarkerRepository;

    @BeforeEach
    void init() {
        Seller seller = Seller.createSeller("111", "111", "111", "111");
        memberService.join(seller);
    }

    @Test
    void 상품_추가() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");

        // when
        itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");

        // then
        List<Item> findItems = itemService.findItems();
        log.info("findItems={}",findItems.get(0));
        assertThat(findItems.size()).isEqualTo(1);
    }

    @Test
    void 상품_판매시작() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");
        itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");
        List<Item> findItems = itemService.findItems();
        Item findItem = findItems.get(0);

        // when
//        itemService.update


        // then
        log.info("findItems={}",findItems.get(0));
        assertThat(findItems.size()).isEqualTo(1);
    }


}