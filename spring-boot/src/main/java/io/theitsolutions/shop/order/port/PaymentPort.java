package io.theitsolutions.shop.order.port;


import io.theitsolutions.shop.order.domain.Order;

public interface PaymentPort {

    String startPayment(Order order, String paymentId, String paymentMethod);

    String getPaymentStatus(String paymentId);

}
