package io.dt3zr.mockashopapi.service;

import io.dt3zr.mockashopapi.domain.ShoppingCartItems;

public interface CompositeShoppingCartService {
    ShoppingCartItems getShoppingCartItemsByUsername(String username);
}
