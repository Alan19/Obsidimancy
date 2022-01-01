package com.pursuitofglowstone.obsidimancy.handlers;

import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import com.pursuitofglowstone.obsidimancy.items.skydivershood.AbstractSkydiversHood;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ArtifactEventHandlers {
    @SubscribeEvent
    public static void onFallDamage(LivingHurtEvent event) {
        final LivingEntity entityLiving = event.getEntityLiving();
        for (ItemStack itemStack : entityLiving.getArmorSlots()) {
            if (itemStack.getItem() instanceof AbstractSkydiversHood hood) {
                hood.onFallDamage(event);
            }
        }
    }

    @SubscribeEvent
    public static void harvestObsidian(PlayerEvent.HarvestCheck event) {
        if (event.getPlayer().getMainHandItem().getItem() == ObsidimancyItems.PRECURSOR_PICKAXE.get() || event.getTargetBlock().getBlock() == Blocks.OBSIDIAN) {
            event.setCanHarvest(true);
        }
    }
}
