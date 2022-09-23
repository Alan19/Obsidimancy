package com.pursuitofglowstone.obsidimancy.tags;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import top.theillusivec4.curios.api.CuriosApi;

public class ObsidimancyTags {
    public static final TagKey<Item> BODY = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "body"));
    public static final TagKey<Item> CHARM = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "charm"));
    public static final TagKey<Item> PRECURSOR_PICKAXES = ItemTags.create(new ResourceLocation(Obsidimancy.MOD_ID, "precursor_pickaxes"));
    public static final TagKey<Item> OBSIDIAN_SHARDS = ItemTags.create(new ResourceLocation(Obsidimancy.MOD_ID, "obsidian_shards"));
    public static final TagKey<Biome> HAS_ALTARS = create("has_structure/altar_ruins");

    private static TagKey<Biome> create(String pName) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Obsidimancy.MOD_ID, pName));
    }
}
