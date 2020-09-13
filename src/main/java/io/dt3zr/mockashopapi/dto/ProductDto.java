package io.dt3zr.mockashopapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String code;
    private String type;
    private String name;
    private String description;
    private BigDecimal price;
}
