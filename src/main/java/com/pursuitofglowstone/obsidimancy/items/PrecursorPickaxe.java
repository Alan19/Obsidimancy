package com.pursuitofglowstone.obsidimancy.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PrecursorPickaxe extends PickaxeItem {
    public PrecursorPickaxe() {
        super(Tiers.IRON, 1, -2.8F, (new Item.Properties()).tab(ObsidimancyItems.TAB_OBSIDIMANCY));
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack itemStack, BlockState blockState) {
        return blockState.getBlock() == Blocks.OBSIDIAN ? speed : super.getDestroySpeed(itemStack, blockState);
    }
}
