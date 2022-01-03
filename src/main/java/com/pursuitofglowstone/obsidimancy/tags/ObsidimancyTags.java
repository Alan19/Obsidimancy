package com.pursuitofglowstone.obsidimancy.tags;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

public class ObsidimancyTags {
    public static final Tag.Named<Item> BODY = ItemTags.createOptional(new ResourceLocation(CuriosApi.MODID, "body"));
    public static final Tag.Named<Item> CHARM = ItemTags.createOptional(new ResourceLocation(CuriosApi.MODID, "charm"));
    public static final Tag.Named<Item> PRECURSOR_PICKAXES = ItemTags.createOptional(new ResourceLocation(Obsidimancy.MOD_ID, "precursor_pickaxes"));
}
