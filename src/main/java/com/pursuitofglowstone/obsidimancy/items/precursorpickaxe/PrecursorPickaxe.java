package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PrecursorPickaxe extends AbstractPrecursorPickaxe {

    public PrecursorPickaxe() {
        super(Tiers.IRON, Rarity.COMMON, 256);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack itemStack, BlockState blockState) {
        return blockState.getBlock() == Blocks.OBSIDIAN ? speed * 2 : super.getDestroySpeed(itemStack, blockState);
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
