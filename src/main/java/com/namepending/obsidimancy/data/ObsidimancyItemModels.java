package com.namepending.obsidimancy.data;

import com.namepending.obsidimancy.Obsidimancy;
import com.namepending.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ObsidimancyItemModels extends ItemModelProvider {
    private final ResourceLocation generatedItem = mcLoc("item/generated");

    public ObsidimancyItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        forItem(ObsidimancyItems.OBSIDIAN_SHARD);
    }

    private void forItem(RegistryObject<? extends Item> item) {
        singleTexture(item.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/" + item.getId().getPath()));
    }

    private void forBlockItem(RegistryObject<? extends BlockNamedItem> item) {
        getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Obsidimancy.MOD_ID, "block/" + item.get().getBlock().getRegistryName().getPath())));
    }

    private void forBlockItem(RegistryObject<? extends BlockNamedItem> item, ResourceLocation modelLocation) {
        getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation));
    }

    private void forBlockItemWithParent(RegistryObject<? extends BlockNamedItem> item, ResourceLocation modelLocation) {
        singleTexture(item.getId().getPath(), generatedItem, "layer0", modelLocation);
    }

    private void forBlockItemWithParent(RegistryObject<? extends BlockNamedItem> item) {
        singleTexture(item.getId().getPath(), generatedItem, "layer0", modLoc("block/" + item.getId().getPath()));
    }

}
