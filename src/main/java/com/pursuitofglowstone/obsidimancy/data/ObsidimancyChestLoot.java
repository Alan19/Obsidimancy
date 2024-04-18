package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.loot.packs.VanillaChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ObsidimancyChestLoot extends VanillaChestLoot {


    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(new ResourceLocation(Obsidimancy.MOD_ID, "chests/common_overworld_loot"), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(1, 3))
                .setBonusRolls(UniformGenerator.between(1, 3))
                .add(LootItem.lootTableItem(ObsidimancyItems.OBSIDIAN_SHARD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ObsidimancyItems.FRAGILE_OBSIDIAN.get()))));
        consumer.accept(new ResourceLocation(Obsidimancy.MOD_ID, "chests/uncommon_overworld_loot"), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(1, 3))
                .setBonusRolls(UniformGenerator.between(1, 3))
                .add(LootItem.lootTableItem(ObsidimancyItems.NETHER_SHARD.get()))
                .add(LootItem.lootTableItem(ObsidimancyItems.ENDER_SHARD.get()))
                .add(LootItem.lootTableItem(ObsidimancyItems.OVERWORLD_SHARD.get()))));
        consumer.accept(new ResourceLocation(Obsidimancy.MOD_ID, "chests/common_end_loot"), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Blocks.BLUE_ICE).setWeight(1))
                .add(LootItem.lootTableItem(Blocks.SNOW_BLOCK).setWeight(4))
                .add(LootItem.lootTableItem(Items.POTATO).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))))
                .add(LootItem.lootTableItem(Items.BREAD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                .add(LootItem.lootTableItem(Items.BEETROOT_SOUP).setWeight(1))
                .add(LootItem.lootTableItem(Items.FURNACE).setWeight(1))
                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                .add(LootItem.lootTableItem(Items.SNOWBALL).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))))
                .add(LootItem.lootTableItem(Items.COAL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))));
        consumer.accept(new ResourceLocation(Obsidimancy.MOD_ID, "chests/uncommon_end_loot"), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.OBSIDIAN).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                .add(LootItem.lootTableItem(Items.FLINT).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(9.0F, 18.0F))))
                .add(LootItem.lootTableItem(Items.FLINT_AND_STEEL).setWeight(40)).add(LootItem.lootTableItem(Items.FIRE_CHARGE).setWeight(40))
                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(15))
                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 24.0F))))
                .add(LootItem.lootTableItem(Items.GOLDEN_SWORD).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_AXE).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_HOE).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_SHOVEL).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_PICKAXE).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_BOOTS).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_CHESTPLATE).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_HELMET).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GOLDEN_LEGGINGS).setWeight(15).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 12.0F))))
                .add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR).setWeight(5))
                .add(LootItem.lootTableItem(Items.LIGHT_WEIGHTED_PRESSURE_PLATE).setWeight(5))
                .add(LootItem.lootTableItem(Items.GOLDEN_CARROT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 12.0F))))
                .add(LootItem.lootTableItem(Items.CLOCK).setWeight(5))
                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F))))
                .add(LootItem.lootTableItem(Items.BELL).setWeight(1))
                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1))
                .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));
        consumer.accept(new ResourceLocation(Obsidimancy.MOD_ID, "chests/rare_end_loot"), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 7.0F))))
                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))))
                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 7.0F))))
                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))))
                .add(LootItem.lootTableItem(Items.SADDLE).setWeight(3)).add(LootItem.lootTableItem(Items.IRON_HORSE_ARMOR))
                .add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR)).add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR))
                .add(LootItem.lootTableItem(Items.DIAMOND_SWORD).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.DIAMOND_BOOTS).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.DIAMOND_CHESTPLATE).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.DIAMOND_LEGGINGS).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.DIAMOND_HELMET).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.DIAMOND_PICKAXE).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.DIAMOND_SHOVEL).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.IRON_SWORD).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.IRON_BOOTS).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.IRON_CHESTPLATE).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.IRON_LEGGINGS).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.IRON_HELMET).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.IRON_PICKAXE).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))
                .add(LootItem.lootTableItem(Items.IRON_SHOVEL).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20.0F, 39.0F)).allowTreasure()))));
        consumer.accept(new ResourceLocation(Obsidimancy.MOD_ID, "chests/end_altar"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4, 18)).setBonusRolls(UniformGenerator.between(1, 4))
                        .add(LootTableReference.lootTableReference(new ResourceLocation(Obsidimancy.MOD_ID, "chests/common_end_loot")).setWeight(50).setQuality(-2))
                        .add(LootTableReference.lootTableReference(new ResourceLocation(Obsidimancy.MOD_ID, "chests/uncommon_end_loot")).setWeight(5).setQuality(-1))
                        .add(LootTableReference.lootTableReference(new ResourceLocation(Obsidimancy.MOD_ID, "chests/rare_end_loot")).setWeight(0).setQuality(10))));
        consumer.accept(new ResourceLocation(Obsidimancy.MOD_ID, "chests/altar_ruins"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 4)).setBonusRolls(UniformGenerator.between(1, 3))
                        .add(LootTableReference.lootTableReference(new ResourceLocation(Obsidimancy.MOD_ID, "chests/common_overworld_loot")).setWeight(50).setQuality(-2))
                        .add(LootTableReference.lootTableReference(new ResourceLocation(Obsidimancy.MOD_ID, "chests/uncommon_overworld_loot")).setWeight(5).setQuality(-1))));
    }
}
