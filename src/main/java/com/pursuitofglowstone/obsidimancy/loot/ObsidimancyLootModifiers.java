package com.pursuitofglowstone.obsidimancy.loot;

import com.mojang.serialization.Codec;
import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ObsidimancyLootModifiers {
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Obsidimancy.MOD_ID);

    public static final Supplier<Codec<? extends IGlobalLootModifier>> SHATTERING = GLM.register("shattering", ObsidianShardsModifier.CODEC);
    public static final Supplier<Codec<? extends IGlobalLootModifier>> AUTO_SMELT = GLM.register("smelting", AutoSmeltModifier.CODEC);

    public static void register(IEventBus bus) {
        GLM.register(bus);
    }
}
