package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ObsidimancyBlockLootTables extends VanillaBlockLoot {

    @Override
    protected void generate() {
        add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get(), block -> createSilkTouchDispatchTable(block,
                applyExplosionDecay(block, LootItem.lootTableItem(ObsidimancyItems.OBSIDIAN_SHARD.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.range(0, 4))))));
        add(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get(), noDrop());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ObsidimancyBlocks.BLOCKS.getRegistry().get();
    }
}
