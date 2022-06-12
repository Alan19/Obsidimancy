package com.pursuitofglowstone.obsidimancy.structures;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ObsidimancyStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Obsidimancy.MOD_ID);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> RUINED_PORTAL = STRUCTURE_FEATURES.register("ruined_portal", AltarRuinsStructure::new);
}
