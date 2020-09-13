package io.dt3zr.mockashopapi.controller;

import io.dt3zr.mockashopapi.domain.OrderDetails;
import io.dt3zr.mockashopapi.dto.OrderDto;
import io.dt3zr.mockashopapi.dto.OrderEventDto;
import io.dt3zr.mockashopapi.dto.OrderItemDto;
import io.dt3zr.mockashopapi.dto.OrderSummaryDto;
import io.dt3zr.mockashopapi.persistence.entity.Order;
import io.dt3zr.mockashopapi.persistence.entity.mapper.OrderDetailsMapper;
import io.dt3zr.mockashopapi.persistence.entity.mapper.OrderItemDetailsMapper;
import io.dt3zr.mockashopapi.persistence.entity.mapper.OrderMapper;
import io.dt3zr.mockashopapi.service.CompositeOrderService;
import io.dt3zr.mockashopapi.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private CompositeOrderService compositeOrderService;
    private OrderService orderService;
    private OrderMapper orderMapper;
    private OrderDetailsMapper orderDetailsMapper;
    private OrderItemDetailsMapper orderItemDetailsMapper;

    public OrderController(CompositeOrderService compositeOrderService, OrderService orderService, OrderMapper orderMapper, OrderDetailsMapper orderDetailsMapper, OrderItemDetailsMapper orderItemDetailsMapper) {
        this.compositeOrderService = compositeOrderService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.orderDetailsMapper = orderDetailsMapper;
        this.orderItemDetailsMapper = orderItemDetailsMapper;
    }

    @PostMapping(value = "/order", produces = "application/json")
    @ResponseBody
    public OrderDto checkout(@RequestBody OrderEventDto orderEvent) {
        log.info("Order received for {}", orderEvent.getUsername());
        return orderMapper.toOrderDto(compositeOrderService.addOrderForUsername(orderEvent.getUsername()));
    }

    @GetMapping(value = "/order/user/{username}", produces = "application/json")
    @ResponseBody
    public Set<OrderSummaryDto> getOrderSummary(@PathVariable String username) {
        return orderService.getOrdersForUsername(username).stream()
                .map(orderMapper::toOrderSummaryDto)
                .collect(toSet());
    }

    @GetMapping(value = "/order/transaction/{transactionId}", produces = "application/json")
    @ResponseBody
    public OrderDto getOrderByTransactionId(@PathVariable String transactionId) {
        OrderDetails orderDetails = compositeOrderService.getOrderByTransaction(transactionId);
        OrderDto orderDto = orderDetailsMapper.toOrderDto(orderDetails);
        Set<OrderItemDto> orderItemDtoSet =orderDetails.getOrderItemDetails().stream()
                .map(orderItemDetailsMapper::toOrderItemDto)
                .collect(toSet());
        orderDto.setOrderItems(orderItemDtoSet);
        return orderDto;
    }

}
