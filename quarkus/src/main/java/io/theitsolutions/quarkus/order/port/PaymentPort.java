package io.theitsolutions.quarkus.order.port;

import io.theitsolutions.quarkus.order.domain.Order;

public interface PaymentPort {

    String startPayment(Order order, String paymentId, String paymentMethod);

    String getPaymentStatus(String paymentId);

}
