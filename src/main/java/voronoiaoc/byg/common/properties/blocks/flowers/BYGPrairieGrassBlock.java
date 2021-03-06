package voronoiaoc.byg.common.properties.blocks.flowers;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.server.ServerWorld;
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;

public class BYGPrairieGrassBlock extends TallGrassBlock implements IGrowable, net.minecraftforge.common.IShearable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    protected BYGPrairieGrassBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == BYGBlockList.PRAIRIE_GRASS ? BYGBlockList.TALL_PRAIRIE_GRASS : BYGBlockList.TALL_PRAIRIE_GRASS);
        if (doubleplantblock.getDefaultState().isValidPosition(world, pos) && world.isAirBlock(pos.up())) {
            doubleplantblock.placeAt(world, pos, 2);
        }

    }

}
