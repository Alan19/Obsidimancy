package com.pursuitofglowstone.obsidimancy.blocks;


import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Predicate;

public class AttunementAltar extends Block {

    public static final Predicate<ItemEntity> OBSIDIAN_SHARD = itemEntity -> itemEntity.getItem().getItem() == ObsidimancyItems.OBSIDIAN_SHARD.get();

    public AttunementAltar() {
        super(Properties.of(Material.STONE));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        final AABB transmuteBounds = AABB.ofSize(new Vec3(pPos.getX(), pPos.getY(), pPos.getZ()), 9, 3, 9);
        pLevel.getEntitiesOfClass(ItemEntity.class, transmuteBounds, OBSIDIAN_SHARD)
                .forEach(itemEntity -> {
                    itemEntity.setItem(new ItemStack(ObsidimancyItems.OVERWORLD_SHARD.get(), itemEntity.getItem().getCount()));
                    pLevel.sendParticles(ParticleTypes.ENCHANT,
                            itemEntity.getX(),
                            itemEntity.getY(),
                            itemEntity.getZ(),
                            4,
                            .5,
                            .5,
                            .5,
                            0);
                });
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }
}
