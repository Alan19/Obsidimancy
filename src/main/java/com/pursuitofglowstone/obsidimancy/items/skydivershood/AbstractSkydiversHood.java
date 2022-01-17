package com.pursuitofglowstone.obsidimancy.items.skydivershood;

import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public abstract class AbstractSkydiversHood extends ArmorItem {

    protected AbstractSkydiversHood(ArmorMaterial pMaterial, Rarity rarity, int durability) {
        super(pMaterial, EquipmentSlot.HEAD, new Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(rarity).durability(durability));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Enchantments.FALL_PROTECTION.getFullname(getFeatherFallingLevel()));
        pTooltipComponents.add(new TranslatableComponent("obsidimancy.inherent_enchantment").setStyle(ObsidimancyItems.INHERENT_ENCHANT_STYLE));
    }

    public abstract int getFeatherFallingLevel();

    public abstract void onFallDamage(LivingHurtEvent event);
}
