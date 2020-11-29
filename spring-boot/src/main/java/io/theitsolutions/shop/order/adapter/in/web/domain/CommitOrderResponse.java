package io.theitsolutions.shop.order.adapter.in.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommitOrderResponse {

    private String orderState;

    private String paymentState;
}
