package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.tags.ObsidimancyTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems.*;

public class ObsidimancyItemTags extends ItemTagsProvider {

    public ObsidimancyItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags() {
        tag(ObsidimancyTags.PRECURSOR_PICKAXES).add(PRECURSOR_PICKAXE.get(), NETHER_PRECURSOR_PICKAXE.get(), ENDER_PRECURSOR_PICKAXE.get());
        tag(ObsidimancyTags.OBSIDIAN_SHARDS).add(OBSIDIAN_SHARD.get(), OVERWORLD_SHARD.get(), NETHER_SHARD.get(), ENDER_SHARD.get());
        tag(Items.TOOLS_PICKAXES).addTags(ObsidimancyTags.PRECURSOR_PICKAXES);
        tag(Items.ARMORS_HELMETS).add(SKYDIVERS_HOOD.get(), OVERWORLD_SKYDIVERS_HOOD.get(), NETHER_SKYDIVERS_HOOD.get(), ENDER_SKYDIVERS_HOOD.get());
    }
}
