package io.theitsolutions.shop.order.adapter.out.persistence;

import io.theitsolutions.shop.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.orderNumber = ?1")
    Order findByOrderNumber(String orderNumber);

}
