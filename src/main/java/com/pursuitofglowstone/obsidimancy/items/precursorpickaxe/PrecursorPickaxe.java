package com.pursuitofglowstone.obsidimancy.items.precursorpickaxe;

import com.pursuitofglowstone.obsidimancy.items.enchantment.ObsidimancyEnchantments;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;

public class PrecursorPickaxe extends AbstractPrecursorPickaxe {
    public PrecursorPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
