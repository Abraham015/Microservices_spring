package dev.abraham.order_service.service;

import dev.abraham.order_service.dto.OrderItemsDTO;
import dev.abraham.order_service.model.Order;
import dev.abraham.order_service.model.OrderItems;
import dev.abraham.order_service.repository.OrderRepository;
import dev.abraham.order_service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order addOrder(OrderRequest orderRequest) {
        System.out.println(orderRequest.getOrderItemsDTO().toString());
        Order order = new Order();
        List<OrderItems> items=orderRequest.getOrderItemsDTO()
                .stream()
                .map(this::mapToDTO)
                .toList();
        order.setOrderNumber(order.getOrderNumber());
        order.setOrderItems(items);
        orderRepository.save(order);
        return order;
    }

    private OrderItems mapToDTO(OrderItemsDTO orderItems) {
        OrderItems orderItem = new OrderItems();
        orderItem.setPrice(orderItems.getPrice());
        orderItem.setQuantity(orderItems.getQuantity());
        orderItem.setSkuCode(orderItems.getSkuCode());
        return orderItem;
    }
}
