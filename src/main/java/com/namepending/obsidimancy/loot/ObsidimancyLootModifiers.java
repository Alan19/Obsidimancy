package com.namepending.obsidimancy.loot;

import com.namepending.obsidimancy.Obsidimancy;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ObsidimancyLootModifiers {
    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Obsidimancy.MOD_ID);

    public static final RegistryObject<ObsidianShardsModifier.Serializer> OBSIDIAN_SHARDS = GLM.register("obsidian_shards", ObsidianShardsModifier.Serializer::new);

    public static void register(IEventBus bus) {
        GLM.register(bus);
    }
}
