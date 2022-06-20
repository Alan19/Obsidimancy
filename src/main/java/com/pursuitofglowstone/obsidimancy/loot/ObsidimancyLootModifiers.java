package com.pursuitofglowstone.obsidimancy.loot;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ObsidimancyLootModifiers {
    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Obsidimancy.MOD_ID);

    public static final RegistryObject<ObsidianShardsModifier.Serializer> OBSIDIAN_SHARDS = GLM.register("obsidian_shards", ObsidianShardsModifier.Serializer::new);
    public static final RegistryObject<AutoSmeltModifier.Serializer> SMELTING = GLM.register("smelting", AutoSmeltModifier.Serializer::new);
    public static final RegistryObject<SilkTouchModifier.Serializer> SILK_TOUCH = GLM.register("ender_pickaxe_silk_touch", SilkTouchModifier.Serializer::new);

    public static void register(IEventBus bus) {
        GLM.register(bus);
    }
}
