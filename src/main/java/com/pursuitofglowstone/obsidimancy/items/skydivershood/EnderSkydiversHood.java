package com.pursuitofglowstone.obsidimancy.items.skydivershood;

import com.pursuitofglowstone.obsidimancy.api.ObsidimancyCapabilities;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EnderSkydiversHood extends AbstractSkydiversHood {
    public EnderSkydiversHood() {
        super(ArmorMaterials.GOLD, Rarity.UNCOMMON, ArmorMaterials.GOLD.getDurabilityForSlot(EquipmentSlot.HEAD) * 2);
    }

    @Override
    public int getFeatherFallingLevel() {
        return 1;
    }

    @Override
    public void onFallDamage(LivingHurtEvent event) {
        event.getEntity().getCapability(ObsidimancyCapabilities.CRITICAL_BOOST_CAPABILITY).ifPresent(criticalBoost -> criticalBoost.setBonusCriticalDamage(event.getAmount() / 20));
    }
}
