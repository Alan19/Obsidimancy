package com.namepending.obsidimancy.data;

import com.namepending.obsidimancy.Obsidimancy;
import com.namepending.obsidimancy.items.ObsidimancyItems;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class ObsidimancyRecipeProvider extends RecipeProvider {
    public ObsidimancyRecipeProvider(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        SmithingRecipeBuilder.smithing(Ingredient.of(Items.IRON_PICKAXE), Ingredient.of(ObsidimancyItems.OBSIDIAN_SHARD.get()), ObsidimancyItems.PRECURSOR_PICKAXE.get()).unlocks("has_obsidian_shard", has(ObsidimancyItems.OBSIDIAN_SHARD.get())).save(consumer, new ResourceLocation(Obsidimancy.MOD_ID, "precursor_pickaxe"));
    }
}
