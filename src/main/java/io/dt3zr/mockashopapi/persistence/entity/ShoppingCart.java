package io.dt3zr.mockashopapi.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
@Data
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems = new HashSet<>();

    public void addCartItemGroup(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setShoppingCart(this);
    }

    @Override
    public String toString() {
        Set<String> items = cartItems.stream().map(g -> "{" + g.getProductCode() + ", " + g.getQuantity() + "}").collect(toSet());
        return "ShoppingCart{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", cartItems=" + items +
                '}';
    }
}
