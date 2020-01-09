package com.joaoborges.gildedrose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joaoborges.gildedrose.application.GildedRoseApp;
import com.joaoborges.gildedrose.database.InventoryDatabase;
import com.joaoborges.gildedrose.model.Inventory;

@SpringBootTest(classes = GildedRoseApp.class)
@RunWith(SpringRunner.class)
public class RetrieveInventoryServiceTest {

    @Mock
    private SurgePricingService surgePricingService;
    @Mock
    private InventoryDatabase inventoryDatabase;

    @InjectMocks
    private RetrieveInventoryService retrieveInventoryService;

    @Test
    public void retrieve_expectOperationRegistered() {
        when(inventoryDatabase.getInventories()).thenReturn(Collections.singletonList(Inventory.builder().build()));

        List<Inventory> inventories = retrieveInventoryService.retrieve();

        assertNotNull(inventories);
        assertEquals(1, inventories.size());

        // verify that retrieval triggered surge pricing to log the request
        verify(surgePricingService, times(1)).inventoryQuery();
    }
}