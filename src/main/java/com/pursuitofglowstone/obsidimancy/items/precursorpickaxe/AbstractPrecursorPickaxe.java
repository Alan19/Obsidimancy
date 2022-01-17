package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import com.pursuitofglowstone.obsidimancy.items.IInherentlyEnchantedItem;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPrecursorPickaxe extends PickaxeItem implements IInherentlyEnchantedItem {
    protected AbstractPrecursorPickaxe(Tier pTier, Rarity rarity, int durability) {
        super(pTier, 1, -2.8F, new Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(rarity).durability(durability));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.addAll(getEnchantmentTooltips(pStack.isEnchanted()));
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantments.UNBREAKING, getUnbreaking());
        enchantments.put(Enchantments.BLOCK_FORTUNE, getFortune());
        enchantments.put(Enchantments.BLOCK_EFFICIENCY, getEfficiency());
        enchantments.put(Enchantments.SILK_TOUCH, getSilkTouch());
        return enchantments;
    }

    public abstract int getUnbreaking();

    public abstract int getFortune();

    public abstract int getEfficiency();

    public abstract int getSilkTouch();
}
