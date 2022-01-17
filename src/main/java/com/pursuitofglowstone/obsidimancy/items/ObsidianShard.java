package com.pursuitofglowstone.obsidimancy.items;

import com.pursuitofglowstone.obsidimancy.blocks.AttunementAltar;
import net.minecraft.ChatFormatting;
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
        final double overworldAttuneProgress = Optional.of(pStack.getOrCreateTag())
                .map(compoundTag -> compoundTag.getDouble(AttunementAltar.OVERWORLD_ATTUNE_PROGRESS))
                .orElse(0D);
        final double netherAttuneProgress = Optional.of(pStack.getOrCreateTag())
                .map(compoundTag -> compoundTag.getDouble(AttunementAltar.NETHER_ATTUNE_PROGRESS))
                .orElse(0D);
        final double endAttuneProgress = Optional.of(pStack.getOrCreateTag())
                .map(compoundTag -> compoundTag.getDouble(AttunementAltar.END_ATTUNE_PROGRESS))
                .orElse(0D);
        if (overworldAttuneProgress != 0) {
            pTooltipComponents.add(new TranslatableComponent("obsidimancy.overworld_attunement_message", String.format("%.2f", overworldAttuneProgress / 3 * 100) + "%").withStyle(ChatFormatting.BLUE));
        }
        if (netherAttuneProgress != 0) {
            pTooltipComponents.add(new TranslatableComponent("obsidimancy.nether_attunement_message", String.format("%.2f", netherAttuneProgress / 3 * 100) + "%").withStyle(ChatFormatting.RED));
        }
        if (endAttuneProgress != 0) {
            pTooltipComponents.add(new TranslatableComponent("obsidimancy.end_attunement_message", String.format("%.2f", endAttuneProgress / 3 * 100) + "%").withStyle(ChatFormatting.AQUA));
        }
    }
}
