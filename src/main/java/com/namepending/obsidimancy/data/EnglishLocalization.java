package com.namepending.obsidimancy.data;

import com.namepending.obsidimancy.Obsidimancy;
import com.namepending.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
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

    protected void add(ItemGroup key, String name) {
        add(key.getRecipeFolderName(), name);
    }
}
