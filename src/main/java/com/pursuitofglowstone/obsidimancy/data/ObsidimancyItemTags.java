package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.tags.ObsidimancyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

import static com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems.*;

public class ObsidimancyItemTags extends ItemTagsProvider {


    public ObsidimancyItemTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ObsidimancyTags.PRECURSOR_PICKAXES).add(PRECURSOR_PICKAXE.get(), NETHER_PRECURSOR_PICKAXE.get(), ENDER_PRECURSOR_PICKAXE.get());
        tag(ObsidimancyTags.OBSIDIAN_SHARDS).add(OBSIDIAN_SHARD.get(), OVERWORLD_SHARD.get(), NETHER_SHARD.get(), ENDER_SHARD.get());
        tag(ItemTags.PICKAXES).addTags(ObsidimancyTags.PRECURSOR_PICKAXES);
        tag(Tags.Items.ARMORS_HELMETS).add(SKYDIVERS_HOOD.get(), OVERWORLD_SKYDIVERS_HOOD.get(), NETHER_SKYDIVERS_HOOD.get(), ENDER_SKYDIVERS_HOOD.get());

    }
}
