package io.theitsolutions.quarkus.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    private String cartName;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "cart")
    private List<CartItem> items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private List<CartCheckoutData> cartCheckoutData;


}
