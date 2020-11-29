package io.theitsolutions.quarkus.order.adapter.out.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.theitsolutions.quarkus.order.domain.Order;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public Order findByOrderNumber(String orderNumber) {
        return find("FROM Order o WHERE o.orderNumber = ?1", orderNumber).singleResult();
    }

}
