package io.dt3zr.mockashopapi.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ShoppingCartItems {
    private String username;
    private Set<ShoppingCartItem> shoppingCartItems = new HashSet<>();

    public void addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.add(shoppingCartItem);
    }
}
