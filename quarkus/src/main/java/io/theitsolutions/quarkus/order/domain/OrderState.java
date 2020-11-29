package io.theitsolutions.quarkus.order.domain;

public enum OrderState {

    INIT,
    PENDING,
    AUTHORIZED,
    COMMITTED,
    FAILED,

}
