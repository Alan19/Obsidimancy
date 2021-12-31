package com.pursuitofglowstone.obsidimancy.structures;

import com.mojang.serialization.Codec;
import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AltarRuinsStructure extends StructureFeature<JigsawConfiguration> {

    public AltarRuinsStructure(Codec<JigsawConfiguration> codec) {
        super(codec, AltarRuinsStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos blockPos = context.chunkPos().getWorldPosition();
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(blockPos.getX(), blockPos.getZ(), context.heightAccessor());
        BlockState topBlock = columnOfBlocks.getBlock(landHeight);
        return topBlock.getFluidState().isEmpty();
    }

    private static @NotNull Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        if (!AltarRuinsStructure.isFeatureChunk(context)) {
            return Optional.empty();
        }
        JigsawConfiguration newConfig = new JigsawConfiguration(() -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(Obsidimancy.MOD_ID, "altar_ruins/start_pool")), 10);
        PieceGeneratorSupplier.Context<JigsawConfiguration> newContext = new PieceGeneratorSupplier.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.heightAccessor(),
                context.validBiome(),
                context.structureManager(),
                context.registryAccess()
        );
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator = JigsawPlacement.addPieces(newContext, PoolElementStructurePiece::new, blockpos, false, true);

        if (structurePiecesGenerator.isPresent()) {
            Obsidimancy.LOGGER.log(Level.DEBUG, "Altar Ruins at %s".formatted(blockpos));
        }
        return structurePiecesGenerator;
    }

    @Override
    public GenerationStep.@NotNull Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }
}
