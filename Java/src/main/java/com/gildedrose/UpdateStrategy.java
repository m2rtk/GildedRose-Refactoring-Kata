package com.gildedrose;

public interface UpdateStrategy {

    void updateSellIn(Item item);

    void updateQuality(Item item);

    boolean canUpdate(String name);

}
