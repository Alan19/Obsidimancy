package com.pursuitofglowstone.obsidimancy.items.skydivershood;

import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class AbstractSkydiversHood extends ArmorItem {
    protected AbstractSkydiversHood(ArmorMaterial pMaterial, Rarity rarity, int durability) {
        super(pMaterial, EquipmentSlot.HEAD, new Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(rarity).durability(durability));
    }

    public abstract int getFeatherFallingLevel();

    public abstract void onFallDamage(LivingHurtEvent event);
}
