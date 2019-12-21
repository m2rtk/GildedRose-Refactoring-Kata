package com.gildedrose;

@SuppressWarnings("WeakerAccess")
class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQuality(item);
        }
    }

    private void updateQuality(Item item) {
        final UpdateStrategy strategy = UpdateStrategy.forItem(item.name);

        strategy.updateQuality(item);
        strategy.updateSellIn(item);
    }

    private interface UpdateStrategy {
        void updateSellIn(Item item);

        void updateQuality(Item item);

        static UpdateStrategy forItem(String name) {
            if (name.startsWith("Backstage passes")) {
                return new TicketUpdateStrategy();
            } else if (name.startsWith("Conjured")) {
                return new ConjuredUpdateStrategy();
            }

            switch (name) {
                case "Sulfuras, Hand of Ragnaros":
                    return new SulfurasUpdateStrategy();
                case "Aged Brie":
                    return new AgedUpdateStrategy();
                default:
                    return new DefaultUpdateStrategy();
            }
        }
    }

    private static class DefaultUpdateStrategy implements UpdateStrategy {
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
            int newQuality = item.quality + amount;
            if (newQuality > 50) {
                item.quality = 50;
            } else {
                item.quality = newQuality;
            }
        }

        protected static void decrementQuality(Item item, int amount) {
            int newQuality = item.quality - amount;
            if (newQuality < 0) {
                item.quality = 0;
            } else {
                item.quality = newQuality;
            }
        }
    }

    private static class SulfurasUpdateStrategy implements UpdateStrategy {
        @Override
        public void updateSellIn(Item item) {

        }

        @Override
        public void updateQuality(Item item) {

        }
    }

    private static class ConjuredUpdateStrategy extends DefaultUpdateStrategy {
        @Override
        public void updateQuality(Item item) {
            if (item.sellIn > 0) {
                decrementQuality(item, 2);
            } else {
                decrementQuality(item, 4);
            }
        }
    }

    private static class TicketUpdateStrategy extends DefaultUpdateStrategy {
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
    }

    private static class AgedUpdateStrategy extends DefaultUpdateStrategy {

        @Override
        public void updateQuality(Item item) {
            if (item.sellIn > 0) {
                incrementQuality(item, 1);
            } else {
                incrementQuality(item, 2);
            }
        }
    }

}