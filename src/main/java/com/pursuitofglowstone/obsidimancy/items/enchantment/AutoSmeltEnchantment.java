package com.pursuitofglowstone.obsidimancy.items.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class AutoSmeltEnchantment extends Enchantment {
    protected AutoSmeltEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof DiggerItem && super.canEnchant(pStack) && !pStack.getAllEnchantments().containsKey(ObsidimancyEnchantments.AUTO_SMELT.get()) && !pStack.getAllEnchantments().containsKey(Enchantments.SILK_TOUCH);
    }

}
