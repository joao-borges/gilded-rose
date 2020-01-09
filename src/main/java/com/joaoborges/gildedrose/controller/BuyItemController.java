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

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joaoborges.gildedrose.model.Item;
import com.joaoborges.gildedrose.service.BuyItemService;

import lombok.RequiredArgsConstructor;

/**
 * Controller responsible to handle the buying items operation, containing the required security checks.
 *
 * @author joaoborges
 */
@RestController
@RequestMapping("/api/buy")
@ResponseBody
@RequiredArgsConstructor
@Validated
public class BuyItemController {

    private final BuyItemService buyItemService;

    @GetMapping("/{itemName}")
    @PreAuthorize("#oauth2.hasScope('buyer')")
    public ResponseEntity<Item> buy(@PathVariable String itemName) {
        Item item = buyItemService.buyItem(itemName);
        return ResponseEntity.ok(item);
    }
}
