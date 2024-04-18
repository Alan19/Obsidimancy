package com.pursuitofglowstone.obsidimancy.blocks;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ObsidimancyBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Obsidimancy.MOD_ID);

    public static final Supplier<AttunementAltar> ATTUNEMENT_ALTAR = BLOCKS.register("attunement_altar", AttunementAltar::new);
    public static final Supplier<FragileObsidian> FRAGILE_OBSIDIAN = BLOCKS.register("fragile_obsidian", FragileObsidian::new);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }


}
