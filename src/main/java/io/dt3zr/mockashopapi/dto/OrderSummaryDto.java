package io.dt3zr.mockashopapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderSummaryDto {
    private String transactionId;
    private BigDecimal total;
    private Date transactionDate;
    private String status;
}
