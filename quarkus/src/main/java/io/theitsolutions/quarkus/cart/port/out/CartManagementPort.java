package io.theitsolutions.quarkus.cart.port.out;

import io.theitsolutions.quarkus.cart.domain.Cart;

public interface CartManagementPort {

    Cart getCartByName(String cartName);

    Cart save(Cart cart);

}
