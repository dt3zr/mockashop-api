package io.dt3zr.mockashopapi.persistence.entity.mapper;

import io.dt3zr.mockashopapi.domain.ShoppingCartItem;
import io.dt3zr.mockashopapi.dto.CartItemDto;
import io.dt3zr.mockashopapi.persistence.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem fromCartItemDto(CartItemDto cartItemDto);
    void toShoppingCartItem(CartItem cartItem, @MappingTarget ShoppingCartItem shoppingCartItem);
}
