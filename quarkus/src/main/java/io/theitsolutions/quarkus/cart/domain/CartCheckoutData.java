package io.theitsolutions.quarkus.cart.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_checkout_data")
public class CartCheckoutData extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private String checkoutStep;

    private String checkoutData;

}
