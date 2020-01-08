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

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.joaoborges.gildedrose.model.Item;

import lombok.Getter;

/**
 * @author joaoborges
 */
@Component
@Getter
public class ItemDatabase implements InitializingBean {

    private List<Item> items;

    @Override
    public void afterPropertiesSet() {
        items = List.of(
                Item.builder().name("Item 1").description("This is a very nice item").price(10).build(),
                Item.builder().name("Item 2").description("This is a very nice item").price(20).build(),
                Item.builder().name("Item 3").description("This is a very nice item").price(30).build(),
                Item.builder().name("Item 4").description("This is a very nice item").price(40).build(),
                Item.builder().name("Item 5").description("This is a very nice item").price(50).build(),
                Item.builder().name("Item 6").description("This is a very nice item").price(60).build(),
                Item.builder().name("Item 7").description("This is a very nice item").price(70).build(),
                Item.builder().name("Item 8").description("This is a very nice item").price(80).build(),
                Item.builder().name("Item 9").description("This is a very nice item").price(90).build(),
                Item.builder().name("Item 10").description("This is a very nice item").price(100).build()
        );
    }

}
