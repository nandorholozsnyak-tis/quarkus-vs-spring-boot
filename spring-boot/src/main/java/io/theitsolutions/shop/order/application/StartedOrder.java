package io.theitsolutions.shop.order.application;

import lombok.Value;

@Value
public class StartedOrder {

    String paymentUrl;
    String orderNumber;
}
