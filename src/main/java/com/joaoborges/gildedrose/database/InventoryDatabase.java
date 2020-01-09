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
package com.joaoborges.gildedrose.database;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.joaoborges.gildedrose.model.Inventory;
import com.joaoborges.gildedrose.model.Item;

import lombok.Getter;

/**
 * @author joaoborges
 */
@Component
public class InventoryDatabase implements InitializingBean {

    @Autowired
    private ItemDatabase itemDatabase;

    @Getter
    private List<Inventory> inventories;

    @Override
    public void afterPropertiesSet() {
        inventories = itemDatabase.getItems().stream().map(item -> Inventory.builder().item(item).availableQuantity(nextInt(1, 51)).build()).collect(toUnmodifiableList());
    }

    public synchronized Item getItemIfAvailable(String itemName) {
        Optional<Inventory> inventory = inventories.stream()
                .filter(i -> i.getItem().getName().equals(itemName))
                .findFirst();

        if (inventory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item " + itemName + " does not exist");
        }

        if (inventory.get().getAvailableQuantity() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item " + itemName + " is out of stock");
        }

        return inventory.get().itemSold(1);
    }
}
