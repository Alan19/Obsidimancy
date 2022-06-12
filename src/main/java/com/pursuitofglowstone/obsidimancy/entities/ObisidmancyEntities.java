package com.pursuitofglowstone.obsidimancy.entities;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ObisidmancyEntities {
    public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Obsidimancy.MOD_ID);
    public static RegistryObject<EntityType<GuardianTotem>> GUARDIAN_TOTEM = createEntity("guardian_totem",
            GuardianTotem::new,
            1,
            1);

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }

    public static <T extends Entity> RegistryObject<EntityType<T>> createEntity(String name, EntityType.EntityFactory<T> factory, float width, float height) {
        ResourceLocation location = new ResourceLocation(Obsidimancy.MOD_ID, name);
        EntityType<T> entity = EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).build(location.toString());
        return ENTITIES.register(name, () -> entity);
    }
}
