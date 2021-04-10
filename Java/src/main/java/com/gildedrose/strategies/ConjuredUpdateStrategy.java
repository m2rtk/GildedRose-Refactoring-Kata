package com.gildedrose.strategies;

import com.gildedrose.Item;

public class ConjuredUpdateStrategy extends DefaultUpdateStrategy {

    @Override
    public void updateQuality(Item item) {
        if (item.sellIn > 0) {
            decrementQuality(item, 2);
        } else {
            decrementQuality(item, 4);
        }
    }

    @Override
    public boolean canUpdate(String name) {
        return name.startsWith("Conjured");
    }
}
