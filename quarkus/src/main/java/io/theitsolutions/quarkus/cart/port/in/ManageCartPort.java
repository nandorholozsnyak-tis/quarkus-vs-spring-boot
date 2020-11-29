package io.theitsolutions.quarkus.cart.port.in;

import io.theitsolutions.quarkus.cart.domain.Cart;

public interface ManageCartPort {

    Cart getCartByName(String cartName);
}
