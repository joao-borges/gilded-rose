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

import static java.time.Instant.now;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.joaoborges.gildedrose.model.Item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author joaoborges
 */
@Component
public class SurgePricingService implements InitializingBean, DisposableBean {

    private Timer timer;

    @Getter(AccessLevel.PACKAGE)
    private final List<InventoryQuery> queries = new ArrayList<>();
    @Getter(AccessLevel.PACKAGE)
    private boolean surgePricingEnabled = false;

    @Setter(AccessLevel.PACKAGE)
    private long timeExpirationAmount = 1;
    @Setter(AccessLevel.PACKAGE)
    private TemporalUnit timeExpirationUnit = ChronoUnit.HOURS;

    @Override
    public void afterPropertiesSet() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               evaluateSurgePricing();
                           }
                       }
                , MILLISECONDS.convert(60, SECONDS)
                , MILLISECONDS.convert(60, SECONDS));
    }

    public void inventoryQuery() {
        queries.add(new InventoryQuery());
        verifySurgePricing();
    }

    public void calculatePrice(Item item) {
        if (surgePricingEnabled) {
            item.setPrice(Math.round(item.getPrice() * 1.1f));
        }
    }

    // below methods only exposed for unit test purposes

    void verifySurgePricing() {
        surgePricingEnabled = queries.size() > 10;
    }

    void evaluateSurgePricing() {
        Instant expirationTime = Instant.now().minus(timeExpirationAmount, timeExpirationUnit);
        synchronized (queries) {
            queries.removeAll(queries.stream().filter(i -> i.time.isBefore(expirationTime)).collect(toList()));
        }
        verifySurgePricing();
    }

    @Override
    public void destroy() {
        timer.cancel();
    }

    @Getter
    private static class InventoryQuery {
        private Instant time;

        private InventoryQuery() {
            time = now();
        }
    }
}
