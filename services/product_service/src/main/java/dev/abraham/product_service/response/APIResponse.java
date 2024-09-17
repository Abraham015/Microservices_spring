package dev.abraham.product_service.response;

import lombok.Data;

@Data
public class APIResponse {
    private String message;
    private Object data;
    public APIResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}