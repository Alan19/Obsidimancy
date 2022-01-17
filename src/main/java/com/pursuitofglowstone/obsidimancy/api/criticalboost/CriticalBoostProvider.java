package com.pursuitofglowstone.obsidimancy.api.criticalboost;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.api.ObsidimancyCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.FloatTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CriticalBoostProvider implements ICapabilitySerializable<FloatTag> {
    public static final ResourceLocation KEY = new ResourceLocation(Obsidimancy.MOD_ID, "critical_boost");
    private final CriticalBoost capability;
    private final LazyOptional<ICriticalBoost> optional;

    public CriticalBoostProvider() {
        this(new CriticalBoost());
    }

    public CriticalBoostProvider(CriticalBoost criticalBoost) {
        this.capability = criticalBoost;
        this.optional = LazyOptional.of(() -> capability);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ObsidimancyCapabilities.CRITICAL_BOOST_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public FloatTag serializeNBT() {
        return capability.serializeNBT();
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        capability.deserializeNBT(nbt);
    }
}
