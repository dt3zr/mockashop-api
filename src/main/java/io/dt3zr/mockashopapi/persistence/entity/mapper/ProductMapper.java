package io.dt3zr.mockashopapi.persistence.entity.mapper;

import io.dt3zr.mockashopapi.domain.OrderItemDetails;
import io.dt3zr.mockashopapi.domain.ShoppingCartItem;
import io.dt3zr.mockashopapi.dto.ProductDto;
import io.dt3zr.mockashopapi.persistence.entity.OrderItem;
import io.dt3zr.mockashopapi.persistence.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);
    Product fromProductDto(ProductDto productDto);
    ShoppingCartItem toShoppingCartItem(Product product);
    OrderItemDetails toOrderItemDetails(Product product);
}
