package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ObsidimancyItemModels extends ItemModelProvider {
    private final ResourceLocation generatedItem = mcLoc("item/generated");
    private final ExistingFileHelper existingFileHelper;

    public ObsidimancyItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerModels() {
        forItem(ObsidimancyItems.OBSIDIAN_SHARD);
        forItem(ObsidimancyItems.OVERWORLD_SHARD);
        forItem(ObsidimancyItems.NETHER_SHARD);
        forItem(ObsidimancyItems.ENDER_SHARD);
        forItem(ObsidimancyItems.PRECURSOR_PICKAXE, "precursor_pickaxe/precursor_pickaxe");
        forItem(ObsidimancyItems.SKYDIVERS_HOOD);
        forItem(ObsidimancyItems.OVERWORLD_SKYDIVERS_HOOD, "skydivers_hood");
        forItem(ObsidimancyItems.NETHER_SKYDIVERS_HOOD, "skydivers_hood");
        forItem(ObsidimancyItems.ENDER_SKYDIVERS_HOOD, "skydivers_hood");
        forItem(ObsidimancyItems.OVERWORLD_PRECURSOR_PICKAXE, "precursor_pickaxe/overworld/ancient_pickaxe");
        forItem(ObsidimancyItems.NETHER_PRECURSOR_PICKAXE, "precursor_pickaxe/nether/chthonic_pickaxe");
        forItem(ObsidimancyItems.ENDER_PRECURSOR_PICKAXE, "precursor_pickaxe/end/ghostly_pickaxe");
        getBuilder(ObsidimancyItems.ATTUNEMENT_ALTAR.getId().getPath()).parent(new ModelFile.ExistingModelFile(modLoc("block/attunement_altar_top"), existingFileHelper));
        forBlockItem(ObsidimancyItems.FRAGILE_OBSIDIAN);
    }

    private void forItem(RegistryObject<? extends Item> item, String pathName) {
        singleTexture(item.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/" + pathName));
    }

    private void forItem(RegistryObject<? extends Item> item) {
        singleTexture(item.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/" + item.getId().getPath()));
    }

    private void forBlockItem(RegistryObject<? extends ItemNameBlockItem> item) {
        getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Obsidimancy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(item.get().getBlock()).getPath())));
    }

    private void forBlockItem(RegistryObject<? extends ItemNameBlockItem> item, ResourceLocation modelLocation) {
        getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation));
    }

    private void forBlockItemWithParent(RegistryObject<? extends ItemNameBlockItem> item, ResourceLocation modelLocation) {
        singleTexture(item.getId().getPath(), generatedItem, "layer0", modelLocation);
    }

    private void forBlockItemWithParent(RegistryObject<? extends ItemNameBlockItem> item) {
        singleTexture(item.getId().getPath(), generatedItem, "layer0", modLoc("block/" + item.getId().getPath()));
    }

}
