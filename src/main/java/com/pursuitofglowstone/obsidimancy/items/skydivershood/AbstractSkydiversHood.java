package com.pursuitofglowstone.obsidimancy.items.skydivershood;

import com.pursuitofglowstone.obsidimancy.items.IInherentlyEnchantedItem;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;

public abstract class AbstractSkydiversHood extends ArmorItem implements IInherentlyEnchantedItem {

    protected AbstractSkydiversHood(ArmorMaterial pMaterial, Rarity rarity, int durability) {
        super(pMaterial, EquipmentSlot.HEAD, new Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(rarity).durability(durability));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.addAll(getEnchantmentTooltips(pStack.isEnchanted()));
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        return Map.of(Enchantments.FALL_PROTECTION, getFeatherFallingLevel());
    }

    public abstract int getFeatherFallingLevel();

    public abstract void onFallDamage(LivingHurtEvent event);
}
