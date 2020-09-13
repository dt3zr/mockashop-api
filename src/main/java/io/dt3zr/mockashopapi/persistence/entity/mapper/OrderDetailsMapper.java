package io.dt3zr.mockashopapi.persistence.entity.mapper;

import io.dt3zr.mockashopapi.domain.OrderDetails;
import io.dt3zr.mockashopapi.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    OrderDto toOrderDto(OrderDetails orderDetails);
}
