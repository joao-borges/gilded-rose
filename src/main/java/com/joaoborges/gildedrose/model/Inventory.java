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
package com.joaoborges.gildedrose.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author joaoborges
 */
@Data
@Builder
public class Inventory {

    private Item item;
    private int availableQuantity;

    public Item itemSold(int quantity) {
        availableQuantity-= quantity;
        return Item.builder().name(item.getName()).description(item.getDescription()).price(item.getPrice()).build();
    }

}
