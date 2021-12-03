package com.namepending.obsidimancy.data;

import com.namepending.obsidimancy.Obsidimancy;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ObsidimancyBlockTags extends BlockTagsProvider {
    public ObsidimancyBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // We'll add tags later
    }
}
