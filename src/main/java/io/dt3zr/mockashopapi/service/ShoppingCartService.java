package io.dt3zr.mockashopapi.service;

import io.dt3zr.mockashopapi.persistence.entity.CartItem;

import java.util.Set;

public interface ShoppingCartService {
    void addCartItem(String username, CartItem cartItem);
    void clear(String username);
    Set<CartItem> getCartItemsByUsername(String username);
}
