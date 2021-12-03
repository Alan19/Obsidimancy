package com.namepending.obsidimancy.items;

import com.namepending.obsidimancy.Obsidimancy;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class ObsidimancyItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Obsidimancy.MOD_ID);
    public static final CreativeModeTab TAB_OBSIDIMANCY = new CreativeModeTab("obsidimancy") {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(OBSIDIAN_SHARD.get());
        }
    };

    public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", () -> new Item(new Item.Properties().tab(ObsidimancyItems.TAB_OBSIDIMANCY)));
    public static final RegistryObject<Item> SKYDIVERS_HOOD = ITEMS.register("skydivers_hood", SkydiversHood::new);
    public static final RegistryObject<Item> PRECURSOR_PICKAXE = ITEMS.register("precursor_pickaxe", PrecursorPickaxe::new);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
