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
package com.joaoborges.gildedrose.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joaoborges.gildedrose.database.InventoryDatabase;
import com.joaoborges.gildedrose.database.ItemDatabase;
import com.joaoborges.gildedrose.model.Inventory;

import lombok.RequiredArgsConstructor;

/**
 * @author joaoborges
 */
@RestController
@RequestMapping("/api/inventory")
@ResponseBody
@RequiredArgsConstructor
@Validated
public class RetrieveInventoryController {

    private final InventoryDatabase inventoryDatabase;
    private final ItemDatabase itemDatabase;

    @GetMapping("")
    public ResponseEntity<List<Inventory>> retrieveAvailableInventory() {
        return ResponseEntity.ok(inventoryDatabase.getInventories());
    }
}

