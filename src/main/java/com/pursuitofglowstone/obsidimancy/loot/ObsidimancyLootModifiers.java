package com.pursuitofglowstone.obsidimancy.loot;

import com.mojang.serialization.Codec;
import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ObsidimancyLootModifiers {
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Obsidimancy.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SHATTERING = GLM.register("shattering", ObsidianShardsModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> AUTO_SMELT = GLM.register("smelting", AutoSmeltModifier.CODEC);

    public static void register(IEventBus bus) {
        GLM.register(bus);
    }
}
