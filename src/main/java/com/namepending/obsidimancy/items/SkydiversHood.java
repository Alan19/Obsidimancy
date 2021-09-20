package com.namepending.obsidimancy.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

public class SkydiversHood extends ArmorItem {
    public SkydiversHood() {
        super(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD, new Item.Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY).rarity(Rarity.UNCOMMON));
    }
}
