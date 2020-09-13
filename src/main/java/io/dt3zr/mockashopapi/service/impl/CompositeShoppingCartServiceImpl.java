package io.dt3zr.mockashopapi.service.impl;

import io.dt3zr.mockashopapi.domain.ShoppingCartItem;
import io.dt3zr.mockashopapi.domain.ShoppingCartItems;
import io.dt3zr.mockashopapi.persistence.entity.CartItem;
import io.dt3zr.mockashopapi.persistence.entity.Product;
import io.dt3zr.mockashopapi.persistence.entity.mapper.CartItemMapper;
import io.dt3zr.mockashopapi.persistence.entity.mapper.ProductMapper;
import io.dt3zr.mockashopapi.service.CompositeShoppingCartService;
import io.dt3zr.mockashopapi.service.ProductCatalogService;
import io.dt3zr.mockashopapi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CompositeShoppingCartServiceImpl implements CompositeShoppingCartService {
    private ProductCatalogService productCatalogService;
    private ShoppingCartService shoppingCartService;
    private ProductMapper productMapper;
    private CartItemMapper cartItemMapper;

    @Autowired
    public CompositeShoppingCartServiceImpl(ProductCatalogService productCatalogService, ShoppingCartService shoppingCartService, ProductMapper productMapper, CartItemMapper cartItemMapper) {
        this.productCatalogService = productCatalogService;
        this.shoppingCartService = shoppingCartService;
        this.productMapper = productMapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public ShoppingCartItems getShoppingCartItemsByUsername(String username) {
        Set<CartItem> cartItems = shoppingCartService.getCartItemsByUsername(username);
        ShoppingCartItems shoppingCartItems = cartItems.stream()
                .map(this::toShoppingCartItem)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(ShoppingCartItems::new, ShoppingCartItems::addShoppingCartItem, (a, b) -> {});
        shoppingCartItems.setUsername(username);
        return shoppingCartItems;
    }

    private Optional<ShoppingCartItem> toShoppingCartItem(CartItem cartItem) {
        Optional<Product> productOptional = productCatalogService.getProductByCode(cartItem.getProductCode());
        if (productOptional.isPresent()) {
            ShoppingCartItem shoppingCartItem = productMapper.toShoppingCartItem(productOptional.get());
            cartItemMapper.toShoppingCartItem(cartItem, shoppingCartItem);
            return Optional.of(shoppingCartItem);
        }
        return Optional.empty();
    }
}
