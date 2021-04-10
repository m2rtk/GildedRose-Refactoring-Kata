package com.gildedrose;

import com.gildedrose.strategies.*;

import java.util.List;

@SuppressWarnings("WeakerAccess")
final class GildedRose {

    private static final UpdateStrategy DEFAULT_STRATEGY = new DefaultUpdateStrategy();
    private static final List<UpdateStrategy> STRATEGIES = List.of(
            new TicketUpdateStrategy(),
            new ConjuredUpdateStrategy(),
            new SulfurasUpdateStrategy(),
            new AgedUpdateStrategy()
    );

    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            final UpdateStrategy strategy = strategyFor(item);

            strategy.updateQuality(item);
            strategy.updateSellIn(item);
        }
    }

    private static UpdateStrategy strategyFor(Item item) {
        for (UpdateStrategy strategy : STRATEGIES) {
            if (strategy.canUpdate(item.name)) {
                return strategy;
            }
        }

        return DEFAULT_STRATEGY;
    }
}