package com.pursuitofglowstone.obsidimancy.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class BrightFragment extends Block {
    public BrightFragment() {
        super(BlockBehaviour.Properties.of(Material.DECORATION).lightLevel(value -> 7));
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
        pLevel.destroyBlock(pPos, false);
    }
}
