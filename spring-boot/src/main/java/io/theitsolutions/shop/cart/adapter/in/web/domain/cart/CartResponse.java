package io.theitsolutions.shop.cart.adapter.in.web.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private String cartName;
    private List<CartItemResponseModel> items;

}
