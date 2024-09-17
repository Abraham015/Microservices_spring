package dev.abraham.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemsDTO {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
