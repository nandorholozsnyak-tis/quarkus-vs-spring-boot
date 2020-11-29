package io.theitsolutions.quarkus.product.application;

import io.theitsolutions.quarkus.product.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@ApplicationScoped
public class ProductService {

    private static final List<Product> PRODUCTS = List.of(
            Product.builder()
                    .id(1L)
                    .brand("Oppa")
                    .name("Keyboard")
                    .price(BigDecimal.valueOf(10))
                    .build(),
            Product.builder()
                    .id(2L)
                    .brand("Lessgo")
                    .name("Phone")
                    .price(BigDecimal.valueOf(20))
                    .build(),
            Product.builder()
                    .id(3L)
                    .brand("MoreThan")
                    .name("Monitor Stand")
                    .price(BigDecimal.valueOf(30))
                    .build()
    );

    public List<Product> getProducts() {
        log.info("Returning all products");
        return PRODUCTS;
    }

    public Product getById(Long id) {
        log.info("Finding product by id:[{}]", id);
        return PRODUCTS.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with id:[" + id + "] does not exist"));
    }

}
