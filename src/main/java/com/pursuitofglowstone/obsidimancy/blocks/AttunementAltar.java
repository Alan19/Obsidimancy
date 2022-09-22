package com.pursuitofglowstone.obsidimancy.blocks;


import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public class AttunementAltar extends Block {

    public static final Predicate<ItemEntity> OBSIDIAN_SHARD = itemEntity -> itemEntity.getItem().getItem() == ObsidimancyItems.OBSIDIAN_SHARD.get();
    public static final String NETHER_ATTUNE_PROGRESS = "NetherAttuneProgress";
    public static final String END_ATTUNE_PROGRESS = "EndAttuneProgress";
    public static final String OVERWORLD_ATTUNE_PROGRESS = "OverworldAttuneProgress";

    public AttunementAltar() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 1200.0F));
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public @NotNull BlockState updateShape(BlockState pState, Direction pFacing, @NotNull BlockState pFacingState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pCurrentPos, @NotNull BlockPos pFacingPos) {
        DoubleBlockHalf doubleblockhalf = pState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
        if (pFacing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (pFacing == Direction.UP) || pFacingState.is(this) && pFacingState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && pFacing == Direction.DOWN && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(pContext) ? defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER) : null;
    }

    /**
     * Called by BlockItem after this block has been placed.
     */
    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, @NotNull BlockState pState, LivingEntity pPlacer, @NotNull ItemStack pStack) {
        BlockPos blockpos = pPos.above();
        pLevel.setBlock(blockpos, copyWaterloggedFrom(pLevel, blockpos, this.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)), 3);
    }

    @Override
    public boolean canSurvive(BlockState pState, @NotNull LevelReader pLevel, @NotNull BlockPos pPos) {
        if (pState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.UPPER) {
            return super.canSurvive(pState, pLevel, pPos);
        } else {
            BlockState blockstate = pLevel.getBlockState(pPos.below());
            if (pState.getBlock() != this) return super.canSurvive(pState, pLevel, pPos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return blockstate.is(this) && blockstate.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER;
        }
    }

    public static BlockState copyWaterloggedFrom(LevelReader p_182454_, BlockPos p_182455_, BlockState p_182456_) {
        return p_182456_.hasProperty(BlockStateProperties.WATERLOGGED) ? p_182456_.setValue(BlockStateProperties.WATERLOGGED, p_182454_.isWaterAt(p_182455_)) : p_182456_;
    }

    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     */
    @Override
    public void playerWillDestroy(Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Player pPlayer) {
        if (!pLevel.isClientSide) {
            if (pPlayer.isCreative()) {
                preventCreativeDropFromBottomPart(pLevel, pPos, pState, pPlayer);
            } else {
                dropResources(pState, pLevel, pPos, (BlockEntity)null, pPlayer, pPlayer.getMainHandItem());
            }
        }

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    /**
     * Called after a player has successfully harvested this block. This method will only be called if the player has
     * used the correct tool and drops should be spawned.
     */
    @Override
    public void playerDestroy(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable BlockEntity pTe, @NotNull ItemStack pStack) {
        super.playerDestroy(pLevel, pPlayer, pPos, Blocks.AIR.defaultBlockState(), pTe, pStack);
    }

    protected static void preventCreativeDropFromBottomPart(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        DoubleBlockHalf doubleblockhalf = pState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pPos.below();
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (blockstate.is(pState.getBlock()) && blockstate.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                pLevel.setBlock(blockpos, blockstate1, 35);
                pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
            }
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BlockStateProperties.DOUBLE_BLOCK_HALF);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        final AABB transmuteBounds = AABB.ofSize(new Vec3(pPos.getX(), pPos.getY(), pPos.getZ()), 9, 3, 9);
        for (ItemEntity itemEntity : pLevel.getEntitiesOfClass(ItemEntity.class, transmuteBounds, OBSIDIAN_SHARD)) {
            itemEntity.setExtendedLifetime();
            itemEntity.setItem(attuneShard(itemEntity.getItem(), pLevel.getBiome(pPos).value().getPrecipitation()));
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

    public ItemStack attuneShard(ItemStack stack, Biome.Precipitation precipitation) {
        double attuneProgress;
        Item resultItem;
        String tagName;
        switch (precipitation) {
            case RAIN -> {
                attuneProgress = Optional.ofNullable(stack.getTag())
                        .map(compoundTag -> compoundTag.getDouble(OVERWORLD_ATTUNE_PROGRESS))
                        .orElse(0D);
                resultItem = ObsidimancyItems.OVERWORLD_SHARD.get();
                tagName = OVERWORLD_ATTUNE_PROGRESS;
            }
            case SNOW -> {
                attuneProgress = Optional.ofNullable(stack.getTag())
                        .map(compoundTag -> compoundTag.getDouble(END_ATTUNE_PROGRESS))
                        .orElse(0D);
                resultItem = ObsidimancyItems.ENDER_SHARD.get();
                tagName = END_ATTUNE_PROGRESS;
            }
            case NONE -> {
                attuneProgress = Optional.ofNullable(stack.getTag())
                        .map(compoundTag -> compoundTag.getDouble(NETHER_ATTUNE_PROGRESS))
                        .orElse(0D);
                resultItem = ObsidimancyItems.NETHER_SHARD.get();
                tagName = NETHER_ATTUNE_PROGRESS;
            }
            default -> throw new IllegalArgumentException();
        }
        final double newProgress = attuneProgress + 1;
        if (newProgress > 2) {
            return new ItemStack(resultItem, stack.getCount());
        }
        else {
            stack.getOrCreateTag().putDouble(tagName, newProgress);
            return stack;
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }
}
