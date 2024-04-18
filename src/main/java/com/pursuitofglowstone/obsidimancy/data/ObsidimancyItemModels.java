package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ObsidimancyItemModels extends ItemModelProvider {
    private final ResourceLocation generatedItem = mcLoc("item/generated");
    private final ExistingFileHelper existingFileHelper;

    public ObsidimancyItemModels(PackOutput packOutput, String modid, ExistingFileHelper existingFileHelper) {
        super(packOutput, modid, existingFileHelper);
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
        getBuilder(ObsidimancyItems.ATTUNEMENT_ALTAR.get().getDescriptionId()).parent(new ModelFile.ExistingModelFile(modLoc("block/attunement_altar_top"), existingFileHelper));
        forBlockItem(ObsidimancyItems.FRAGILE_OBSIDIAN);
    }

    private void forItem(Supplier<? extends Item> item, String pathName) {
        singleTexture(item.get().getDescriptionId(), mcLoc("item/handheld"), "layer0", modLoc("item/" + pathName));
    }

    private void forItem(Supplier<? extends Item> item) {
        singleTexture(item.get().getDescriptionId(), mcLoc("item/handheld"), "layer0", modLoc("item/" + item.get().getDescriptionId()));
    }

    private void forBlockItem(Supplier<? extends ItemNameBlockItem> item) {
        getBuilder(item.get().getDescriptionId()).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Obsidimancy.MOD_ID, "block/" + Registries.BLOCK.getKey(item.get().getBlock()).getPath())));
    }

    private void forBlockItem(Supplier<? extends ItemNameBlockItem> item, ResourceLocation modelLocation) {
        getBuilder(item.get().getDescriptionId()).parent(new ModelFile.UncheckedModelFile(modelLocation));
    }

    private void forBlockItemWithParent(Supplier<? extends ItemNameBlockItem> item, ResourceLocation modelLocation) {
        singleTexture(item.get().getDescriptionId(), generatedItem, "layer0", modelLocation);
    }

    private void forBlockItemWithParent(Supplier<? extends ItemNameBlockItem> item) {
        singleTexture(item.get().getDescriptionId(), generatedItem, "layer0", modLoc("block/" + item.get().getDescriptionId()));
    }

}
