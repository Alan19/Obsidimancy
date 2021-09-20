package com.namepending.obsidimancy.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PrecursorPickaxe extends PickaxeItem {
    public PrecursorPickaxe() {
        super(ItemTier.IRON, 1, -2.8F, (new Item.Properties()).tab(ObsidimancyItems.TAB_OBSIDIMANCY));
    }

    @Override
    public int getHarvestLevel(@Nonnull ItemStack stack, @Nonnull ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        return (blockState != null && blockState.getBlock() == Blocks.OBSIDIAN) ? 3 : super.getHarvestLevel(stack, tool, player, blockState);
    }
}
