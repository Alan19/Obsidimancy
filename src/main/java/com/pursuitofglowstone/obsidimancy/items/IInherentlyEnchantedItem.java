package com.pursuitofglowstone.obsidimancy.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IInherentlyEnchantedItem {
    Map<Enchantment, Integer> getEnchantments();

    default List<Component> getEnchantmentTooltips(boolean enchanted) {
        List<Component> components = new ArrayList<>();
        getEnchantments().forEach((enchantment, integer) -> {
            if (integer > 0) {
                components.add(enchantment.getFullname(integer));
            }
        });
        if (!enchanted) {
            components.add(new TranslatableComponent("obsidimancy.inherent_enchantment").setStyle(ObsidimancyItems.INHERENT_ENCHANT_STYLE));
        }
        return components;
    }
}
