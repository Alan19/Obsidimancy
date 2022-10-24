package com.pursuitofglowstone.obsidimancy.blocks;


import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AttunementAltar extends Block {

    public static final Predicate<ItemEntity> OBSIDIAN_SHARD = itemEntity -> itemEntity.getItem().getItem() == ObsidimancyItems.OBSIDIAN_SHARD.get();
    public static final String NETHER_ATTUNE_PROGRESS = "NetherAttuneProgress";
    public static final String END_ATTUNE_PROGRESS = "EndAttuneProgress";
    public static final String OVERWORLD_ATTUNE_PROGRESS = "OverworldAttuneProgress";

    public static final VoxelShape LOWER_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(1, 2, 1, 6, 3, 15),
            Block.box(10, 2, 1, 15, 3, 15),
            Block.box(6, 2, 1, 10, 3, 6),
            Block.box(6, 2, 10, 10, 3, 15),
            Block.box(0, 2, 0, 2, 6, 2),
            Block.box(14, 2, 0, 16, 6, 2),
            Block.box(0, 2, 14, 2, 6, 16),
            Block.box(14, 2, 14, 16, 6, 16),
            Block.box(0, 2, 5, 1, 3, 6),
            Block.box(15, 2, 5, 16, 3, 6),
            Block.box(10, 2, 15, 11, 3, 16),
            Block.box(10, 2, 0, 11, 3, 1),
            Block.box(0, 3, 5, 2, 4, 6),
            Block.box(14, 3, 5, 16, 4, 6),
            Block.box(10, 3, 14, 11, 4, 16),
            Block.box(10, 3, 0, 11, 4, 2),
            Block.box(0, 2, 10, 1, 3, 11),
            Block.box(15, 2, 10, 16, 3, 11),
            Block.box(5, 2, 15, 6, 3, 16),
            Block.box(5, 2, 0, 6, 3, 1),
            Block.box(0, 3, 10, 2, 4, 11),
            Block.box(14, 3, 10, 16, 4, 11),
            Block.box(5, 3, 14, 6, 4, 16),
            Block.box(5, 3, 0, 6, 4, 2)
    ).reduce(Shapes::or).get();

    public static final VoxelShape TOP_SHAPE = Stream.of(
            Block.box(2, -1, -1, 14, 1, 1),
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(18, 14, 14, 18, 18, 18),
            Block.box(-2, 14, 14, 2, 18, 18),
            Block.box(14, 14, 14, 18, 18, 18),
            Block.box(-2, 14, -2, 2, 18, 2),
            Block.box(-2, -2, -2, 2, 2, 2),
            Block.box(14, -2, -2, 18, 2, 2),
            Block.box(14, -2, 14, 18, 2, 18),
            Block.box(-2, -2, 14, 2, 2, 18),
            Block.box(14, 14, -2, 18, 18, 2),
            Block.box(-1, 2, 15, 1, 14, 17),
            Block.box(15, 2, 15, 17, 14, 17),
            Block.box(15, 2, -1, 17, 14, 1),
            Block.box(-1, 2, -1, 1, 14, 1),
            Block.box(-1, 15, 2, 1, 17, 14),
            Block.box(15, 15, 2, 17, 17, 14),
            Block.box(15, -1, 2, 17, 1, 14),
            Block.box(-1, -1, 2, 1, 1, 14),
            Block.box(2, -1, 15, 14, 1, 17),
            Block.box(2, -1, -1, 14, 1, 1),
            Block.box(2, 15, 15, 14, 17, 17),
            Block.box(2, -1, -1, 14, 1, 1),
            Block.box(2, 15, -1, 14, 17, 1)
    ).reduce(Shapes::or).get();

    public AttunementAltar() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().noOcclusion().strength(5.0F, 1200.0F));
    }

    public static BlockState copyWaterloggedFrom(LevelReader levelReader, BlockPos pos, @NotNull BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, levelReader.isWaterAt(pos)) : state;
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
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        DoubleBlockHalf doubleblockhalf = pState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
        if (doubleblockhalf == DoubleBlockHalf.LOWER) {
            return LOWER_SHAPE;
        }
        else {
            return TOP_SHAPE;
        }
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BlockStateProperties.DOUBLE_BLOCK_HALF);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
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
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        final AABB transmuteBounds = AABB.ofSize(new Vec3(pPos.getX(), pPos.getY(), pPos.getZ()), 9, 3, 9);
        for (ItemEntity itemEntity : pLevel.getEntitiesOfClass(ItemEntity.class, transmuteBounds, OBSIDIAN_SHARD)) {
            double range = .25D;
            double i = pRandom.triangle(itemEntity.getX(), range);
            double j = pRandom.triangle(itemEntity.getY(), range);
            double k = pRandom.triangle(itemEntity.getZ(), range);
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
