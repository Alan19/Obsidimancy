package com.pursuitofglowstone.obsidimancy.tags;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

public class ObsidimancyTags {
    public static final TagKey<Item> BODY = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "body"));
    public static final TagKey<Item> CHARM = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "charm"));
    public static final TagKey<Item> PRECURSOR_PICKAXES = ItemTags.create(new ResourceLocation(Obsidimancy.MOD_ID, "precursor_pickaxes"));
}
