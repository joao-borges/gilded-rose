
package com.joaoborges.gildedrose.model;

import lombok.Builder;
import lombok.Data;

/**
 * Item representation.
 *
 * @author joaoborges
 */
@Data
@Builder
public class Item {

    private String name;
    private String description;
    private int price;

}
