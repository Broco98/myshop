package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.Item;
import study.myshop.domain.member.Customer;
import study.myshop.domain.order.Delivery;
import study.myshop.domain.order.Order;
import study.myshop.domain.order.OrderItem;
import study.myshop.domain.regular.Regular;
import study.myshop.domain.regular.RegularItem;
import study.myshop.repository.AddressRepository;
import study.myshop.repository.item.ItemRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.order.DeliveryRepository;
import study.myshop.repository.order.OrderRepository;
import study.myshop.repository.regular.RegularItemRepository;
import study.myshop.repository.regular.RegularOrderRepository;
import study.myshop.repository.regular.RegularRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegularService {

    private final RegularOrderRepository regularOrderRepository;
    private final RegularItemRepository regularItemRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final RegularRepository regularRepository;


    // 정기배송 등록
    @Transactional
    public void registerRegular(Long customerId, Long[] itemIds, Integer[] counts, String address, LocalDateTime regularDeliveryDate) {

        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        List<Item> findItemList = itemRepository.findByIdIn(itemIds);

        List<RegularItem> regularOrderItems = new ArrayList<>();
        int index = 0;
        for (Item findItem : findItemList) {
            RegularItem regularOrderItem = RegularItem.createRegularItem(findItem, findItem.getSalesPrice(), counts[index++]);
            regularOrderItems.add(regularOrderItem);
        }

        Regular regular = Regular.createRegular(findCustomer, address, regularOrderItems, regularDeliveryDate);
        regularRepository.save(regular);
    }

    // 정기배송 취소
    @Transactional
    public void cancelRegular(Long customerId, Long regularId) {
        Regular findRegular = regularRepository.findById(regularId).orElseThrow();

        if (findRegular.getCustomer().getId() != customerId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findRegular.cancel();
    }

    // 정기배송 수정
    @Transactional
    public void updateRegular(Long customerId, Long regularId, Long[] itemIds, Integer[] counts, String address, LocalDateTime regularDeliveryDate) {

        Regular findRegular = regularRepository.findById(regularId).orElseThrow();

        if (findRegular.getCustomer().getId() != customerId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        List<Item> findItemList = itemRepository.findByIdIn(itemIds);

        List<RegularItem> regularOrderItems = new ArrayList<>();
        int index = 0;
        for (Item findItem : findItemList) {
            RegularItem regularOrderItem = RegularItem.createRegularItem(findItem, findItem.getSalesPrice(), counts[index++]);
            regularOrderItems.add(regularOrderItem);
        }

        findRegular.update(address, regularOrderItems, regularDeliveryDate);
    }

    // TODO: 어떻게 일정 시간마다 주문으로 바꿀 수 있는지 공부가 필요함
    // 정기배송 시작
    // @Scheduled()
    public void processOrder(Long regularId) {

    }


    public Regular findById(Long regularId) {
        return regularRepository.findById(regularId).orElseThrow();
    }


}
