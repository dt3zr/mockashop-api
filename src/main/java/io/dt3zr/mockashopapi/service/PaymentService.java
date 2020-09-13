package io.dt3zr.mockashopapi.service;

import java.math.BigDecimal;

public interface PaymentService {
    String send(BigDecimal amount);
}
