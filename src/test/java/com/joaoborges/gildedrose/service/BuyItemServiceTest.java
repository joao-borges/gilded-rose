package com.joaoborges.gildedrose.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joaoborges.gildedrose.application.GildedRoseApp;
import com.joaoborges.gildedrose.database.InventoryDatabase;
import com.joaoborges.gildedrose.model.Item;

@SpringBootTest(classes = GildedRoseApp.class)
@RunWith(SpringRunner.class)
public class BuyItemServiceTest {

    @Mock
    private SurgePricingService surgePricingService;
    @Mock
    private InventoryDatabase inventoryDatabase;

    @InjectMocks
    private BuyItemService buyItemService;

    @Test
    public void buyItem_expectSurgePricingCalculated() {
        when(inventoryDatabase.getItemIfAvailable(any())).thenReturn(Item.builder().build());

        Item item = buyItemService.buyItem("Item");
        assertNotNull(item);

        verify(surgePricingService, times(1)).calculatePrice(eq(item));
    }
}