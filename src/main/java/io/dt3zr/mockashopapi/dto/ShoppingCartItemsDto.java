package io.dt3zr.mockashopapi.dto;

import io.dt3zr.mockashopapi.domain.ShoppingCartItem;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ShoppingCartItemsDto {
    private String username;
    private Set<ShoppingCartItem> shoppingCartItems = new HashSet<>();
}
