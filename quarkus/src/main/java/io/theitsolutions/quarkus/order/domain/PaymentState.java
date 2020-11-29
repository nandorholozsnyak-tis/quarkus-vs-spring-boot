package io.theitsolutions.quarkus.order.domain;

public enum PaymentState {

    PAYMENT_INIT,
    PAYMENT_PENDING,
    AUTHORIZED,
    CANCELLED

}
