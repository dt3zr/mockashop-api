package io.dt3zr.mockashopapi.service.impl;

import io.dt3zr.mockashopapi.domain.OrderDetails;
import io.dt3zr.mockashopapi.domain.OrderItemDetails;
import io.dt3zr.mockashopapi.domain.ShoppingCartItems;
import io.dt3zr.mockashopapi.exception.OrderNotFoundException;
import io.dt3zr.mockashopapi.persistence.entity.Order;
import io.dt3zr.mockashopapi.persistence.entity.OrderItem;
import io.dt3zr.mockashopapi.persistence.entity.mapper.OrderMapper;
import io.dt3zr.mockashopapi.persistence.entity.mapper.ProductMapper;
import io.dt3zr.mockashopapi.service.CompositeOrderService;
import io.dt3zr.mockashopapi.service.CompositeShoppingCartService;
import io.dt3zr.mockashopapi.service.OrderService;
import io.dt3zr.mockashopapi.service.PaymentService;
import io.dt3zr.mockashopapi.service.ProductCatalogService;
import io.dt3zr.mockashopapi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class CompositeOrderServiceImpl implements CompositeOrderService {
    private CompositeShoppingCartService compositeShoppingCartService;
    private ShoppingCartService shoppingCartService;
    private PaymentService paymentService;
    private OrderService orderService;
    private ProductCatalogService productCatalogService;
    private OrderMapper orderMapper;
    private ProductMapper productMapper;

    public CompositeOrderServiceImpl(CompositeShoppingCartService compositeShoppingCartService, ShoppingCartService shoppingCartService, PaymentService paymentService, OrderService orderService, ProductCatalogService productCatalogService, OrderMapper orderMapper, ProductMapper productMapper) {
        this.compositeShoppingCartService = compositeShoppingCartService;
        this.shoppingCartService = shoppingCartService;
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.productCatalogService = productCatalogService;
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
    }

    @Override
    public Order addOrderForUsername(String username) {
        // compute the grand total
        ShoppingCartItems shoppingCartItems = compositeShoppingCartService.getShoppingCartItemsByUsername(username);
        Optional<BigDecimal> totalOptional = shoppingCartItems.getShoppingCartItems().stream()
                .map(i -> i.getPrice().multiply(new BigDecimal(i.getQuantity())))
                .reduce((price1, price2) -> price1.add(price2));

        String transactionId = paymentService.send(totalOptional.get());
        Order order = orderService.addOrder(shoppingCartItems, transactionId, totalOptional.get());
        shoppingCartService.clear(username);
        return order;
    }

    @Override
    public OrderDetails getOrderByTransaction(String transactionId) {
        Order order = orderService.getOrderByTransactionId(transactionId)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order for '%s' cannot be found", transactionId)));

        OrderDetails orderDetails = orderMapper.toOrderDetails(order);

        Set<OrderItemDetails> orderItemDetails =
                order.getOrderItems().stream()
                        .map(this::toOrderItemDetails)
                        .map(Optional::get)
                        .collect(toSet());
        orderDetails.setOrderItemDetails(orderItemDetails);
        return orderDetails;
    }

    private Optional<OrderItemDetails> toOrderItemDetails(OrderItem orderItem) {
        return productCatalogService.getProductByCode(orderItem.getProductCode())
                .map(productMapper::toOrderItemDetails)
                .map(o -> setOrderItemDetails(o, orderItem));
    }

    private OrderItemDetails setOrderItemDetails(OrderItemDetails orderItemDetails, OrderItem orderItem) {
        orderItemDetails.setQuantity(orderItem.getQuantity());
        orderItemDetails.setPrice(orderItem.getPrice());
        return orderItemDetails;
    }
}
