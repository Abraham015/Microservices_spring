package dev.abraham.order_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abraham.order_service.dto.InventoryDTO;
import dev.abraham.order_service.dto.OrderItemsDTO;
import dev.abraham.order_service.model.Order;
import dev.abraham.order_service.model.OrderItems;
import dev.abraham.order_service.repository.OrderRepository;
import dev.abraham.order_service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public Order addOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderItems> items=orderRequest.getOrderItemsDTO()
                .stream()
                .map(this::mapToDTO)
                .toList();
        order.setOrderNumber(order.getOrderNumber());
        order.setOrderItems(items);
        //Call inventory service and place order is in stock
        List<String> skuCodes=order.getOrderItems()
                .stream()
                .map(OrderItems::getSkuCode)
                .toList();
        InventoryDTO[] result=webClient.get()
                .uri("http://localhost:8082/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryDTO[].class)
                .block();
        if(result!=null){
            boolean productInStock= Arrays
                    .stream(result)
                    .allMatch(InventoryDTO::isInStock);
            if(productInStock){
                orderRepository.save(order);
                return order;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    private OrderItems mapToDTO(OrderItemsDTO orderItems) {
        OrderItems orderItem = new OrderItems();
        orderItem.setPrice(orderItems.getPrice());
        orderItem.setQuantity(orderItems.getQuantity());
        orderItem.setSkuCode(orderItems.getSkuCode());
        return orderItem;
    }
}
