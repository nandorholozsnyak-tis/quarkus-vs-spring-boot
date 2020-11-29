package io.theitsolutions.shop.cart.port.out;


import io.theitsolutions.shop.cart.domain.Cart;

public interface CartManagementPort {

    Cart getCartByName(String cartName);

    Cart save(Cart cart);

}
