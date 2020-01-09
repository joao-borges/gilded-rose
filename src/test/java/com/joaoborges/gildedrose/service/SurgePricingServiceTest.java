package com.joaoborges.gildedrose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joaoborges.gildedrose.application.GildedRoseApp;
import com.joaoborges.gildedrose.model.Item;

@SpringBootTest(classes = GildedRoseApp.class)
@RunWith(SpringRunner.class)
public class SurgePricingServiceTest {

    @Autowired
    private SurgePricingService surgePricingService;

    @Before
    public void before() {
        // clear scenario before all tests
        surgePricingService.getQueries().clear();
        surgePricingService.verifySurgePricing();
    }

    @Test
    public void inventoryQueryTest_expectQueryRecorded_thresholdNotReached() {
        surgePricingService.inventoryQuery();

        assertEquals(1, surgePricingService.getQueries().size());
        assertFalse(surgePricingService.isSurgePricingEnabled());
    }

    @Test
    public void inventoryQueryTest_thresholdReached_expectSurgePricingEnabled() {
        IntStream.range(0,10).forEach(i -> surgePricingService.inventoryQuery());

        assertEquals(10, surgePricingService.getQueries().size());
        assertFalse(surgePricingService.isSurgePricingEnabled());

        surgePricingService.inventoryQuery();
        assertEquals(11, surgePricingService.getQueries().size());
        assertTrue(surgePricingService.isSurgePricingEnabled());
    }

    @Test
    public void inventoryQueryTest_thresholdReached_intervalExpired_expectSurgePricingDisabled() {
        // reconfigure the surge pricing to suitable values for unit testing (expire every two seconds)
        surgePricingService.setTimeExpirationAmount(500);
        surgePricingService.setTimeExpirationUnit(ChronoUnit.MILLIS);
        surgePricingService.afterPropertiesSet();

        IntStream.range(0,11).forEach(i -> surgePricingService.inventoryQuery());

        assertEquals(11, surgePricingService.getQueries().size());
        assertTrue(surgePricingService.isSurgePricingEnabled());

        // wait for 500ms to check surge pricing was disabled due to no requests
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //
        }

        surgePricingService.evaluateSurgePricing();
        assertFalse(surgePricingService.isSurgePricingEnabled());
    }

    @Test
    public void calculatePrice_surgePricingDisabled() {
        Item item = Item.builder().price(10).build();
        surgePricingService.calculatePrice(item);

        assertEquals(10, item.getPrice());
    }

    @Test
    public void calculatePrice_surgePricingEnabled() {
        IntStream.range(0,11).forEach(i -> surgePricingService.inventoryQuery());

        Item item = Item.builder().price(10).build();
        surgePricingService.calculatePrice(item);

        assertEquals(11, item.getPrice());
    }
}