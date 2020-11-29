package io.theitsolutions.quarkus.cart.adapter.out.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.theitsolutions.quarkus.cart.domain.Cart;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartRepository implements PanacheRepository<Cart> {

    public Cart findByCartName(String cartName) {
        return find("FROM Cart c JOIN FETCH c.items WHERE c.cartName = ?1 ", cartName).singleResult();
    }
}
