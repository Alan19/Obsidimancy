package com.pursuitofglowstone.obsidimancy.items.skydivershood;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SkydiversHood extends AbstractSkydiversHood {

    public SkydiversHood() {
        super(ArmorMaterials.LEATHER, Rarity.COMMON, ArmorMaterials.LEATHER.getDurabilityForSlot(EquipmentSlot.HEAD) * 2);
    }

    @Override
    public int getFeatherFallingLevel() {
        return 1;
    }

    @Override
    public void onFallDamage(LivingHurtEvent event) {

    }
}
