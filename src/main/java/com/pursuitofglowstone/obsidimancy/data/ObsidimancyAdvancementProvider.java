package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.TickTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ObsidimancyAdvancementProvider extends AdvancementProvider {
    private Advancement root;
    private Advancement getObsidianShard;

    public ObsidimancyAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(@NotNull Consumer<Advancement> consumer, @NotNull ExistingFileHelper fileHelper) {
        root = Advancement.Builder.advancement()
                .display(new DisplayBuilder(ObsidimancyItems.FRAGILE_OBSIDIAN.get(), "root")
                        .hidden(true)
                        .showToast(false)
                        .announceToChat(false)
                        .background(new ResourceLocation("obsidimancy:textures/block/fragile_obsidian.png"))
                        .build())
                .addCriterion("tick", new TickTrigger.TriggerInstance(EntityPredicate.Composite.ANY))
                .save(consumer, "obsidimancy:root");
        getObsidianShard = Advancement.Builder.advancement()
                .display(new DisplayBuilder(ObsidimancyItems.OBSIDIAN_SHARD.get(), "get_obsidian_shard").build())
                .addCriterion("get_shard", InventoryChangeTrigger.TriggerInstance.hasItems(ObsidimancyItems.OBSIDIAN_SHARD.get()))
                .save(consumer, "get_obsidian_shard");
    }
}
