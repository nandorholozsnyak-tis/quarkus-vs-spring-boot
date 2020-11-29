package io.theitsolutions.quarkus.order.adapter.in.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartPaymentRequest {

    @NotBlank
    private String cartName;

    @NotBlank
    private String paymentMethod;

}
