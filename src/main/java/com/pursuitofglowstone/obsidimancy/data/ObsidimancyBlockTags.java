package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import com.pursuitofglowstone.obsidimancy.tags.ObsidimancyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ObsidimancyBlockTags extends BlockTagsProvider {

    public ObsidimancyBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.NEEDS_IRON_TOOL).add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get(), ObsidimancyBlocks.ATTUNEMENT_ALTAR.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get());
        tag(ObsidimancyTags.MINEABLE_WITH_PICKAXE_OR_SHOVEL).addTags(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_SHOVEL);

    }
}
