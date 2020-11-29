package io.theitsolutions.shop.product.adater.in.web.domain;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    Product mapToProduct(io.theitsolutions.shop.product.domain.Product product);

    List<Product> mapToProducts(List<io.theitsolutions.shop.product.domain.Product> product);

}
