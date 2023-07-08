package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import com.pursuitofglowstone.obsidimancy.tags.ObsidimancyTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ObsidimancyBlockTags extends BlockTagsProvider {
    public ObsidimancyBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.NEEDS_IRON_TOOL).add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get(), ObsidimancyBlocks.ATTUNEMENT_ALTAR.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get());
        tag(ObsidimancyTags.MINEABLE_WITH_PICKAXE_OR_SHOVEL).addTags(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
