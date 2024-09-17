package dev.abraham.inventory_service.service;

import dev.abraham.inventory_service.model.Inventory;
import dev.abraham.inventory_service.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Inventory getInventorybyskuCode(String id) {
        Optional<Inventory> inventory=inventoryRepository.findBySkuCode(id);
        return inventory.orElse(null);
    }
}
