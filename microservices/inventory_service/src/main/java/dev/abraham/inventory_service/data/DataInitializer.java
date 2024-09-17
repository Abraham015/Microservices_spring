package dev.abraham.inventory_service.data;

import dev.abraham.inventory_service.model.Inventory;
import dev.abraham.inventory_service.repository.InventoryRepository;
import dev.abraham.inventory_service.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createStock();
    }

    private void createStock() {
        for (int i = 0; i < 5; i++) {
            String sku="iphone_1"+i;
            if(inventoryService.getInventorybyskuCode(sku)==null){
                Inventory inventory = new Inventory();
                inventory.setSkuCode("iphone_1"+i);
                inventory.setQuantity(100);
                inventoryRepository.save(inventory);
            }
        }
    }
}
