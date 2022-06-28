package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;

public class PrecursorPickaxe extends AbstractPrecursorPickaxe {

    public PrecursorPickaxe() {
        super(Tiers.IRON, Rarity.COMMON, 256);
    }

    @Override
    public int getUnbreaking() {
        return 0;
    }

    @Override
    public int getFortune() {
        return 0;
    }

    @Override
    public int getEfficiency() {
        return 0;
    }

    @Override
    public int getSilkTouch() {
        return 0;
    }
}
