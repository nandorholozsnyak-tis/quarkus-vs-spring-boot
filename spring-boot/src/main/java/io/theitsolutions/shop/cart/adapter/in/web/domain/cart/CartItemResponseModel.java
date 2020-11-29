package io.theitsolutions.shop.cart.adapter.in.web.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseModel {

    private Long productId;
    private Integer quantity;

}
