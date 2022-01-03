package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems.*;

public class ObsidimancyRecipeProvider extends RecipeProvider {
    public ObsidimancyRecipeProvider(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        saveUpgradeRecipe(Items.IRON_PICKAXE, Ingredient.of(OBSIDIAN_SHARD.get()), PRECURSOR_PICKAXE.get(), OBSIDIAN_SHARD.get(), consumer);
        saveUpgradeGroup(consumer, SKYDIVERS_HOOD.get(), OVERWORLD_SKYDIVERS_HOOD.get(), NETHER_SKYDIVERS_HOOD.get(), ENDER_SKYDIVERS_HOOD.get());
        saveUpgradeGroup(consumer, PRECURSOR_PICKAXE.get(), OVERWORLD_PRECURSOR_PICKAXE.get(), NETHER_PRECURSOR_PICKAXE.get(), ENDER_PRECURSOR_PICKAXE.get());
        ShapedRecipeBuilder.shaped(SKYDIVERS_HOOD.get()).define('S', OBSIDIAN_SHARD.get())
                .define('W', ItemTags.WOOL)
                .pattern("WWW")
                .pattern("WSW")
                .unlockedBy("has_obsidian_shard", has(OBSIDIAN_SHARD.get()))
                .save(consumer, new ResourceLocation(Obsidimancy.MOD_ID, "skydivers_hood"));
    }

    private void saveUpgradeGroup(@NotNull Consumer<FinishedRecipe> consumer, Item base, Item overworldResult, Item netherResult, Item endResult) {
        saveUpgradeRecipe(base, Ingredient.of(new ItemStack(OVERWORLD_SHARD.get(), 4)), overworldResult, OVERWORLD_SHARD.get(), consumer);
        saveUpgradeRecipe(base, Ingredient.of(new ItemStack(NETHER_SHARD.get(), 4)), netherResult, NETHER_SHARD.get(), consumer);
        saveUpgradeRecipe(base, Ingredient.of(new ItemStack(ENDER_SHARD.get(), 4)), endResult, ENDER_SHARD.get(), consumer);
    }

    private void saveUpgradeRecipe(Item base, Ingredient ingredient, Item result, Item requirementItem, @NotNull Consumer<FinishedRecipe> consumer) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(base), ingredient, result)
                .unlocks("has_%s".formatted(requirementItem.getRegistryName().getPath()), has(requirementItem))
                .save(consumer, new ResourceLocation(Obsidimancy.MOD_ID, result.getRegistryName().getPath()));
    }

}
