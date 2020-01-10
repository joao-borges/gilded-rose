
package com.joaoborges.gildedrose.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.joaoborges.gildedrose.database.InventoryDatabase;
import com.joaoborges.gildedrose.model.Inventory;

import lombok.RequiredArgsConstructor;

/**
 * Inventory management.
 *
 * @author joaoborges
 */
@Component
@RequiredArgsConstructor
public class RetrieveInventoryService {

    private final InventoryDatabase inventoryDatabase;
    private final SurgePricingService surgePricingService;

    public List<Inventory> retrieve() {
        surgePricingService.inventoryQuery();
        return inventoryDatabase.getInventories();
    }
}
