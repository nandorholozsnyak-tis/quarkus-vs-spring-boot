package io.theitsolutions.shop.cart.adapter.out.persistence;


import io.theitsolutions.shop.cart.domain.Cart;
import io.theitsolutions.shop.cart.domain.CartCheckoutData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartCheckoutDataRepository extends JpaRepository<CartCheckoutData, Long> {

    @Query("SELECT ccd FROM CartCheckoutData ccd WHERE ccd.cart = ?1 AND ccd.checkoutStep = ?2")
    Optional<CartCheckoutData> findByCartAndCheckoutStep(Cart cart, String checkoutStep);

}
