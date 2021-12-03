package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;

public class EnglishLocalization extends LanguageProvider {
    public EnglishLocalization(DataGenerator gen) {
        super(gen, Obsidimancy.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ObsidimancyItems.OBSIDIAN_SHARD.get(), "Obsidian Shard");
        add(ObsidimancyItems.SKYDIVERS_HOOD.get(), "Skydiver's Hood");
        add(ObsidimancyItems.PRECURSOR_PICKAXE.get(), "Precursor's Pickaxe");
        add(ObsidimancyItems.TAB_OBSIDIMANCY, "Obsidimancy");
    }

    protected void add(CreativeModeTab key, String name) {
        add(key.getRecipeFolderName(), name);
    }
}
