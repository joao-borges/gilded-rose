package com.joaoborges.gildedrose.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joaoborges.gildedrose.application.GildedRoseApp;
import com.joaoborges.gildedrose.model.Inventory;

@SpringBootTest(classes = GildedRoseApp.class)
@RunWith(SpringRunner.class)
public class InventoryDatabaseTest {

    @Autowired
    private InventoryDatabase inventoryDatabase;

    @Test
    public void testGetInventories_expect20DefaultItems() {
        List<Inventory> inventories = inventoryDatabase.getInventories();

        assertNotNull(inventories);
        assertEquals(20, inventories.size());
        inventories.forEach(inventory -> assertTrue(inventory.getAvailableQuantity() > 0));
    }
}