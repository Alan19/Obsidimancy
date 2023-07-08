package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.overworld;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.HashMap;
import java.util.Map;

public class EdenicPrecursorPickaxe extends PrimalPrecursorPickaxe {
    public EdenicPrecursorPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        Map<Enchantment, Integer> map = new HashMap<>();
        map.put(Enchantments.BLOCK_EFFICIENCY, 1);
        map.put(Enchantments.BLOCK_FORTUNE, 2);
        map.put(Enchantments.UNBREAKING, 2);
        map.put(Enchantments.MENDING, 1);
        map.putAll(super.getEnchantments());
        return map;
    }
}
