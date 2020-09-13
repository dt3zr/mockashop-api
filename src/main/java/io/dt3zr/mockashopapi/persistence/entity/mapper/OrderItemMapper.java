package io.dt3zr.mockashopapi.persistence.entity.mapper;

import io.dt3zr.mockashopapi.domain.ShoppingCartItem;
import io.dt3zr.mockashopapi.persistence.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem fromShoppingCartItem(ShoppingCartItem shoppingCartItem);
}
