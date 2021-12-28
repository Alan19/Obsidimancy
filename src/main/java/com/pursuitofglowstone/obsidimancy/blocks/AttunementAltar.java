package com.pursuitofglowstone.obsidimancy.blocks;


import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
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
        for (ItemEntity itemEntity : pLevel.getEntitiesOfClass(ItemEntity.class, transmuteBounds, OBSIDIAN_SHARD)) {
            itemEntity.setExtendedLifetime();
            itemEntity.setItem(attuneShard(itemEntity.getItem()));
            pLevel.sendParticles(ParticleTypes.ENCHANT, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 20, .3D, .3D, .3D, 0D);
            itemEntity.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 1F, 1F);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
        final AABB transmuteBounds = AABB.ofSize(new Vec3(pPos.getX(), pPos.getY(), pPos.getZ()), 9, 3, 9);
        for (ItemEntity itemEntity : pLevel.getEntitiesOfClass(ItemEntity.class, transmuteBounds, OBSIDIAN_SHARD)) {
            double range = .25D;
            double i = itemEntity.getX() + pRandom.nextDouble(range) - pRandom.nextDouble(range);
            double j = itemEntity.getY() + pRandom.nextDouble(range) - pRandom.nextDouble(range);
            double k = itemEntity.getZ() + pRandom.nextDouble(range) - pRandom.nextDouble(range);
            pLevel.addParticle(ParticleTypes.ENCHANT, i, j, k, 0, 2F, 0);
        }
    }

    public ItemStack attuneShard(ItemStack stack) {
        final int attuneProgress = Optional.ofNullable(stack.getTag())
                .map(compoundTag -> compoundTag.getInt("OverworldAttuneProgress"))
                .orElse(0);
        final int newProgress = attuneProgress + 1;
        if (newProgress > 2) {
            return new ItemStack(ObsidimancyItems.OVERWORLD_SHARD.get(), stack.getCount());
        }
        else {
            stack.getOrCreateTag().putInt("OverworldAttuneProgress", newProgress);
            return stack;
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }
}
