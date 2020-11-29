package io.theitsolutions.shop.order.port;


import io.theitsolutions.shop.order.domain.Order;

public interface OrderManagementPort {

    Order save(Order order);

    Order findByOrderNumber(String orderNumber);

}
