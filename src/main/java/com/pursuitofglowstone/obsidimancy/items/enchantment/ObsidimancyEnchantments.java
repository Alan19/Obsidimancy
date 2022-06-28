package com.pursuitofglowstone.obsidimancy.items.enchantment;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ObsidimancyEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Obsidimancy.MOD_ID);
    public static final RegistryObject<Enchantment> SHATTERING = ENCHANTMENTS.register("shattering", ShatteringEnchantment::new);

    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }


}
