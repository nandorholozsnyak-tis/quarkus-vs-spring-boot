package io.theitsolutions.quarkus.cart.adapter.out.persistence;

import io.theitsolutions.quarkus.cart.domain.Cart;
import io.theitsolutions.quarkus.cart.port.out.CartManagementPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CartManagementAdapter implements CartManagementPort {

    private final CartRepository cartRepository;

    @Override
    public Cart getCartByName(String cartName) {
        log.debug("Finding cart by cartName:[{}]", cartName);
        return cartRepository.findByCartName(cartName);
    }

    @Override
    public Cart save(Cart cart) {
        log.debug("Saving cart:[{}]", cart);
        cartRepository.persist(cart);
        return cart;
    }
}
