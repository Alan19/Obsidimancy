package com.namepending.obsidimancy.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
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

    /**
     * Constructs a LootModifier.
     *  @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     * @param minCount The minimum number of items to drop
     * @param maxCount The maximum number of items to drop
     * @param itemToCheck The item to replace when the loot table is rolled
     * @param itemReward The item to replace the checked item with
     * @param minBonusPerLevelOfFortune The minimum amount of the reward item to drop
     * @param maxBonusPerLevelOfFortune The maximum amount of the reward item to drop
     */
    public ObsidianShardsModifier(ILootCondition[] conditionsIn, int minCount, int maxCount, Item itemToCheck, Item itemReward, int minBonusPerLevelOfFortune, int maxBonusPerLevelOfFortune) {
        super(conditionsIn);
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.itemToCheck = itemToCheck;
        this.itemReward = itemReward;
        this.minBonusPerLevelOfFortune = minBonusPerLevelOfFortune;
        this.maxBonusPerLevelOfFortune = maxBonusPerLevelOfFortune;
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
        final ItemStack baseReward = SetCount.setCount(RandomValueRange.between(minCount, maxCount)).build().apply(new ItemStack(itemReward), context);
        final ItemStack modifiedReward = LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(minBonusPerLevelOfFortune, maxBonusPerLevelOfFortune)).build().apply(baseReward, context);
        generatedLoot.add(modifiedReward);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ObsidianShardsModifier> {
        @Override
        public ObsidianShardsModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            int minCount = JSONUtils.getAsInt(object, "minCount");
            int maxCount = JSONUtils.getAsInt(object, "maxCount");
            Item obsidianItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getAsString(object, "obsidianItem"))));
            Item obsidianShardItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "obsidianShardItem")));
            int minFortuneBonus = JSONUtils.getAsInt(object, "minFortuneBonus");
            int maxFortuneBonus = JSONUtils.getAsInt(object, "maxFortuneBonus");
            return new ObsidianShardsModifier(ailootcondition, minCount, maxCount, obsidianItem, obsidianShardItem, minFortuneBonus, maxFortuneBonus);
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
            return json;
        }
    }
}
