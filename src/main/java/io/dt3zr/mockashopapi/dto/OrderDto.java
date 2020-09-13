package io.dt3zr.mockashopapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDto {
    private String username;
    private String status;
    private BigDecimal total;
    private String transactionId;
    private Date transactionDate;
    private Set<OrderItemDto> orderItems = new HashSet<>();
}
