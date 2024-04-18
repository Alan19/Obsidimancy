package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Obsidimancy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GatherDataHandler {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(true, new ObsidimancyItemModels(generator, Obsidimancy.MOD_ID, existingFileHelper));
        generator.addProvider(true, new EnglishLocalization(generator));
        ObsidimancyBlockTags blockTags = new ObsidimancyBlockTags(generator, existingFileHelper);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new ObsidimancyItemTags(generator, blockTags, existingFileHelper));
        generator.addProvider(true, new ObsidimancyLootTables(generator));
        generator.addProvider(true, new ObsidimancyGLMs(generator));
        generator.addProvider(true, new ObsidimancyRecipeProvider(generator));
        generator.addProvider(true, new ObsidimancyBlockModels(generator, existingFileHelper));
        generator.addProvider(true, new ObsidimancyBiomeTags(generator, existingFileHelper));
    }
}
