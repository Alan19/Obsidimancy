package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

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

    // TODO Add a GLM to make this drop stuff properly
    @Override
    public int getSilkTouch() {
        return 1;
    }

    // TODO Check if this restriction applies to enchanted books
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.BLOCK_FORTUNE && enchantment != Enchantments.SILK_TOUCH && super.canApplyAtEnchantingTable(stack, enchantment);
    }
}
