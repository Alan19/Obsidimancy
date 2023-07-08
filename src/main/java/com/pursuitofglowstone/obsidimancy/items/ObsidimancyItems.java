package com.pursuitofglowstone.obsidimancy.items;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.end.EndPrecursorPickaxe;
import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.nether.NetherPrecursorPickaxe;
import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.overworld.OverworldPrecursorPickaxe;
import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.PrecursorPickaxe;
import com.pursuitofglowstone.obsidimancy.items.skydivershood.EnderSkydiversHood;
import com.pursuitofglowstone.obsidimancy.items.skydivershood.NetherSkydiversHood;
import com.pursuitofglowstone.obsidimancy.items.skydivershood.OverworldSkydiversHood;
import com.pursuitofglowstone.obsidimancy.items.skydivershood.SkydiversHood;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
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
    public static final Style INHERENT_ENCHANT_STYLE = Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(true);

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


    public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", ObsidianShard::new);
    public static final RegistryObject<Item> OVERWORLD_SHARD = ITEMS.register("overworld_shard", ObsidimancyItems::createUncommonItem);
    public static final RegistryObject<Item> NETHER_SHARD = ITEMS.register("nether_shard", ObsidimancyItems::createUncommonItem);
    public static final RegistryObject<Item> ENDER_SHARD = ITEMS.register("ender_shard", ObsidimancyItems::createUncommonItem);
    public static final RegistryObject<Item> SKYDIVERS_HOOD = ITEMS.register("skydivers_hood", SkydiversHood::new);
    public static final RegistryObject<Item> OVERWORLD_SKYDIVERS_HOOD = ITEMS.register("overworld_skydivers_hood", OverworldSkydiversHood::new);
    public static final RegistryObject<Item> NETHER_SKYDIVERS_HOOD = ITEMS.register("nether_skydivers_hood", NetherSkydiversHood::new);
    public static final RegistryObject<Item> ENDER_SKYDIVERS_HOOD = ITEMS.register("ender_skydivers_hood", EnderSkydiversHood::new);
    public static final RegistryObject<Item> PRECURSOR_PICKAXE = ITEMS.register("precursor_pickaxe", () -> new PrecursorPickaxe(Tiers.IRON,
            1,
            -2.8F,
            new Item.Properties()
                    .tab(CreativeModeTab.TAB_TOOLS)
                    .rarity(Rarity.COMMON)));
    public static final RegistryObject<DoubleHighBlockItem> ATTUNEMENT_ALTAR = ITEMS.register("attunement_altar", () -> new DoubleHighBlockItem(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get(), new Item.Properties().tab(TAB_OBSIDIMANCY)));
    public static final RegistryObject<ItemNameBlockItem> FRAGILE_OBSIDIAN = ITEMS.register("fragile_obsidian", () -> generateItemBlock(ObsidimancyBlocks.FRAGILE_OBSIDIAN.get()));
    public static final RegistryObject<Item> OVERWORLD_PRECURSOR_PICKAXE = ITEMS.register("overworld_precursor_pickaxe", ObsidimancyItems::createOverworldPrecursorPickaxe);
    public static final RegistryObject<Item> NETHER_PRECURSOR_PICKAXE = ITEMS.register("nether_precursor_pickaxe", ObsidimancyItems::createNetherPrecursorPickaxe);
    public static final RegistryObject<Item> ENDER_PRECURSOR_PICKAXE = ITEMS.register("ender_precursor_pickaxe", ObsidimancyItems::createEndPrecursorPickaxe);

    @NotNull
    private static EndPrecursorPickaxe createEndPrecursorPickaxe() {
        return new EndPrecursorPickaxe(Tiers.IRON,
                1,
                -2.8F,
                new Item.Properties()
                        .tab(ObsidimancyItems.TAB_OBSIDIMANCY)
                        .rarity(Rarity.UNCOMMON));
    }

    @NotNull
    private static OverworldPrecursorPickaxe createOverworldPrecursorPickaxe() {
        return new OverworldPrecursorPickaxe(Tiers.IRON,
                1,
                -2.8F,
                new Item.Properties()
                        .tab(ObsidimancyItems.TAB_OBSIDIMANCY)
                        .rarity(Rarity.UNCOMMON));
    }

    @NotNull
    private static NetherPrecursorPickaxe createNetherPrecursorPickaxe() {
        return new NetherPrecursorPickaxe(Tiers.IRON,
                1,
                -2.8F,
                new Item.Properties()
                        .tab(ObsidimancyItems.TAB_OBSIDIMANCY)
                        .rarity(Rarity.UNCOMMON)
                        .durability(300)
                        .fireResistant());
    }

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
