package com.pursuitofglowstone.obsidimancy.api;

import com.pursuitofglowstone.obsidimancy.api.criticalboost.CriticalBoost;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ObsidimancyCapabilities {
    public static final Capability<CriticalBoost> CRITICAL_BOOST_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
}
