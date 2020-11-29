package io.theitsolutions.quarkus.product.adater.in.web.domain;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    Product mapToProduct(io.theitsolutions.quarkus.product.domain.Product product);

    List<Product> mapToProducts(List<io.theitsolutions.quarkus.product.domain.Product> product);

}
