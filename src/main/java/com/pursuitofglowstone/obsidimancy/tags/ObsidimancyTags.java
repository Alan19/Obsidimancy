package com.pursuitofglowstone.obsidimancy.tags;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import top.theillusivec4.curios.api.CuriosApi;

public class ObsidimancyTags {
    public static final TagKey<Item> BODY = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "body"));
    public static final TagKey<Item> CHARM = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "charm"));
    public static final TagKey<Item> PRECURSOR_PICKAXES = ItemTags.create(new ResourceLocation(Obsidimancy.MOD_ID, "precursor_pickaxes"));
    public static final TagKey<Item> OBSIDIAN_SHARDS = ItemTags.create(new ResourceLocation(Obsidimancy.MOD_ID, "obsidian_shards"));
    public static final TagKey<Block> MINEABLE_WITH_PICKAXE_OR_SHOVEL = BlockTags.create(new ResourceLocation(Obsidimancy.MOD_ID, "mineable_with_pickaxe_or_shovel"));

    private static TagKey<Biome> create(String pName) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Obsidimancy.MOD_ID, pName));
    }
}
