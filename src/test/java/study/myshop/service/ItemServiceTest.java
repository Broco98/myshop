package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.*;
import study.myshop.domain.member.Seller;
import study.myshop.repository.item.ItemHashTagRepository;
import study.myshop.repository.item.ItemMarkerRepository;
import study.myshop.repository.item.ItemRepository;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired MemberService memberService;
    @Autowired ItemRepository itemRepository;
    @Autowired ItemHashTagRepository itemHashTagRepository;
    @Autowired ItemMarkerRepository itemMarkerRepository;

    @BeforeEach
    void init() {
        Seller seller = Seller.createSeller("111", "111", "111", "111");
        memberService.join(seller);
    }

    @Test
    @DisplayName("상품 추가")
    void addItem() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");

        // when
        Long itemId = itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");

        // then
        List<Item> findItems = itemService.findItems();
        Item findItem = itemService.findById(itemId);
        log.info("findItems={}", findItem);
        assertThat(findItems.size()).isEqualTo(1);
        assertThat(findItem.getStatus()).isEqualTo(ItemStatus.STOP);
    }

    @Test
    @DisplayName("상품 판매 시작 & 상품 판매 중지")
    void startSaleAndStopSale() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");
        Long itemId = itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");
        Item findItem = itemService.findById(itemId);

        // when - 1
        itemService.startSale(itemId);

        // then
        log.info("findItems={}",findItem);
        assertThat(findItem.getStatus()).isEqualTo(ItemStatus.SALE);

        // when - 2
        itemService.stopSale(itemId);

        // then
        assertThat(findItem.getStatus()).isEqualTo(ItemStatus.STOP);
    }

    @Test
    @DisplayName("상품 수정")
    void update() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");
        Long itemId = itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");

        // when
        itemService.updateItem(itemId, "상품2", 1, 2, 3, 4, "테스트 상품 정보 수정");

        // then
        Item findItem = itemService.findById(itemId);
        assertThat(findItem.getName()).isEqualTo("상품2");
        assertThat(findItem.getSalesQuantityGram()).isEqualTo(1);
        assertThat(findItem.getSalesQuantityNum()).isEqualTo(2);
        assertThat(findItem.getOriginalPrice()).isEqualTo(3);
        assertThat(findItem.getStock()).isEqualTo(4);
    }

    @Test
    @DisplayName("마커 추가")
    void addMarker() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");
        Long itemId = itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");
        Item findItem = itemService.findById(itemId);

        // when
        findItem.addMarker(Marker.NEW);
        findItem.addMarker(Marker.GAP);
        findItem.addMarker(Marker.PB);

        // then
        Set<ItemMarker> markers = findItem.getItemMarkers();
        List<ItemMarker> itemMarkers = itemMarkerRepository.findAll();
        log.info("itemMarkers={}", itemMarkers);
        log.info("findItem.getItemMarkers={}", markers);

        assertThat(itemMarkers.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("마커 삭제")
    void removeMarker() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");
        Long itemId = itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");
        Item findItem = itemService.findById(itemId);

        findItem.addMarker(Marker.NEW);
        findItem.addMarker(Marker.GAP);
        findItem.addMarker(Marker.PB);

        // when
        findItem.removeMarker(Marker.NEW);

        // then
        List<ItemMarker> itemMarkers = itemMarkerRepository.findAll();

        assertThat(itemMarkers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("태그 추가")
    void addHashTag() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");
        Long itemId = itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");
        Item findItem = itemService.findById(itemId);

        // when
        findItem.addHashTag("test1");
        findItem.addHashTag("test2");
        findItem.addHashTag("test3");

        // then
        List<ItemHashTag> itemHashTags = itemHashTagRepository.findAll();

        assertThat(itemHashTags.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("태그 추가")
    void removeHashTag() {
        // given
        Seller seller = (Seller) memberService.login("111", "111");
        Long itemId = itemService.addItem(seller.getId(), "상품1", 1, 1, 100, 100, "테스트 상품1");
        Item findItem = itemService.findById(itemId);

        findItem.addHashTag("test1");
        findItem.addHashTag("test2");
        findItem.addHashTag("test3");

        // when
        findItem.removeHashTag("test1");

        // then
        List<ItemHashTag> itemHashTags = itemHashTagRepository.findAll();

        assertThat(itemHashTags.size()).isEqualTo(2);
    }


}