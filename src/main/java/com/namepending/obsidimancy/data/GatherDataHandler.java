package com.namepending.obsidimancy.data;

import com.namepending.obsidimancy.Obsidimancy;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Obsidimancy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GatherDataHandler {
    @SubscribeEvent
    public static void gatherData (final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new ItemModels(generator, Obsidimancy.MOD_ID, event.getExistingFileHelper()));
        generator.addProvider(new EnglishLocalization(generator));
    }
}
