package io.dt3zr.mockashopapi.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private String username;
    private String productCode;
    private long quantity;
}
