package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
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
        add(ObsidimancyItems.ATTUNEMENT_ALTAR.get(), "Attunement Altar");
        add(ObsidimancyItems.OVERWORLD_SHARD.get(), "Overworld Shard");
        add(ObsidimancyItems.NETHER_SHARD.get(), "Nether Shard");
        add(ObsidimancyItems.ENDER_SHARD.get(), "Ender Shard");
        add(ObsidimancyItems.FRAGILE_OBSIDIAN.get(), "Fragile Obsidian");
        add(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get(), "Attunement Altar");
        add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get(), "Fragile Obsidian");
        add("obsidimancy.overworld_attunement_message", "Overworld Attunement Progress: %s/100");
    }

    protected void add(CreativeModeTab key, String name) {
        add("itemGroup.%s".formatted(key.getRecipeFolderName()), name);
    }
}
