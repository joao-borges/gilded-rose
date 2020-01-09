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

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.joaoborges.gildedrose.model.Item;

import lombok.Getter;

/**
 * Holds all possible items available to be sold.
 *
 * @author joaoborges
 */
@Component
@Getter
public class ItemDatabase implements InitializingBean {

    private List<Item> items;

    @Override
    public void afterPropertiesSet() {
        items = IntStream.range(0, 20)
                .mapToObj(index -> Item.builder().name("Item" + (index + 1)).description("Item type " + (index + 1)).price(nextInt(0, 100)).build())
                .collect(toList());
    }

}
