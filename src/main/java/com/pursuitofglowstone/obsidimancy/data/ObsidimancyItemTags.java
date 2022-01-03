package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import com.pursuitofglowstone.obsidimancy.tags.ObsidimancyTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ObsidimancyItemTags extends ItemTagsProvider {

    public ObsidimancyItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(ObsidimancyTags.PRECURSOR_PICKAXES).add(ObsidimancyItems.PRECURSOR_PICKAXE.get(), ObsidimancyItems.NETHER_PRECURSOR_PICKAXE.get(), ObsidimancyItems.ENDER_PRECURSOR_PICKAXE.get());
    }
}
