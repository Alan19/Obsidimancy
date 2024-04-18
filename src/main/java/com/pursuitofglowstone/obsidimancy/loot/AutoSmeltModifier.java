package com.pursuitofglowstone.obsidimancy.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AutoSmeltModifier extends LootModifier {
    public static Supplier<Codec<AutoSmeltModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> codecStart(instance)
            .apply(instance, AutoSmeltModifier::new)));

    public AutoSmeltModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();
        generatedLoot.stream().map(itemStack -> smelt(itemStack, context)).forEach(newLoot::add);
        return newLoot;
    }

    private static ItemStack smelt(ItemStack stack, LootContext context) {
        return context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                .filter(smeltingRecipe -> !smeltingRecipe.getResultItem().isEmpty())
                .map(smeltingRecipe -> getOutputAndSpawnXP(context, smeltingRecipe, stack.getCount()))
                .orElse(stack);
    }

    @NotNull
    private static ItemStack getOutputAndSpawnXP(LootContext context, SmeltingRecipe smeltingRecipe, int inputStackSize) {
        createExperience(context.getLevel(), context.getParam(LootContextParams.ORIGIN), inputStackSize, smeltingRecipe.getExperience());
        return ItemHandlerHelper.copyStackWithSize(smeltingRecipe.getResultItem(), smeltingRecipe.getResultItem().getCount() * inputStackSize);
    }

    private static void createExperience(ServerLevel pLevel, Vec3 pPopVec, int pRecipeIndex, float pExperience) {
        int xpAward = Mth.floor((float) pRecipeIndex * pExperience);
        float fractionalAward = Mth.frac((float) pRecipeIndex * pExperience);
        if (fractionalAward != 0.0F && Math.random() < (double) fractionalAward) {
            ++xpAward;
        }

        ExperienceOrb.award(pLevel, pPopVec, xpAward);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
