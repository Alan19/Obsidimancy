package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.tags.ObsidimancyTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.tags.BiomeTags.*;

public class ObsidimancyBiomeTags extends BiomeTagsProvider {
    public ObsidimancyBiomeTags(DataGenerator p_211094_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_211094_, Obsidimancy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
    }
}
