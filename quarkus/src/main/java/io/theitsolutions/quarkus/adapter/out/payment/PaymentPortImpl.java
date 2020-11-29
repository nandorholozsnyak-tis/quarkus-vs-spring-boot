package io.theitsolutions.quarkus.adapter.out.payment;

import io.theitsolutions.quarkus.order.domain.Order;
import io.theitsolutions.quarkus.order.port.PaymentPort;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class PaymentPortImpl implements PaymentPort {

    @Override
    public String startPayment(Order order, String paymentId, String paymentMethod) {
        log.info("Payment started for order:[{}] with paymentId:[{}] and with payment method:[{}]", order.getOrderNumber(), paymentId, paymentMethod);
        return "https://example.com/order-started";
    }

    @Override
    public String getPaymentStatus(String paymentId) {
        return "paid";
    }

}
