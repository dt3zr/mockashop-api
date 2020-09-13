package io.dt3zr.mockashopapi.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class OrderDetails {
    private String transactionId;
    private Date transactionDate;
    private String status;
    private BigDecimal total;
    private Set<OrderItemDetails> orderItemDetails;
}
