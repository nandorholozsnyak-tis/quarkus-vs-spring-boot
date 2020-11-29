package io.theitsolutions.shop.cart.adapter.out.persistence;

import io.theitsolutions.shop.cart.domain.Cart;
import io.theitsolutions.shop.cart.domain.CartCheckoutData;
import io.theitsolutions.shop.cart.port.in.ManageCartCheckoutDataPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
@RequiredArgsConstructor
public class CartCheckoutDataAdapter implements ManageCartCheckoutDataPort {

    private final CartCheckoutDataRepository cartCheckoutDataRepository;

    @Override
    public CartCheckoutData save(CartCheckoutData cartCheckoutData) {
        return cartCheckoutDataRepository.save(cartCheckoutData);
    }

    @Override
    public CartCheckoutData findByCartAndCheckoutStep(Cart cart, String step) {
        return cartCheckoutDataRepository.findByCartAndCheckoutStep(cart, step)
                .orElseThrow(() -> new NoResultException("CartCheckoutData with cart and step does not exist"));
    }
}
