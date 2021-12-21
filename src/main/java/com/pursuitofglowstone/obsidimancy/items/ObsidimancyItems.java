package com.pursuitofglowstone.obsidimancy.items;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class ObsidimancyItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Obsidimancy.MOD_ID);
    public static final CreativeModeTab TAB_OBSIDIMANCY = getCreativeModeTab();

    @NotNull
    private static CreativeModeTab getCreativeModeTab() {
        return new CreativeModeTab("obsidimancy") {
            @Nonnull
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(OBSIDIAN_SHARD.get());
            }
        };
    }


    public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", ObsidimancyItems::createStandardItem);
    public static final RegistryObject<Item> OVERWORLD_SHARD = ITEMS.register("overworld_shard", ObsidimancyItems::createUncommonItem);
    public static final RegistryObject<Item> NETHER_SHARD = ITEMS.register("nether_shard", ObsidimancyItems::createUncommonItem);
    public static final RegistryObject<Item> ENDER_SHARD = ITEMS.register("ender_shard", ObsidimancyItems::createUncommonItem);
    public static final RegistryObject<Item> SKYDIVERS_HOOD = ITEMS.register("skydivers_hood", SkydiversHood::new);
    public static final RegistryObject<Item> PRECURSOR_PICKAXE = ITEMS.register("precursor_pickaxe", PrecursorPickaxe::new);
    public static final RegistryObject<Item> ATTUNEMENT_ALTAR = ITEMS.register("attunement_altar", () -> generateItemBlock(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get()));

    @NotNull
    private static Item createStandardItem() {
        return new Item(new Item.Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY));
    }

    @NotNull
    private static Item createUncommonItem() {
        return new Item(new Item.Properties().tab(TAB_OBSIDIMANCY).rarity(Rarity.UNCOMMON));
    }

    @NotNull
    private static ItemNameBlockItem generateItemBlock(Block block) {
        return new ItemNameBlockItem(block, new Item.Properties().tab(TAB_OBSIDIMANCY));
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
