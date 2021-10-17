package com.namepending.obsidimancy.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SkydiversHood extends ArmorItem {
    public SkydiversHood() {
        super(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD, new Item.Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(Rarity.UNCOMMON));
    }

    // TODO Make this affected by configs
    public float getDamageMultiplier() {
        return .1f;
    }
}
