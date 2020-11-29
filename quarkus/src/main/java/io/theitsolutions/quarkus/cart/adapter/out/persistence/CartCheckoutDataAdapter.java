package io.theitsolutions.quarkus.cart.adapter.out.persistence;

import io.theitsolutions.quarkus.cart.domain.Cart;
import io.theitsolutions.quarkus.cart.domain.CartCheckoutData;
import io.theitsolutions.quarkus.cart.port.in.ManageCartCheckoutDataPort;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

@ApplicationScoped
@RequiredArgsConstructor
public class CartCheckoutDataAdapter implements ManageCartCheckoutDataPort {

    private final CartCheckoutDataRepository cartCheckoutDataRepository;

    @Override
    public CartCheckoutData save(CartCheckoutData cartCheckoutData) {
        cartCheckoutDataRepository.persist(cartCheckoutData);
        return cartCheckoutData;
    }

    @Override
    public CartCheckoutData findByCartAndCheckoutStep(Cart cart, String step) {
        return cartCheckoutDataRepository.findByCartAndCheckoutStep(cart, step)
                .orElseThrow(() -> new NoResultException("CartCheckoutData with cart and step does not exist"));
    }
}
