package io.theitsolutions.shop.product.adater.in.web;

import io.theitsolutions.shop.product.adater.in.web.domain.ProductListResponse;
import io.theitsolutions.shop.product.adater.in.web.domain.ProductMapper;
import io.theitsolutions.shop.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductListResponse> getProducts() {
        return ResponseEntity.ok(new ProductListResponse(productMapper.mapToProducts(productService.getProducts())));
    }

}
