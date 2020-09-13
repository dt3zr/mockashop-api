package io.dt3zr.mockashopapi.service;

import io.dt3zr.mockashopapi.domain.OrderDetails;
import io.dt3zr.mockashopapi.persistence.entity.Order;

public interface CompositeOrderService {
    Order addOrderForUsername(String username);
    OrderDetails getOrderByTransaction(String transactionId);
}
