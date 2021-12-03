package com.namepending.obsidimancy.handlers;

import com.namepending.obsidimancy.items.SkydiversHood;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ArtifactEventHandlers {
    @SubscribeEvent
    public static void onFallDamage(LivingFallEvent event) {
        final LivingEntity entityLiving = event.getEntityLiving();
        for (ItemStack itemStack : entityLiving.getArmorSlots()) {
            if (itemStack.getItem() instanceof SkydiversHood) {
                event.setDamageMultiplier(event.getDamageMultiplier() * ((SkydiversHood) itemStack.getItem()).getDamageMultiplier());
            }
        }
    }
}
