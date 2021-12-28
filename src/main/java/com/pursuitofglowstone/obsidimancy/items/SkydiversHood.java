package com.pursuitofglowstone.obsidimancy.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SkydiversHood extends ArmorItem {

    public static final float DAMAGE_MULTIPLIER = .85f;

    public SkydiversHood() {
        super(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(Rarity.UNCOMMON));
    }

    // TODO Make this affected by configs
    public float getDamageMultiplier() {
        return DAMAGE_MULTIPLIER;
    }
}
