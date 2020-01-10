
package com.joaoborges.gildedrose.model;

import lombok.Builder;
import lombok.Data;

/**
 * Represents an item in the current inventory, holding their available quantity.
 *
 * @author joaoborges
 */
@Data
@Builder
public class Inventory {

    private Item item;
    private int availableQuantity;

    public Item itemSold(int quantity) {
        availableQuantity-= quantity;
        return Item.builder().name(item.getName()).description(item.getDescription()).price(item.getPrice()).build();
    }

}
