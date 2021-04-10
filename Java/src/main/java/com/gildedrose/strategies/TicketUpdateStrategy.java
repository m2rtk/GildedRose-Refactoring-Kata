package com.gildedrose.strategies;

import com.gildedrose.Item;

public class TicketUpdateStrategy extends DefaultUpdateStrategy {

    @Override
    public void updateQuality(Item item) {
        if (item.sellIn < 1) {
            item.quality = 0;
        } else if (item.sellIn < 6) {
            incrementQuality(item, 3);
        } else if (item.sellIn < 11) {
            incrementQuality(item, 2);
        } else {
            incrementQuality(item, 1);
        }
    }

    @Override
    public boolean canUpdate(String name) {
        return name.startsWith("Backstage passes");
    }
}
