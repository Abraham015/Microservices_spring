package dev.abraham.inventory_service.controller;

import dev.abraham.inventory_service.model.Inventory;
import dev.abraham.inventory_service.reponse.APIResponse;
import dev.abraham.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public ResponseEntity<APIResponse> getInventorybySkuCode(@PathVariable String skuCode) {
        Inventory inventory=inventoryService.getInventorybyskuCode(skuCode);
        if(inventory==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("No stock found", null));
        }
        return ResponseEntity.ok(new APIResponse("Stock found", inventory));
    }
}