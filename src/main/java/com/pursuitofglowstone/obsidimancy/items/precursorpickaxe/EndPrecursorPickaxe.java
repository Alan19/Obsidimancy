package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Map;

public class EndPrecursorPickaxe extends AttunedPrecursorPickaxe {

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
        return 1;
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
}
