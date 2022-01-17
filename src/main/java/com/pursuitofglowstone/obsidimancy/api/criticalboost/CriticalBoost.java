package com.pursuitofglowstone.obsidimancy.api.criticalboost;

import net.minecraft.nbt.FloatTag;

public class CriticalBoost implements ICriticalBoost {
    private float bonusDamage;

    @Override
    public float getBonusCritical() {
        return bonusDamage;
    }

    @Override
    public void setBonusCriticalDamage(float bonusCriticalDamage) {
        bonusDamage = bonusCriticalDamage;
    }

    @Override
    public FloatTag serializeNBT() {
        return FloatTag.valueOf(bonusDamage);
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        bonusDamage = nbt.getAsFloat();
    }
}
