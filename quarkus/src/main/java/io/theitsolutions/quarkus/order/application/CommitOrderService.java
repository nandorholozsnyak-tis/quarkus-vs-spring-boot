package io.theitsolutions.quarkus.order.application;

import io.theitsolutions.quarkus.order.domain.Order;
import io.theitsolutions.quarkus.order.domain.OrderState;
import io.theitsolutions.quarkus.order.domain.PaymentInfo;
import io.theitsolutions.quarkus.order.domain.PaymentState;
import io.theitsolutions.quarkus.order.port.OrderManagementPort;
import io.theitsolutions.quarkus.order.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CommitOrderService {

    private final PaymentPort paymentPort;
    private final OrderManagementPort orderManagementPort;

    @Transactional
    public Order commitOrder(String orderNumber) {
        log.info("Committing order with order number:[{}]", orderNumber);
        Order order = orderManagementPort.findByOrderNumber(orderNumber);
        PaymentInfo pendingPaymentInfo = order.getInitOrPendingPaymentInfo();
        String paymentReference = pendingPaymentInfo.getPaymentReference();
        String paymentStatus = paymentPort.getPaymentStatus(paymentReference);
        PaymentInfo currentPaymentInfo = PaymentInfo.ofPaymentInfo(pendingPaymentInfo);
        currentPaymentInfo.setPaymentState(getStatus(paymentStatus));
        order.addPaymentInfo(currentPaymentInfo);
        updateOrderState(order, currentPaymentInfo);
        return orderManagementPort.save(order);
    }

    private void updateOrderState(Order order, PaymentInfo currentPaymentInfo) {
        if (currentPaymentInfo.getPaymentState() == PaymentState.CANCELLED) {
            throw new RuntimeException("Payment failed, please start a new payment for this order");
        }
        if (currentPaymentInfo.getPaymentState() == PaymentState.PAYMENT_PENDING) {
            order.setOrderState(OrderState.PENDING);
        }
        if (currentPaymentInfo.getPaymentState() == PaymentState.AUTHORIZED) {
            order.setOrderState(OrderState.COMMITTED);
            currentPaymentInfo.setPaymentFinish(LocalDateTime.now());
        }
    }

    private PaymentState getStatus(String paymentStatus) {
        switch (paymentStatus) {
            case "open":
            case "pending":
                return PaymentState.PAYMENT_PENDING;
            case "paid":
                return PaymentState.AUTHORIZED;
            case "failed":
                return PaymentState.CANCELLED;
            default:
                throw new IllegalStateException("Unexpected value: " + paymentStatus);
        }
    }
}
