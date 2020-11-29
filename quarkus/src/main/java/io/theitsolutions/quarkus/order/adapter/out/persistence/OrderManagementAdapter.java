package io.theitsolutions.quarkus.order.adapter.out.persistence;

import io.theitsolutions.quarkus.order.domain.Order;
import io.theitsolutions.quarkus.order.port.OrderManagementPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class OrderManagementAdapter implements OrderManagementPort {

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        log.debug("Saving order:[{}]", order);
        orderRepository.persist(order);
        return order;
    }

    @Override
    public Order findByOrderNumber(String orderNumber) {
        log.debug("Finding order by order number:[{}]", orderNumber);
        return orderRepository.findByOrderNumber(orderNumber);
    }
}
