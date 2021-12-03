package com.namepending.obsidimancy.data;

import com.namepending.obsidimancy.Obsidimancy;
import com.namepending.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class ObsidimancyRecipeProvider extends RecipeProvider {
    public ObsidimancyRecipeProvider(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.IRON_PICKAXE), Ingredient.of(ObsidimancyItems.OBSIDIAN_SHARD.get()), ObsidimancyItems.PRECURSOR_PICKAXE.get()).unlocks("has_obsidian_shard", has(ObsidimancyItems.OBSIDIAN_SHARD.get())).save(consumer, new ResourceLocation(Obsidimancy.MOD_ID, "precursor_pickaxe"));
        ShapedRecipeBuilder.shaped(ObsidimancyItems.SKYDIVERS_HOOD.get()).define('S', ObsidimancyItems.OBSIDIAN_SHARD.get()).define('W', ItemTags.WOOL).pattern("WWW").pattern("WSW").unlockedBy("has_obsidian_shard", has(ObsidimancyItems.OBSIDIAN_SHARD.get())).save(consumer, new ResourceLocation(Obsidimancy.MOD_ID, "skydivers_hood"));
    }

}
