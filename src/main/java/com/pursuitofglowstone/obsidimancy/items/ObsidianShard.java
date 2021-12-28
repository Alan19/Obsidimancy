package com.pursuitofglowstone.obsidimancy.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

public class ObsidianShard extends Item {
    public ObsidianShard() {
        super(new Item.Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        final int overworldAttuneProgress = Optional.of(pStack.getOrCreateTag())
                .map(compoundTag -> compoundTag.getInt("OverworldAttuneProgress"))
                .orElse(0);
        pTooltipComponents.add(new TranslatableComponent("obsidimancy.overworld_attunement_message", String.format("%.2f", (float) overworldAttuneProgress / 3 * 100)));
    }
}
