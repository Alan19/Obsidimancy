package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import com.pursuitofglowstone.obsidimancy.items.enchantment.ObsidimancyEnchantments;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;

public class EnglishLocalization extends LanguageProvider {
    public EnglishLocalization(DataGenerator gen) {
        super(gen, Obsidimancy.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItems();
        add(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get(), "Attunement Altar");
        add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get(), "Fragile Obsidian");
        add("obsidimancy.overworld_attunement_message", "Overworld Attunement Progress: %s");
        add("obsidimancy.nether_attunement_message", "Nether Attunement Progress: %s");
        add("obsidimancy.end_attunement_message", "End Attunement Progress: %s");
        add("obsidimancy.inherent_enchantment", "This item has the effect of these enchantments, but can still be enchanted with other enchantments!");
    }

    private void addItems() {
        add(ObsidimancyItems.TAB_OBSIDIMANCY, "Obsidimancy");
        add(ObsidimancyItems.FRAGILE_OBSIDIAN.get(), "Fragile Obsidian");
        add(ObsidimancyItems.OBSIDIAN_SHARD.get(), "Obsidian Shard");
        add(ObsidimancyItems.OVERWORLD_SHARD.get(), "Overworld Shard");
        add(ObsidimancyItems.NETHER_SHARD.get(), "Nether Shard");
        add(ObsidimancyItems.ENDER_SHARD.get(), "Ender Shard");
        add(ObsidimancyItems.PRECURSOR_PICKAXE.get(), "Precursor's Pickaxe");
        add(ObsidimancyItems.OVERWORLD_PRECURSOR_PICKAXE.get(), "Ancient Pickaxe");
        add(ObsidimancyItems.NETHER_PRECURSOR_PICKAXE.get(), "Chthonic Pickaxe");
        add(ObsidimancyItems.ENDER_PRECURSOR_PICKAXE.get(), "Ghostly Pickaxe");
        add(ObsidimancyItems.SKYDIVERS_HOOD.get(), "Skydiver's Hood");
        add(ObsidimancyItems.OVERWORLD_SKYDIVERS_HOOD.get(), "Ancient Hood");
        add(ObsidimancyItems.NETHER_SKYDIVERS_HOOD.get(), "Cthonic Hood");
        add(ObsidimancyItems.ENDER_SKYDIVERS_HOOD.get(), "Ghostly Hood");
    }

    protected void add(CreativeModeTab key, String name) {
        add("itemGroup.%s".formatted(key.getRecipeFolderName()), name);
        add(ObsidimancyEnchantments.SHATTERING.get(), "Shattering");
    }
}
