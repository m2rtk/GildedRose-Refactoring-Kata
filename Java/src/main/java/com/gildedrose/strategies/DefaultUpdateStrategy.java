package com.gildedrose.strategies;

import com.gildedrose.Item;
import com.gildedrose.UpdateStrategy;

public class DefaultUpdateStrategy implements UpdateStrategy {

    @Override
    public void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    @Override
    public void updateQuality(Item item) {
        if (item.sellIn > 0) {
            decrementQuality(item, 1);
        } else {
            decrementQuality(item, 2);
        }
    }

    protected static void incrementQuality(Item item, int amount) {
        item.quality = Math.min(item.quality + amount, 50);
    }

    protected static void decrementQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, 0);
    }

    @Override
    public boolean canUpdate(String name) {
        return true;
    }
}
