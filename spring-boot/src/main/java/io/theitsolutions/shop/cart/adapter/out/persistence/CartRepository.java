package io.theitsolutions.shop.cart.adapter.out.persistence;


import io.theitsolutions.shop.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c JOIN FETCH c.items WHERE c.cartName = ?1 ")
    Cart findByCartName(String cartName);

}
