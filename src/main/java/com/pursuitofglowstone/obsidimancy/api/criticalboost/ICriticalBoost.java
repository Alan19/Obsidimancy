package com.pursuitofglowstone.obsidimancy.api.criticalboost;

import net.minecraft.nbt.FloatTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public interface ICriticalBoost extends INBTSerializable<FloatTag> {
    float getBonusCritical();

    void setBonusCriticalDamage(float bonusCriticalDamage);
}
