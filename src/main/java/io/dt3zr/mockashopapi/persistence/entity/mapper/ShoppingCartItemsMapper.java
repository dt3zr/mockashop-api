package io.dt3zr.mockashopapi.persistence.entity.mapper;

import io.dt3zr.mockashopapi.domain.ShoppingCartItems;
import io.dt3zr.mockashopapi.dto.ShoppingCartItemsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartItemsMapper {
    ShoppingCartItemsDto toShoppingCartItemDto(ShoppingCartItems shoppingCartItems);
}
