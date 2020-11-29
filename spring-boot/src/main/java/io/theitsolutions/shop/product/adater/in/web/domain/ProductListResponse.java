package io.theitsolutions.shop.product.adater.in.web.domain;

import lombok.Value;

import java.util.List;

@Value
public class ProductListResponse {

    List<Product> products;

}
