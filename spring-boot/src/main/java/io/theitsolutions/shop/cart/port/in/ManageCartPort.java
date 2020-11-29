package io.theitsolutions.shop.cart.port.in;


import io.theitsolutions.shop.cart.domain.Cart;

public interface ManageCartPort {

    Cart getCartByName(String cartName);
}
