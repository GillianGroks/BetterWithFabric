package com.github.gilliangroks.betterwithfabric.block;

import com.github.gilliangroks.betterwithfabric.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class HempBlock extends CropBlock {

    public static final IntProperty AGE = Properties.AGE_7;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(5.5D, 0.0D, 5.5D, 9.5D, 3.0D, 9.5D),
            Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 5.0D, 9.5D),
            Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 7.0D, 9.5D),
            Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 12.0D, 9.5D),
            Block.createCuboidShape(4.5D, 0.0D, 4.5D, 11.5D, 14.0D, 11.5D),
            Block.createCuboidShape(3.5D, 0.0D, 3.5D, 12.5D, 15.0D, 12.5D),
            Block.createCuboidShape(2.5D, 0.0D, 2.5D, 13.5D, 16.0D, 13.5D),
            Block.createCuboidShape(2.5D, 0.0D, 2.5D, 13.5D, 16.0D, 13.50D)
    };

    public HempBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    protected ItemConvertible getSeedsItem() {
        return ItemRegistry.HEMP_SEEDS;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        float f = CropBlock.getAvailableMoisture(this, world, pos);
        int currentAge = this.getAge(state);
        int lightLevel = world.getBaseLightLevel(pos, 0);

        if (lightLevel >= 11) {
            if (currentAge < 7 && random.nextInt((int) (25.0f / f) + 1) == 0) {
                world.setBlockState(pos, this.withAge(currentAge + 1), 2);
            }
            if (this.getCropHeight(world, pos) < 3 && currentAge == 7 && world.isAir(pos.up())) {
                world.setBlockState(pos.up(1), state.with(AGE, 0), 3);
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, @NotNull WorldView world, BlockPos pos) {
        if (getCropHeight(world, pos) > 1 && world.getBlockState(pos.down(1)).isAir()) {
            return false;
        }
        return ((world.getBaseLightLevel(pos, 0) >= 11 || world.getBlockState(pos.up(1)).isAir()) && getCropHeight(world, pos) < 3 );
    }

    @Override
    protected boolean canPlantOnTop(@NotNull BlockState state, BlockView world, BlockPos pos) {

        return state.isOf(Blocks.FARMLAND);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return this.randomTicks;
    }

    protected int getCropHeight(@NotNull BlockView world, @NotNull BlockPos pos) {
        int cropHeight = 1;
        while (!world.getBlockState(pos.down(cropHeight)).isOf(Blocks.FARMLAND)) {
            ++cropHeight;
        }
        return cropHeight;
    }

    protected int getCropHeight(@NotNull ServerWorld world, @NotNull BlockPos pos) {
        int cropHeight = 1;
        while (!world.getBlockState(pos.down(cropHeight)).isOf(Blocks.FARMLAND)) {
            ++cropHeight;
        }
        return cropHeight;
    }

}
