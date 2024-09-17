package dev.abraham.order_service.request;

import dev.abraham.order_service.dto.OrderItemsDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<OrderItemsDTO> orderItemsDTO;
}
