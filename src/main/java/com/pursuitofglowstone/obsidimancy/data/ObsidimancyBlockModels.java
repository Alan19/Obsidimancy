package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.AttunementAltar;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ObsidimancyBlockModels extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;

    public ObsidimancyBlockModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Obsidimancy.MOD_ID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get());
        attunementAltar();
    }

    private void attunementAltar() {
        final String topPath = ObsidimancyBlocks.ATTUNEMENT_ALTAR.getId().getPath() + "_top";
        final String bottomPath = ObsidimancyBlocks.ATTUNEMENT_ALTAR.getId().getPath() + "_bottom";
        getVariantBuilder(((RegistryObject<? extends AttunementAltar>) ObsidimancyBlocks.ATTUNEMENT_ALTAR).get())
                .partialState()
                .with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .addModels(new ConfiguredModel(new ModelFile.ExistingModelFile(modLoc("block/" + topPath), existingFileHelper)))
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .addModels(new ConfiguredModel(new ModelFile.ExistingModelFile(modLoc("block/" + bottomPath), existingFileHelper)));
    }
}
