
package com.joaoborges.gildedrose.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joaoborges.gildedrose.model.Inventory;
import com.joaoborges.gildedrose.service.RetrieveInventoryService;

import lombok.RequiredArgsConstructor;

/**
 * Unsecured controller responsible to retrieve the current inventory of items.
 *
 * @author joaoborges
 */
@RestController
@RequestMapping("/api/inventory")
@ResponseBody
@RequiredArgsConstructor
@Validated
public class RetrieveInventoryController {

    private final RetrieveInventoryService retrieveInventoryService;

    @GetMapping("")
    public ResponseEntity<List<Inventory>> retrieveAvailableInventory() {
        return ResponseEntity.ok(retrieveInventoryService.retrieve());
    }

}

