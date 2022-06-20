package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ObsidimancyBlockModels extends BlockStateProvider {
    public ObsidimancyBlockModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get());
    }
}
