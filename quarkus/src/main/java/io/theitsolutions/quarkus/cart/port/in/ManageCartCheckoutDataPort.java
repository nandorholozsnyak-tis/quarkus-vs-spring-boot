package io.theitsolutions.quarkus.cart.port.in;

import io.theitsolutions.quarkus.cart.domain.Cart;
import io.theitsolutions.quarkus.cart.domain.CartCheckoutData;

public interface ManageCartCheckoutDataPort {

    CartCheckoutData save(CartCheckoutData cartCheckoutData);

    CartCheckoutData findByCartAndCheckoutStep(Cart cart, String step);

}
