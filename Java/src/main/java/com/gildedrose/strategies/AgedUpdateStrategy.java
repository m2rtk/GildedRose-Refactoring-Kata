package com.gildedrose.strategies;

import com.gildedrose.Item;

public class AgedUpdateStrategy extends DefaultUpdateStrategy {

    @Override
    public void updateQuality(Item item) {
        if (item.sellIn > 0) {
            incrementQuality(item, 1);
        } else {
            incrementQuality(item, 2);
        }
    }

    @Override
    public boolean canUpdate(String name) {
        return "Aged Brie".equals(name);
    }
}
