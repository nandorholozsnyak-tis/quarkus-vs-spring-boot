package io.theitsolutions.quarkus.cart.adapter.in.web;

import io.theitsolutions.quarkus.cart.adapter.in.web.domain.cart.AddToCartRequest;
import io.theitsolutions.quarkus.cart.adapter.in.web.domain.cart.CartItemResponseModel;
import io.theitsolutions.quarkus.cart.adapter.in.web.domain.cart.CartResponse;
import io.theitsolutions.quarkus.cart.domain.Cart;
import io.theitsolutions.quarkus.cart.port.in.AddToCartPort;
import io.theitsolutions.quarkus.cart.port.in.ManageCartPort;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/api/v1")
@ApplicationScoped
@RequiredArgsConstructor
public class CartController {

    private final AddToCartPort addToCartPort;
    private final ManageCartPort manageCartPort;

    @GET
    @Path("/carts/{cartName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCartByName(@PathParam("cartName") String cartName) {
        Cart cart = manageCartPort.getCartByName(cartName);
        CartResponse response = getResponse(cart);
        return Response.ok(response).build();
    }

    @POST
    @Path("/add-to-cart")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToCart(@Valid AddToCartRequest addToCartRequest) {
        Cart cart = addToCartPort.addItemToCart(addToCartRequest.getCartName(), addToCartRequest.getProductId(), addToCartRequest.getQuantity());
        CartResponse response = getResponse(cart);
        return Response.ok(response).build();
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
