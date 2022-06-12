package com.pursuitofglowstone.obsidimancy.entities;

import com.pursuitofglowstone.obsidimancy.blocks.ObsidimancyBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class PlaceFragmentGoal extends Goal {
    private final TamableAnimal mob;
    private final Block block;
    private final PathNavigation navigation;
    private LivingEntity owner;
    private float oldWaterCost;
    private int timeToRecalcPath;

    public PlaceFragmentGoal(TamableAnimal mob, Block block) {
        this.mob = mob;
        this.block = block;
        this.navigation = mob.getNavigation();

    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getOwner();
        if (livingentity == null) {
            return false;
        }
        else if (livingentity.isSpectator()) {
            return false;
        }
        else if (this.mob.isOrderedToSit()) {
            return false;
        }
        else {
            this.owner = livingentity;
            return true;
        }

    }

    @Override
    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
        this.mob.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public void tick() {
        this.mob.getLookControl().setLookAt(this.owner, 10.0F, this.mob.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            BlockPos.betweenClosedStream(AABB.ofSize(new Vec3(mob.getX(), mob.getY(), mob.getZ()), 9, 9, 9))
                    .filter(pos -> mob.level.getLightEngine().getRawBrightness(pos, 0) <= 7)
                    .findAny()
                    .ifPresent(blockPos -> this.navigation.moveTo(mob, 10F));
        }
        final BlockPos pos = this.mob.getOnPos();
        final Level level = this.mob.getLevel();
        if (level.isEmptyBlock(pos) && mob.level.getLightEngine().getRawBrightness(pos, 0) <= 7) {
            level.setBlock(pos, ObsidimancyBlocks.BRIGHT_FRAGMENT.get().defaultBlockState(), 3);
        }
    }

}
