package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import com.pursuitofglowstone.obsidimancy.items.IInherentlyEnchantedItem;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import com.pursuitofglowstone.obsidimancy.items.enchantment.ObsidimancyEnchantments;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPrecursorPickaxe extends PickaxeItem implements IInherentlyEnchantedItem {


    public AbstractPrecursorPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.addAll(getEnchantmentTooltips(pStack.isEnchanted()));
    }

    @Override
    public int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        return super.getEnchantmentLevel(stack, enchantment) + getEnchantments().getOrDefault(enchantment, 0);
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        return Map.of(ObsidimancyEnchantments.SHATTERING.get(), 1);
    }

}
