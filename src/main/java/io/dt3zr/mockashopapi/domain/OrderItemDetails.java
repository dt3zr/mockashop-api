package io.dt3zr.mockashopapi.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDetails {
    private String productCode;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
}
