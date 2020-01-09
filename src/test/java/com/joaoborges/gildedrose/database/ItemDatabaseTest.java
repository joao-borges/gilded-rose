package com.joaoborges.gildedrose.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joaoborges.gildedrose.application.GildedRoseApp;
import com.joaoborges.gildedrose.model.Item;

@SpringBootTest(classes = GildedRoseApp.class)
@RunWith(SpringRunner.class)
public class ItemDatabaseTest {

    @Autowired
    private ItemDatabase itemDatabase;

    @Test
    public void testGetItems_expect20DefaultItems() {
        List<Item> items = itemDatabase.getItems();

        assertNotNull(items);
        assertEquals(20, items.size());
    }
}