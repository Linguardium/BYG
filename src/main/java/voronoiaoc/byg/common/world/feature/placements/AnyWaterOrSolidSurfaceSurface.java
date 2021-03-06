package voronoiaoc.byg.common.world.feature.placements;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AnyWaterOrSolidSurfaceSurface extends Placement<FrequencyConfig> {
    public static final Placement<FrequencyConfig> WWATERORSOLIDSURFACE = new AnyWaterOrSolidSurfaceSurface(FrequencyConfig::deserialize);

    public AnyWaterOrSolidSurfaceSurface(Function<Dynamic<?>, ? extends FrequencyConfig> config) {
        super(config);
    }


    public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, FrequencyConfig config, BlockPos pos) {
        return IntStream.range(0, config.count).mapToObj((obj) -> {
            int x = random.nextInt(16) + pos.getX();
            int z = random.nextInt(16) + pos.getZ();

            BlockPos.Mutable mutable = new BlockPos.Mutable(x, 255, z);
            while (mutable.getY() > 0 &&
                    !world.getBlockState(mutable).isSolid() &&
                    world.getBlockState(mutable).getFluidState().isEmpty())
                mutable.move(Direction.DOWN);

            return mutable.toImmutable();
        });
    }
}