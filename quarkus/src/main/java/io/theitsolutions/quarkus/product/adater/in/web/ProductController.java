package io.theitsolutions.quarkus.product.adater.in.web;

import io.theitsolutions.quarkus.product.adater.in.web.domain.ProductListResponse;
import io.theitsolutions.quarkus.product.adater.in.web.domain.ProductMapper;
import io.theitsolutions.quarkus.product.application.ProductService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
@ApplicationScoped
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok(new ProductListResponse(productMapper.mapToProducts(productService.getProducts()))).build();
    }

}
