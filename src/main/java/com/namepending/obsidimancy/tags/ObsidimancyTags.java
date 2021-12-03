package com.namepending.obsidimancy.tags;

import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import top.theillusivec4.curios.api.CuriosApi;

public class ObsidimancyTags {
    public static final Tag.Named<Item> BODY = ItemTags.createOptional(new ResourceLocation(CuriosApi.MODID, "body"));
    public static final Tag.Named<Item> CHARM = ItemTags.createOptional(new ResourceLocation(CuriosApi.MODID, "charm"));
}
