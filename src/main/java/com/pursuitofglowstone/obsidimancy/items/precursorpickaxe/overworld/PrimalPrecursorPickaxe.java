package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.overworld;

import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.AbstractPrecursorPickaxe;
import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.PrecursorPickaxe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class PrimalPrecursorPickaxe extends PrecursorPickaxe {

    public PrimalPrecursorPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        Map<Enchantment, Integer> map = new HashMap<>();
        map.put(Enchantments.BLOCK_FORTUNE, 1);
        map.putAll(super.getEnchantments());
        return map;
    }
}
