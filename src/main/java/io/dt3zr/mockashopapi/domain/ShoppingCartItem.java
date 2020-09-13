package io.dt3zr.mockashopapi.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartItem {
    private String productCode;
    private long quantity;
    private String type;
    private String name;
    private String description;
    private BigDecimal price;
}
