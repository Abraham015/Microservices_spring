package dev.abraham.order_service.controller;

import dev.abraham.order_service.model.Order;
import dev.abraham.order_service.request.OrderRequest;
import dev.abraham.order_service.response.APIResponse;
import dev.abraham.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        try {
            Order order=orderService.addOrder(orderRequest);
            if(order==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("skucode not in stock", null));
            }
            return ResponseEntity.ok(new APIResponse("Order placed", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Error", e.getMessage()));
        }
    }
}
