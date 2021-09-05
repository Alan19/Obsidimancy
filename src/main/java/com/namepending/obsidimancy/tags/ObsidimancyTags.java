package com.namepending.obsidimancy.tags;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.curios.api.CuriosApi;

public class ObsidimancyTags {
    public static final ITag.INamedTag<Item> BODY = ItemTags.createOptional(new ResourceLocation(CuriosApi.MODID, "body"));
    public static final ITag.INamedTag<Item> CHARM = ItemTags.createOptional(new ResourceLocation(CuriosApi.MODID, "charm"));
}
