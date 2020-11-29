package io.theitsolutions.shop.cart.port.in;


import io.theitsolutions.shop.cart.domain.Cart;

public interface AddToCartPort {

    Cart addItemToCart(String cartName, Long productId, Integer quantity);
}
