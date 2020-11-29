package io.theitsolutions.shop.order.adapter.in.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartPaymentResponse {

    private String paymentUrl;
    private String orderNumber;

}
