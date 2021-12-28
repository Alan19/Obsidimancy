package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.blocks.AttunementAltar;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class ObsidimancyBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        add(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get(), block -> createSilkTouchDispatchTable(block,
                applyExplosionDecay(block, LootItem.lootTableItem(ObsidimancyItems.OBSIDIAN_SHARD.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        dropSelf(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ObsidimancyBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
