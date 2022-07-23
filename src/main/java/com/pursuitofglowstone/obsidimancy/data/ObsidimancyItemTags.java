package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.tags.ObsidimancyTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems.*;

public class ObsidimancyItemTags extends ItemTagsProvider {

    public ObsidimancyItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(ObsidimancyTags.PRECURSOR_PICKAXES).add(PRECURSOR_PICKAXE.get(), NETHER_PRECURSOR_PICKAXE.get(), ENDER_PRECURSOR_PICKAXE.get());
        tag(ObsidimancyTags.OBSIDIAN_SHARDS).add(OBSIDIAN_SHARD.get(), OVERWORLD_SHARD.get(), NETHER_SHARD.get(), ENDER_SHARD.get());
    }
}
