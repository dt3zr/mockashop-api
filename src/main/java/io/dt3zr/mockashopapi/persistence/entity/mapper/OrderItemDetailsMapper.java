package io.dt3zr.mockashopapi.persistence.entity.mapper;

import io.dt3zr.mockashopapi.domain.OrderItemDetails;
import io.dt3zr.mockashopapi.dto.OrderItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemDetailsMapper {
    OrderItemDto toOrderItemDto(OrderItemDetails orderItemDetails);
}
