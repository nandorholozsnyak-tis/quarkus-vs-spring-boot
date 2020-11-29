package io.theitsolutions.shop.cart.port.in;


import io.theitsolutions.shop.cart.domain.Cart;
import io.theitsolutions.shop.cart.domain.CartCheckoutData;

public interface ManageCartCheckoutDataPort {

    CartCheckoutData save(CartCheckoutData cartCheckoutData);

    CartCheckoutData findByCartAndCheckoutStep(Cart cart, String step);

}
