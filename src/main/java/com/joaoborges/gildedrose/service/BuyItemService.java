/*
 * Copyright (c) 1999-2018 Touch Tecnologia e Informatica Ltda.
 *
 * R. Gomes de Carvalho, 1666, 3o. Andar, Vila Olimpia, Sao Paulo, SP, Brasil.
 *
 * Todos os direitos reservados.
 * Este software e confidencial e de propriedade da Touch Tecnologia e Informatica Ltda. (Informacao Confidencial)
 * As informacoes contidas neste arquivo nao podem ser publicadas, e seu uso esta limitado de acordo
 * com os termos do contrato de licenca.
 */
package com.joaoborges.gildedrose.service;

import org.springframework.stereotype.Component;

import com.joaoborges.gildedrose.database.InventoryDatabase;
import com.joaoborges.gildedrose.model.Item;

import lombok.RequiredArgsConstructor;

/**
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
