package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Obsidimancy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GatherDataHandler {
    @SubscribeEvent
    public static void gatherData (final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(new ObsidimancyItemModels(generator, Obsidimancy.MOD_ID, existingFileHelper));
        generator.addProvider(new EnglishLocalization(generator));
        ObsidimancyBlockTags blockTags = new ObsidimancyBlockTags(generator, existingFileHelper);
        generator.addProvider(blockTags);
        generator.addProvider(new ObsidimancyItemTags(generator, blockTags, existingFileHelper));
        generator.addProvider(new ObsidimancyLootTables(generator));
        generator.addProvider(new ObsidimancyGLMs(generator));
        generator.addProvider(new ObsidimancyRecipeProvider(generator));
        generator.addProvider(new ObsidimancyBlockModels(generator, existingFileHelper));
        generator.addProvider(new ObsidimancyBiomeTags(generator, existingFileHelper));
    }
}
