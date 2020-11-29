package io.theitsolutions.shop.order.adapter.out.persistence;

import io.theitsolutions.shop.order.domain.Order;
import io.theitsolutions.shop.order.port.OrderManagementPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderManagementAdapter implements OrderManagementPort {

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        log.debug("Saving order:[{}]", order);
        return orderRepository.save(order);
    }

    @Override
    public Order findByOrderNumber(String orderNumber) {
        log.debug("Finding order by order number:[{}]", orderNumber);
        return orderRepository.findByOrderNumber(orderNumber);
    }
}
