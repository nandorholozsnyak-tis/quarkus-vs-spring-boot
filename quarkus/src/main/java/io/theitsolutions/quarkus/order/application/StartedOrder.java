package io.theitsolutions.quarkus.order.application;

import lombok.Value;

@Value
public class StartedOrder {

    String paymentUrl;
    String orderNumber;
}
