package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import com.pursuitofglowstone.obsidimancy.items.IInherentlyEnchantedItem;
import com.pursuitofglowstone.obsidimancy.items.enchantment.ObsidimancyEnchantments;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
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

    @Override
    public final Map<Enchantment, Integer> getAllEnchantments(ItemStack stack) {
        Map<Enchantment, Integer> enchantments = new HashMap<>(super.getAllEnchantments(stack));
        getEnchantments().forEach((enchantment, integer) -> enchantments.compute(enchantment, (enchantment1, integer1) -> integer1 == null ? integer : integer1 + integer));
        return enchantments;
    }
}
