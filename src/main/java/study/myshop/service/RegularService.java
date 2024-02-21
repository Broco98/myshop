package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.Address;
import study.myshop.domain.item.Item;
import study.myshop.domain.member.Customer;
import study.myshop.domain.regular.Regular;
import study.myshop.domain.regular.RegularOrder;
import study.myshop.domain.regular.RegularOrderItem;
import study.myshop.repository.AddressRepository;
import study.myshop.repository.item.ItemRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.order.DeliveryRepository;
import study.myshop.repository.order.OrderRepository;
import study.myshop.repository.regular.RegularOrderItemRepository;
import study.myshop.repository.regular.RegularOrderRepository;
import study.myshop.repository.regular.RegularRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegularService {

    private final RegularOrderRepository regularOrderRepository;
    private final RegularOrderItemRepository regularOrderItemRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final RegularRepository regularRepository;


    // TODO
    // 정기배송 생성
    public void regular(Long customerId, Long[] itemIds, Address address, Integer[] counts) {

        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        List<Item> findItemList = itemRepository.findByIdIn(itemIds);

        List<RegularOrderItem> regularOrderItems = new ArrayList<>();
        int index = 0;
        for (Item findItem : findItemList) {
            RegularOrderItem regularOrderItem = RegularOrderItem.createRegularOrderItem(findItem, findItem.getSalesPrice(), counts[index++]);
            regularOrderItems.add(regularOrderItem);
        }

        Regular regular = Regular.createRegular(findCustomer, address, regularOrderItems);
        regularRepository.save(regular);
    }

    // 정기배송 삭제
    // 정기배송 수정
    // 정기배송 시작
    public void mapRegularToOrder(Long regularId) {

    }
    // 정기배송 조회
    // 정기배송 실제 배송 조회



}
