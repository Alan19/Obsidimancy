package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import com.pursuitofglowstone.obsidimancy.loot.ObsidianShardsModifier;
import com.pursuitofglowstone.obsidimancy.loot.ObsidimancyLootModifiers;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ObsidimancyGLMs extends GlobalLootModifierProvider {
    public ObsidimancyGLMs(DataGenerator gen) {
        super(gen, Obsidimancy.MOD_ID);
    }

    @Override
    protected void start() {
        add("obsidian", ObsidimancyLootModifiers.OBSIDIAN_SHARDS.get(), new ObsidianShardsModifier(new LootItemCondition[]{
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ObsidimancyItems.PRECURSOR_PICKAXE.get())).build(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.OBSIDIAN).build()
        }, 2, 4, Items.OBSIDIAN, ObsidimancyItems.OBSIDIAN_SHARD.get(), 0, 1));
    }
}
