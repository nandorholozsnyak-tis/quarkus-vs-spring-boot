package io.theitsolutions.shop.cart.adapter.in.web;

import io.theitsolutions.shop.cart.adapter.in.web.domain.cart.AddToCartRequest;
import io.theitsolutions.shop.cart.adapter.in.web.domain.cart.CartItemResponseModel;
import io.theitsolutions.shop.cart.adapter.in.web.domain.cart.CartResponse;
import io.theitsolutions.shop.cart.domain.Cart;
import io.theitsolutions.shop.cart.port.in.AddToCartPort;
import io.theitsolutions.shop.cart.port.in.ManageCartPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final AddToCartPort addToCartPort;
    private final ManageCartPort manageCartPort;

    @GetMapping(path = "/carts/{cartName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> getCartByName(@PathVariable("cartName") String cartName) {
        Cart cart = manageCartPort.getCartByName(cartName);
        CartResponse response = getResponse(cart);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/add-to-cart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> addItemToCart(@RequestBody @Valid AddToCartRequest addToCartRequest) {
        Cart cart = addToCartPort.addItemToCart(addToCartRequest.getCartName(), addToCartRequest.getProductId(), addToCartRequest.getQuantity());
        CartResponse response = getResponse(cart);
        return ResponseEntity.ok(response);
    }

    private CartResponse getResponse(Cart cart) {
        return new CartResponse(cart.getCartName(), cart.getItems()
                .stream()
                .map(cartItem -> CartItemResponseModel.builder()
                        .productId(cartItem.getProductId())
                        .quantity(cartItem.getQuantity())
                        .build())
                .collect(Collectors.toList()));
    }

}
