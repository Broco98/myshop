package study.myshop.service;

import lombok.RequiredArgsConstructor;
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


    // TODO: 날짜 추가
    // 정기배송 생성
    @Transactional
    public void register(Long customerId, Long[] itemIds, Integer[] counts, String address, LocalDateTime regularDeliveryDate) {

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

    // 정기배송 삭제
    @Transactional
    public void cancel(Long regularId) {

    }

    // 정기배송 수정

    // 정기배송 시작
    public void order(Long regularId) {
        Regular regular = regularRepository.findById(regularId).orElseThrow();

        // TODO: 일정 확인

        Delivery delivery = Delivery.createDelivery(regular.getAddress());
        List<OrderItem> orderItems = new ArrayList<>();
        for (RegularItem item : regular.getOrderItems()) {
            OrderItem orderItem = OrderItem.createOrderItem(item.getItem(), item.getOrderPrice(), item.getCount());
            orderItems.add(orderItem);
        }
        Order order = Order.createOrder(regular.getCustomer(), delivery, orderItems);

        orderRepository.save(order);
    }


    public Regular findById(Long regularId) {
        return regularRepository.findById(regularId).orElseThrow();
    }


    // 정기배송 실제 배송 조회



}
