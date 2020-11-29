package io.theitsolutions.quarkus.cart.application;

import io.theitsolutions.quarkus.cart.domain.Cart;
import io.theitsolutions.quarkus.cart.domain.CartItem;
import io.theitsolutions.quarkus.cart.port.in.AddToCartPort;
import io.theitsolutions.quarkus.cart.port.in.ManageCartPort;
import io.theitsolutions.quarkus.cart.port.out.CartManagementPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CartService implements AddToCartPort, ManageCartPort {

    private final CartManagementPort cartManagementPort;

    @Transactional(NOT_SUPPORTED)
    public Cart getCartByName(String cartName) {
        log.info("Finding cart by name:[{}]", cartName);
        return cartManagementPort.getCartByName(cartName);
    }

    @Override
    @Transactional
    public Cart addItemToCart(String cartName, Long productId, Integer quantity) {
        log.info("Adding product with id:[{}] and with quantity:[{}] to the cart with name:[{}]", productId, quantity, cartName);
        Cart cart = getOrCreateNewCart(cartName);
        createNewCartItemAndPutIntoCart(productId, quantity, cart);
        return cartManagementPort.save(cart);
    }

    private void createNewCartItemAndPutIntoCart(Long productId, Integer quantity, Cart cart) {
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productId(productId)
                .quantity(quantity)
                .build();
        List<CartItem> cartItems = Optional.ofNullable(cart.getItems())
                .orElse(new ArrayList<>(1));
        cartItems.add(cartItem);
        cart.setItems(cartItems);
    }

    private Cart getOrCreateNewCart(String cartName) {
        return StringUtils.isNotBlank(cartName)
                ? cartManagementPort.getCartByName(cartName)
                : createNewCart();
    }

    private Cart createNewCart() {
        return Cart.builder()
                .cartName("cart_" + UUID.randomUUID().toString())
                .build();
    }

}
