package dev.abraham.inventory_service.controller;

import dev.abraham.inventory_service.dto.InventoryDTO;
import dev.abraham.inventory_service.model.Inventory;
import dev.abraham.inventory_service.reponse.APIResponse;
import dev.abraham.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getInventoryBySkuCode(@RequestParam List<String> skuCode) {
        //System.out.println(inventoryService.getInventorybyskuCode(skuCode));
        return inventoryService.getInventorybyskuCode(skuCode);
    }
}