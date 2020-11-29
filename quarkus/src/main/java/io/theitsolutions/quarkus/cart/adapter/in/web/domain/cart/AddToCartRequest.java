package io.theitsolutions.quarkus.cart.adapter.in.web.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    private String cartName;
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;

}
