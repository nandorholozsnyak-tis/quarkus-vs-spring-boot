package io.theitsolutions.shop.cart.adapter.out.persistence;

import io.theitsolutions.shop.cart.domain.Cart;
import io.theitsolutions.shop.cart.port.out.CartManagementPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
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
        return cartRepository.save(cart);
    }
}
