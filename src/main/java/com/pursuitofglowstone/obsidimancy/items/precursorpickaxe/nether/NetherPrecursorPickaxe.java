package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.nether;

import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.AbstractPrecursorPickaxe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class NetherPrecursorPickaxe extends AbstractPrecursorPickaxe {

    public NetherPrecursorPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.SILK_TOUCH && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        final Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(book);
        return !enchantments.containsKey(Enchantments.SILK_TOUCH) && super.isBookEnchantable(stack, book);
    }
}
