package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.end;

import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.AbstractPrecursorPickaxe;
import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.PrecursorPickaxe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Map;

public class EndPrecursorPickaxe extends PrecursorPickaxe {

    public EndPrecursorPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.BLOCK_FORTUNE && enchantment != Enchantments.SILK_TOUCH && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        final Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(book);
        return !enchantments.containsKey(Enchantments.SILK_TOUCH) && !enchantments.containsKey(Enchantments.BLOCK_FORTUNE) && super.isBookEnchantable(stack, book);
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        Map<Enchantment, Integer> enchantments = super.getEnchantments();
        enchantments.put(Enchantments.SILK_TOUCH, 1);
        return enchantments;
    }
}
