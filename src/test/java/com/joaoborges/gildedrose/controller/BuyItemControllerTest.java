package com.joaoborges.gildedrose.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.joaoborges.gildedrose.application.GildedRoseApp;
import com.joaoborges.gildedrose.database.InventoryDatabase;

@SpringBootTest(classes = GildedRoseApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BuyItemControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private Filter springSecurityFilterChain;
    @Autowired
    private InventoryDatabase inventoryDatabase;
    @Autowired
    private TokenStore tokenStore;

    private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.context).addFilters(springSecurityFilterChain).build();
    }

    @Test
    public void buyUnauthenticated_ExpectUnauthorized() throws Exception {
        // run a request unauthenticated to the buy endpoint and expect response unauthorized status
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/buy/Item1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void buyAuthenticated_invalidToken_expectUnauthorized() throws Exception {
        // run a request authenticated to the buy endpoint with an invalid token and expect response unauthorized status
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/buy/Item1")
                        .header(HttpHeaders.AUTHORIZATION, "invalid-token"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void buyAuthenticated_correctToken_expectOk() throws Exception {
        // set up the token
        OAuth2Request oAuth2Request = new OAuth2Request(Map.of(), "buyer-client", Collections.emptyList(), true, Set.of("buyer"), null, null, null, null);
        OAuth2Authentication authentication = new OAuth2Authentication(oAuth2Request, null);
        OAuth2AccessToken token = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());

        tokenStore.storeAccessToken(token, authentication);

        // run a request authenticated to the buy endpoint with correct token and expect response with the item
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/buy/Item1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getValue()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Item1")));;

    }
}