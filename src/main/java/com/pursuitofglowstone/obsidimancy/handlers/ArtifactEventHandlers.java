package com.pursuitofglowstone.obsidimancy.handlers;

import com.pursuitofglowstone.obsidimancy.api.ObsidimancyCapabilities;
import com.pursuitofglowstone.obsidimancy.items.enchantment.ObsidimancyEnchantments;
import com.pursuitofglowstone.obsidimancy.items.skydivershood.AbstractSkydiversHood;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber
public class ArtifactEventHandlers {
    @SubscribeEvent
    public static void onFallDamage(LivingHurtEvent event) {
        final LivingEntity entityLiving = event.getEntity();
        for (ItemStack itemStack : entityLiving.getArmorSlots()) {
            if (itemStack.getItem() instanceof AbstractSkydiversHood hood) {
                hood.onFallDamage(event);
            }
        }
    }

    @SubscribeEvent
    public static void harvestObsidian(PlayerEvent.HarvestCheck event) {
        final int shatteringLevel = EnchantmentHelper.getEnchantmentLevel(ObsidimancyEnchantments.SHATTERING.get(), event.getEntity());
        if (shatteringLevel > 0 && event.getTargetBlock().is(Tags.Blocks.OBSIDIAN)) {
            event.setCanHarvest(true);
        }
    }

    @SubscribeEvent
    public static void harvestObsidianSpeed(PlayerEvent.BreakSpeed event) {
        final int shatteringLevel = EnchantmentHelper.getEnchantmentLevel(ObsidimancyEnchantments.SHATTERING.get(), event.getEntity());
        if (shatteringLevel > 0 && event.getState().is(Tags.Blocks.OBSIDIAN)) {
            event.setNewSpeed(event.getNewSpeed() * (shatteringLevel + 1));
        }
    }

    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        event.getEntity().getCapability(ObsidimancyCapabilities.CRITICAL_BOOST_CAPABILITY).ifPresent(criticalBoost -> {
            if (event.isVanillaCritical()) {
                event.setDamageModifier(criticalBoost.getBonusCritical() + event.getDamageModifier());
                criticalBoost.setBonusCriticalDamage(0);
            }
        });
    }
}
