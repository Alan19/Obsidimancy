package com.pursuitofglowstone.obsidimancy;

import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import com.pursuitofglowstone.obsidimancy.items.enchantment.ObsidimancyEnchantments;
import com.pursuitofglowstone.obsidimancy.loot.ObsidimancyLootModifiers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Obsidimancy.MOD_ID)
public class Obsidimancy
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "obsidimancy";

    public Obsidimancy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerCurios);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setRenderTypes);
        MinecraftForge.EVENT_BUS.register(this);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ObsidimancyBlocks.register(modEventBus);
        ObsidimancyItems.register(modEventBus);
        ObsidimancyLootModifiers.register(modEventBus);
        ObsidimancyEnchantments.register(modEventBus);
    }

    private void registerCurios(final InterModEnqueueEvent event) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("body").build());
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("charm").build());
    }

    private void setRenderTypes(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ObsidimancyBlocks.ATTUNEMENT_ALTAR.get(), RenderType.cutoutMipped());
    }

}
