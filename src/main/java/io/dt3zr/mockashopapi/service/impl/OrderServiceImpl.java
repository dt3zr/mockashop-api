package io.dt3zr.mockashopapi.service.impl;

import io.dt3zr.mockashopapi.domain.ShoppingCartItems;
import io.dt3zr.mockashopapi.persistence.OrderRepository;
import io.dt3zr.mockashopapi.persistence.entity.Order;
import io.dt3zr.mockashopapi.persistence.entity.OrderItem;
import io.dt3zr.mockashopapi.persistence.entity.mapper.OrderItemMapper;
import io.dt3zr.mockashopapi.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderItemMapper orderItemMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemMapper orderItemMapper) {
        this.orderRepository = orderRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public Order addOrder(ShoppingCartItems shoppingCartItems, String transactionId, BigDecimal total) {
        Order order = new Order();
        order.setUsername(shoppingCartItems.getUsername());
        order.setTransactionId(transactionId);
        order.setStatus("Shipping soon");
        order.setTotal(total);
        order.setTransactionDate(Date.from(Instant.now()));
        Set<OrderItem> orderItems = shoppingCartItems.getShoppingCartItems().stream()
                .map(orderItemMapper::fromShoppingCartItem).map(o -> this.setOrder(o, order)).collect(toSet());
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Set<Order> getOrdersForUsername(String username) {
        Set<Order> orders = orderRepository.findOrderByUsername(username);
        return orders;
    }

    @Override
    public Optional<Order> getOrderByTransactionId(String transactionId) {
        return orderRepository.findByTransactionId(transactionId);
    }

    private OrderItem setOrder(OrderItem orderItem, Order order) {
        orderItem.setOrder(order);
        return orderItem;
    }
}
