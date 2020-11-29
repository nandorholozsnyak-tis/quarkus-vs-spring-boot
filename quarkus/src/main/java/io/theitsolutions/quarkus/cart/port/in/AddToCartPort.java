package io.theitsolutions.quarkus.cart.port.in;

import io.theitsolutions.quarkus.cart.domain.Cart;

public interface AddToCartPort {

    Cart addItemToCart(String cartName, Long productId, Integer quantity);
}
