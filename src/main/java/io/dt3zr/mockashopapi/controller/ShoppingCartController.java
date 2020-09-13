package io.dt3zr.mockashopapi.controller;

import io.dt3zr.mockashopapi.domain.ShoppingCartItems;
import io.dt3zr.mockashopapi.dto.CartItemDto;
import io.dt3zr.mockashopapi.dto.ShoppingCartItemsDto;
import io.dt3zr.mockashopapi.persistence.entity.mapper.CartItemMapper;
import io.dt3zr.mockashopapi.persistence.entity.mapper.ShoppingCartItemsMapper;
import io.dt3zr.mockashopapi.service.CompositeShoppingCartService;
import io.dt3zr.mockashopapi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private CompositeShoppingCartService compositeShoppingCartService;
    private CartItemMapper cartItemMapper;
    private ShoppingCartItemsMapper shoppingCartItemsMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService, CompositeShoppingCartService compositeShoppingCartService, CartItemMapper cartItemMapper, ShoppingCartItemsMapper shoppingCartItemsMapper) {
        this.shoppingCartService = shoppingCartService;
        this.compositeShoppingCartService = compositeShoppingCartService;
        this.cartItemMapper = cartItemMapper;
        this.shoppingCartItemsMapper = shoppingCartItemsMapper;
    }

    @PostMapping("/shopping-cart")
    public void addCartItem(@RequestBody CartItemDto cartItemDto) {
        shoppingCartService.addCartItem(cartItemDto.getUsername(), cartItemMapper.fromCartItemDto(cartItemDto));
//        return getShoppingCartByUsername(cartItemDto.getUsername());
    }

    @GetMapping("/shopping-cart/{username}")
    public ShoppingCartItemsDto getShoppingCartByUsername(@PathVariable String username) {
        ShoppingCartItems shoppingCartItems = compositeShoppingCartService.getShoppingCartItemsByUsername(username);
        return shoppingCartItemsMapper.toShoppingCartItemDto(shoppingCartItems);
    }
}
