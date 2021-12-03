package com.pursuitofglowstone.obsidimancy.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;

public class PrecursorPickaxe extends PickaxeItem {
    public PrecursorPickaxe() {
        super(Tiers.IRON, 1, -2.8F, (new Item.Properties()).tab(ObsidimancyItems.TAB_OBSIDIMANCY));
    }

    // TODO Reimplement tier increasing effect
}
