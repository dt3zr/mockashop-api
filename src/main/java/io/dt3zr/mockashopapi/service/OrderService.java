package io.dt3zr.mockashopapi.service;

import io.dt3zr.mockashopapi.domain.ShoppingCartItems;
import io.dt3zr.mockashopapi.persistence.entity.Order;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public interface OrderService {
    Order addOrder(ShoppingCartItems shoppingCartItems, String transactionId, BigDecimal total);
    Set<Order> getOrdersForUsername(String username);
    Optional<Order> getOrderByTransactionId(String transactionId);
}
