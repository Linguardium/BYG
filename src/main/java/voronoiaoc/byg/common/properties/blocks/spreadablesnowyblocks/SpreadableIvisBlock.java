package voronoiaoc.byg.common.properties.blocks.spreadablesnowyblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SpreadableIvisBlock extends Block {
    public SpreadableIvisBlock(Properties properties) {
        super(properties);
    }

    private static boolean func_220257_b(BlockState state, IWorldReader worldReader, BlockPos blockPos) {
        BlockPos blockpos = blockPos.up();
        BlockState blockstate = worldReader.getBlockState(blockpos);
        {
            int i = LightEngine.func_215613_a(worldReader, state, blockPos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(worldReader, blockpos));
            return i < worldReader.getMaxLightLevel();
        }
    }


    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!func_220257_b(state, worldIn, pos)) {
            if (!worldIn.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
        } else {
            if (worldIn.getLight(pos.up()) >= 9) {
                BlockState blockstate = this.getDefaultState();

                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                    if (worldIn.getBlockState(blockpos).getBlock() == Blocks.END_STONE) {
                    }
                }
            }

        }
    }
}
