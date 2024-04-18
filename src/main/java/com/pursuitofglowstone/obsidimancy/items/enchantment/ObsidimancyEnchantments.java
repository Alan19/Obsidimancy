package com.pursuitofglowstone.obsidimancy.items.enchantment;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ObsidimancyEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, Obsidimancy.MOD_ID);
    public static final Supplier<Enchantment> SHATTERING = ENCHANTMENTS.register("shattering", ShatteringEnchantment::new);
    public static final Supplier<Enchantment> AUTO_SMELT = ENCHANTMENTS.register("auto_smelting", AutoSmeltEnchantment::new);

    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }


}
