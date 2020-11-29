package io.theitsolutions.quarkus.cart.adapter.out.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.theitsolutions.quarkus.cart.domain.Cart;
import io.theitsolutions.quarkus.cart.domain.CartCheckoutData;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CartCheckoutDataRepository implements PanacheRepository<CartCheckoutData> {

    public Optional<CartCheckoutData> findByCartAndCheckoutStep(Cart cart, String checkoutStep) {
        return find("FROM CartCheckoutData ccd WHERE ccd.cart.id = ?1 AND ccd.checkoutStep = ?2", cart.getId(), checkoutStep).firstResultOptional();
    }

}
