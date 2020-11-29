package io.theitsolutions.quarkus.order.port;

import io.theitsolutions.quarkus.order.domain.Order;

public interface OrderManagementPort {

    Order save(Order order);

    Order findByOrderNumber(String orderNumber);

}
