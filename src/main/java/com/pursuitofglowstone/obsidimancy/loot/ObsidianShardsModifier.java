package com.pursuitofglowstone.obsidimancy.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class ObsidianShardsModifier extends LootModifier {
    private final int minCount;
    private final int maxCount;
    private final Item itemToCheck;
    private final Item itemReward;
    private final int minBonusPerLevelOfFortune;
    private final int maxBonusPerLevelOfFortune;
    private final int upperLimit;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn              the ILootConditions that need to be matched before the loot is modified.
     * @param minCount                  The minimum number of items to drop
     * @param maxCount                  The maximum number of items to drop
     * @param itemToCheck               The item to replace when the loot table is rolled
     * @param itemReward                The item to replace the checked item with
     * @param minBonusPerLevelOfFortune The minimum amount of the reward item to drop
     * @param maxBonusPerLevelOfFortune The maximum amount of the reward item to drop
     * @param upperLimit                The upper limit of the amount of items to drop
     */
    public ObsidianShardsModifier(LootItemCondition[] conditionsIn, int minCount, int maxCount, Item itemToCheck, Item itemReward, int minBonusPerLevelOfFortune, int maxBonusPerLevelOfFortune, int upperLimit) {
        super(conditionsIn);
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.itemToCheck = itemToCheck;
        this.itemReward = itemReward;
        this.minBonusPerLevelOfFortune = minBonusPerLevelOfFortune;
        this.maxBonusPerLevelOfFortune = maxBonusPerLevelOfFortune;
        this.upperLimit = upperLimit;
    }

    /**
     * Removes the checked item from the generated loot, and then add a random amount of the reward item to the loot
     * @param generatedLoot The list of items that is dropped as loot
     * @param context The loot context, to get the looting level
     * @return The modified loot list
     */
    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.removeIf(itemStack -> itemStack.getItem() == itemToCheck);
        final ItemStack baseReward = SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)).build().apply(new ItemStack(itemReward), context);
        final ItemStack modifiedReward = LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(minBonusPerLevelOfFortune, maxBonusPerLevelOfFortune)).setLimit(upperLimit).build().apply(baseReward, context);
        generatedLoot.add(modifiedReward);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ObsidianShardsModifier> {
        @Override
        public ObsidianShardsModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            int minCount = GsonHelper.getAsInt(object, "minCount");
            int maxCount = GsonHelper.getAsInt(object, "maxCount");
            Item obsidianItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "obsidianItem"))));
            Item obsidianShardItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "obsidianShardItem")));
            int minFortuneBonus = GsonHelper.getAsInt(object, "minFortuneBonus");
            int maxFortuneBonus = GsonHelper.getAsInt(object, "maxFortuneBonus");
            int upperLimit = GsonHelper.getAsInt(object, "upperLimit");
            return new ObsidianShardsModifier(ailootcondition, minCount, maxCount, obsidianItem, obsidianShardItem, minFortuneBonus, maxFortuneBonus, upperLimit);
        }

        @Override
        public JsonObject write(ObsidianShardsModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("minCount", instance.minCount);
            json.addProperty("maxCount", instance.maxCount);
            json.addProperty("obsidianItem", ForgeRegistries.ITEMS.getKey(instance.itemToCheck).toString());
            json.addProperty("obsidianShardItem", ForgeRegistries.ITEMS.getKey(instance.itemReward).toString());
            json.addProperty("minFortuneBonus", instance.minBonusPerLevelOfFortune);
            json.addProperty("maxFortuneBonus", instance.maxBonusPerLevelOfFortune);
            json.addProperty("upperLimit", instance.upperLimit);
            return json;
        }
    }
}
