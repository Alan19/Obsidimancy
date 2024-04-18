package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;

public class DisplayBuilder {
    private final String advancementName;
    private final ItemStack displayItem;
    private AdvancementType advancementType = AdvancementType.TASK;
    private boolean showToast = true;
    private boolean announceToChat = true;
    private boolean hidden = false;
    private Optional<ResourceLocation> background = Optional.empty();

    public DisplayBuilder(ItemLike displayItem, String advancementName) {
        this.advancementName = advancementName;
        this.displayItem = new ItemStack(displayItem);
    }

    public DisplayBuilder(ItemStack displayItem, String advancementName) {
        this.advancementName = advancementName;
        this.displayItem = displayItem;

    }

    public DisplayBuilder frameType(AdvancementType frameType) {
        this.advancementType = frameType;
        return this;
    }

    public DisplayBuilder showToast(boolean showToast) {
        this.showToast = showToast;
        return this;
    }

    public DisplayBuilder announceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
        return this;
    }

    public DisplayBuilder hidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public DisplayBuilder background(ResourceLocation resourceLocation) {
        background = Optional.of(resourceLocation);
        return this;
    }

    public DisplayInfo build() {
        return new DisplayInfo(displayItem, Component.translatable(Obsidimancy.MOD_ID + ".advancement." + advancementName + ".name"), Component.translatable(Obsidimancy.MOD_ID + ".advancement." + advancementName + ".desc"), background, advancementType, showToast, announceToChat, hidden);
    }
}
