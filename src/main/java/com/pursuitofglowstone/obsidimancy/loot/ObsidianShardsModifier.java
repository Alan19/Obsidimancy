package com.pursuitofglowstone.obsidimancy.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ObsidianShardsModifier extends LootModifier {
    public static final Supplier<Codec<ObsidianShardsModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> codecStart(instance)
            .and(Codec.INT.fieldOf("min").forGetter(ObsidianShardsModifier::getMinCount))
            .and(Codec.INT.fieldOf("max").forGetter(ObsidianShardsModifier::getMaxCount))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(ObsidianShardsModifier::getItemToCheck))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("result").forGetter(ObsidianShardsModifier::getItemReward))
            .and(Codec.INT.fieldOf("bonusMultiplier").forGetter(ObsidianShardsModifier::getLootingMultiplier))
            .and(Codec.INT.fieldOf("limitMax").forGetter(ObsidianShardsModifier::getUpperLimit))
            .apply(instance, ObsidianShardsModifier::new)));

    private final int minCount;
    private final int maxCount;
    private final Item itemToCheck;
    private final Item itemReward;
    private final int lootingMultiplier;
    private final int upperLimit;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn              the ILootConditions that need to be matched before the loot is modified.
     * @param minCount                  The minimum number of items to drop
     * @param maxCount                  The maximum number of items to drop
     * @param itemToCheck               The item to replace when the loot table is rolled
     * @param itemReward                The item to replace the checked item with
     * @param lootingMultiplier The maximum amount of the reward item to drop
     * @param upperLimit                The upper limit of the amount of items to drop
     */
    public ObsidianShardsModifier(LootItemCondition[] conditionsIn, int minCount, int maxCount, Item itemToCheck, Item itemReward, int lootingMultiplier, int upperLimit) {
        super(conditionsIn);
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.itemToCheck = itemToCheck;
        this.itemReward = itemReward;
        this.lootingMultiplier = lootingMultiplier;
        this.upperLimit = upperLimit;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public Item getItemToCheck() {
        return itemToCheck;
    }

    public Item getItemReward() {
        return itemReward;
    }

    public int getLootingMultiplier() {
        return lootingMultiplier;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    /**
     * Removes the checked item from the generated loot, and then add a random amount of the reward item to the loot
     *
     * @param generatedLoot The list of items that is dropped as loot
     * @param context       The loot context, to get the looting level
     * @return The modified loot list
     */
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.removeIf(itemStack -> itemStack.getItem() == itemToCheck);
        final ItemStack baseReward = SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)).build().apply(new ItemStack(itemReward), context);
        final ItemStack modifiedReward = LootingEnchantFunction.lootingMultiplier(ConstantValue.exactly(lootingMultiplier)).setLimit(upperLimit).build().apply(baseReward, context);
        generatedLoot.add(modifiedReward);
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
