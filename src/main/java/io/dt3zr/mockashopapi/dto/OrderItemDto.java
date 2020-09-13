package io.dt3zr.mockashopapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private String productCode;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
}
