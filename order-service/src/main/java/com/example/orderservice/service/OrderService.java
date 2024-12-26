package com.example.orderservice.service;

import com.example.orderservice.consumers.BasketCheckoutEvent;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(BasketCheckoutEvent event) {
        Order order = new Order();
        order.setUserName(event.getUserName());
        order.setTotalPrice(event.getTotalPrice());

        List<OrderItem> orderItems = event.getItems().stream()
                .map(itemEvent -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(itemEvent.getProductId());
                    orderItem.setProductName(itemEvent.getProductName());
                    orderItem.setPrice(itemEvent.getPrice());
                    orderItem.setQuantity(itemEvent.getQuantity());
                    orderItem.setOrder(order); // Important: set the order reference
                    return orderItem;
                }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders(String userName) {
        return orderRepository.findAllByUserName(userName);
    }
}
