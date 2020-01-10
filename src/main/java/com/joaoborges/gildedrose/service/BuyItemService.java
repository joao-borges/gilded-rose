
package com.joaoborges.gildedrose.service;

import org.springframework.stereotype.Component;

import com.joaoborges.gildedrose.database.InventoryDatabase;
import com.joaoborges.gildedrose.model.Item;

import lombok.RequiredArgsConstructor;

/**
 * Service handling the item purchase operation.
 *
 * @author joaoborges
 */
@Component
@RequiredArgsConstructor
public class BuyItemService {

    private final InventoryDatabase inventoryDatabase;
    private final SurgePricingService surgePricingService;

    public Item buyItem(String itemName) {
        Item item = inventoryDatabase.getItemIfAvailable(itemName);
        surgePricingService.calculatePrice(item);
        return item;
    }
}
