package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.Item;
import study.myshop.domain.member.Customer;
import study.myshop.domain.order.Delivery;
import study.myshop.domain.order.Order;
import study.myshop.domain.order.OrderItem;
import study.myshop.repository.item.ItemRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.order.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Long addOrder(Long customerId, Long[] itemIds, String address, Integer[] counts) {
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        List<Item> findItems = itemRepository.findByIdIn(itemIds);

        Delivery delivery = Delivery.createDelivery(address);

        List<OrderItem> orderItems = new ArrayList<>();
        int index = 0;
        for (Item findItem : findItems) {
            OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getSalesPrice(), counts[index++]);
            orderItems.add(orderItem);
        }

        Order order = Order.createOrder(findCustomer, delivery, orderItems);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long customerId, Long orderId) {
        Order findOrder = orderRepository.findById(orderId); // 그냥 쿼리 2번 날리자.

        if (findOrder.getCustomer().getId() != customerId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findOrder.cancel();
    }


    public List<Order> findMyOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

}
