package voronoiaoc.byg.common.world.feature.features.nether.emburbog;

import com.mojang.datafixers.Dynamic;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;
import java.util.function.Function;

public class TallEmburRoots extends Feature<NoFeatureConfig> {
    public TallEmburRoots(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
        super(config);
    }

    public static final EnumProperty<DoubleBlockHalf> HALF;

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (!worldIn.isAirBlock(pos) || worldIn.getBlockState(pos.down()).getBlock() != BYGBlockList.EMBUR_NYLIUM) {
            return false;
        } else {
            worldIn.setBlockState(pos, BYGBlockList.TALL_EMBUR_ROOTS.getDefaultState(), 10);
            worldIn.setBlockState(pos.offset(Direction.UP), BYGBlockList.TALL_EMBUR_ROOTS.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 10);
            return true;
        }
    }

    static {
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    }
}