package com.pursuitofglowstone.obsidimancy.items.skydivershood;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

public class OverworldSkydiversHood extends AbstractSkydiversHood {
    public OverworldSkydiversHood() {
        super(ArmorMaterials.GOLD, Rarity.UNCOMMON, ArmorMaterials.GOLD.getDurabilityForType(Type.HELMET) * 2);
    }

    @Override
    public int getFeatherFallingLevel() {
        return 2;
    }

    @Override
    public void onFallDamage(LivingHurtEvent event) {
        // No bonus on fall event
    }
}
