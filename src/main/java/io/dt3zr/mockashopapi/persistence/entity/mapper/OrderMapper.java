package io.dt3zr.mockashopapi.persistence.entity.mapper;

import io.dt3zr.mockashopapi.domain.OrderDetails;
import io.dt3zr.mockashopapi.dto.OrderDto;
import io.dt3zr.mockashopapi.dto.OrderSummaryDto;
import io.dt3zr.mockashopapi.persistence.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
    OrderSummaryDto toOrderSummaryDto(Order order);
    OrderDetails toOrderDetails(Order order);
}
