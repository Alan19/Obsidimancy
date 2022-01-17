package com.pursuitofglowstone.obsidimancy.handlers;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.api.criticalboost.CriticalBoostProvider;
import com.pursuitofglowstone.obsidimancy.api.criticalboost.ICriticalBoost;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Obsidimancy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEventHandler {
    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(ICriticalBoost.class);
    }

    @SubscribeEvent
    public static void attachToEntity(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(CriticalBoostProvider.KEY, new CriticalBoostProvider());
        }
    }
}
