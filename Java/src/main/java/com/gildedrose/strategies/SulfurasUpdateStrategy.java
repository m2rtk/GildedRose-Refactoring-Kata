package com.gildedrose.strategies;

import com.gildedrose.Item;
import com.gildedrose.UpdateStrategy;

public class SulfurasUpdateStrategy implements UpdateStrategy {

    @Override
    public void updateSellIn(Item item) {

    }

    @Override
    public void updateQuality(Item item) {

    }

    @Override
    public boolean canUpdate(String name) {
        return "Sulfuras, Hand of Ragnaros".equals(name);
    }
}
