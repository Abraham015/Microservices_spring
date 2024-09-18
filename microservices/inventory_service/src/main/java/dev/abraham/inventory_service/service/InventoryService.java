package dev.abraham.inventory_service.service;

import dev.abraham.inventory_service.dto.InventoryDTO;
import dev.abraham.inventory_service.model.Inventory;
import dev.abraham.inventory_service.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<InventoryDTO> getInventorybyskuCode(List<String> id) {
        List<Inventory> inventory=inventoryRepository.findBySkuCodeIn(id);
        return inventory.stream()
                .map(inventory1 -> InventoryDTO.builder()
                    .skuCode(inventory1.getSkuCode())
                    .isInStock(inventory1.getQuantity()>0)
                    .build())
                .toList();
    }
}
