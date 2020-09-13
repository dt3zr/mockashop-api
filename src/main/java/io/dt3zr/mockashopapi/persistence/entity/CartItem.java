package io.dt3zr.mockashopapi.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    private Long quantity;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem itemGroup = (CartItem) o;
        return Objects.equals(id, itemGroup.id) &&
                Objects.equals(productCode, itemGroup.productCode) &&
                Objects.equals(quantity, itemGroup.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCode, quantity);
    }
}
