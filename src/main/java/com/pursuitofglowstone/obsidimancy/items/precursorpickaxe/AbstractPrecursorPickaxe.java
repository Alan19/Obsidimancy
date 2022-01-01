package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;

public abstract class AbstractPrecursorPickaxe extends PickaxeItem {
    protected AbstractPrecursorPickaxe(Tier pTier, Rarity rarity, int durability) {
        super(pTier, 1, -2.8F, new Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(rarity).durability(durability));
    }

    public abstract int getUnbreaking();

    public abstract int getFortune();

    public abstract int getEfficiency();

    public abstract int getSilkTouch();
}
