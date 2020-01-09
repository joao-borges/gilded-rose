package com.joaoborges.gildedrose.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.joaoborges.gildedrose.application.GildedRoseApp;

@SpringBootTest(classes = GildedRoseApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RetrieveInventoryControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.context).addFilters(springSecurityFilterChain).build();
    }

    @Test
    public void retrieveAvailableInventory_unauthenticated_expectInventoryReturned() throws Exception {
        // run a request unauthenticated to the inventory endpoint and expect the inventory is returned with all 20 items.
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/inventory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(20)));

    }
}