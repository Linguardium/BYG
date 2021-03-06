package voronoiaoc.byg.common.world.feature.features.overworld.trees.skyris;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import voronoiaoc.byg.common.world.feature.features.overworld.trees.util.BYGAbstractTreeFeature;
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SkyrisTree3 extends BYGAbstractTreeFeature<NoFeatureConfig> {
    //Blocks used for the tree.
    private static final BlockState LOG = BYGBlockList.SKYRIS_LOG.getDefaultState();
    private static final BlockState LEAVES = BYGBlockList.SKYRIS_LEAVES.getDefaultState();
    private static final BlockState LEAVES2 = BYGBlockList.SKYRIS_LEAVES_GREEN_APPLE.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();
    Random random = new Random();

    public SkyrisTree3(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn) {
        super(configIn);
        //setSapling((net.minecraftforge.common.IPlantable) BYGBlockList.SKYRIS_SAPLING);
    }

    protected static boolean canTreeReplace(IWorldGenerationBaseReader genBaseReader, BlockPos blockPos) {
        return canLogPlaceHere(
                genBaseReader, blockPos
        );
    }

    public boolean place(Set<BlockPos> treeBlockSet, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling) {
//This sets heights for trees. Rand.nextint allows for tree height randomization. The final int value sets the minimum for tree Height.
        int randTreeHeight = rand.nextInt(2) + 6;
        //Positions
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        if (posY >= 1 && posY + randTreeHeight + 1 < worldIn.getMaxHeight()) {
            if (!isDesiredGroundwDirtTag(worldIn, pos.offset(Direction.DOWN), Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.isAnotherTreeNearby(worldIn, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isSapling)) {
                return false;
            } else if (!this.doesTreeFit(worldIn, pos, randTreeHeight)) {
                return false;
            } else {
                //Places dirt under logs where/when necessary.

                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int randTreeHeight2 = randTreeHeight - rand.nextInt(1);//Crashes on 0.
                int posY1 = 2 - rand.nextInt(1);//Crashes on 0.
                int posX1 = posX;
                int posZ1 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;
                int topTrunkHeight2 = posY + randTreeHeight + randTreeHeight - 1;
                int topTrunkHeight3 = posY + randTreeHeight + randTreeHeight + 8;
                int topTrunkHeight4 = posY + randTreeHeight + randTreeHeight + 10;
                int topTrunkHeight5 = posY + randTreeHeight + randTreeHeight + 11;

                //Raising the 'groundUpLogRemover'  will remove all log blocks from the ground up no matter how thick the trunk is based on the value given. 5 would destroy all trunks from 5 up off the ground.
                for (int groundUpLogRemover = 0; groundUpLogRemover < randTreeHeight; ++groundUpLogRemover) {
                    if (groundUpLogRemover >= randTreeHeight2 && posY1 < 0) { //Unknown
                        posX1 += direction.getXOffset();
                        posZ1 += direction.getZOffset();
                        ++posY1;
                    }
                    //This Int is responsible for the Y coordinate of the trunk BlockPos'.
                    int logplacer = posY + groundUpLogRemover;
                    int logplacer2 = posY + randTreeHeight;
                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);
                    BlockPos blockpos2 = new BlockPos(posX1, logplacer2, posZ1);

                    //Sets Logs
                    this.treelog(treeBlockSet, worldIn, blockpos1, boundsIn);

                    this.treelog(treeBlockSet, worldIn, blockpos2.east(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(2).up(), boundsIn);

                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(2), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(3), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(4), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(5), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(6), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(4).up(6), boundsIn);


                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(7).south(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(8).south(2), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(9).south(2), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.east(3).up(10).south(2), boundsIn);

                    this.treelog(treeBlockSet, worldIn, blockpos2.south().up(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.south(2).up(2), boundsIn);

                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west().up(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west().up(2), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west().up(3), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west().up(4), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west().up(5), boundsIn);

                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west(2).up(6), boundsIn);


                    this.treelog(treeBlockSet, worldIn, blockpos2.north(2).up(4), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north(2).up(5), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north(2).up(6), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north(2).up(7), boundsIn);

                    this.treelog(treeBlockSet, worldIn, blockpos2.north(3).up(7), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north(4).up(8), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north(5).up(9), boundsIn);

                    this.treelog(treeBlockSet, worldIn, blockpos2.north(2).up(8).west(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north(2).up(9).west(2), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north(2).up(10).west(3), boundsIn);

                }
                //This allows a random rotation between 3 differently leave Presets in the same class. Optimizes Performance instead of the loading of several classes.

                int leavessquarespos = 2;
                for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                    for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {

                        int posX2 = posX1;
                        int posZ2 = posZ1 + 2;

                        int posX3 = posX1 + 1;
                        int posZ3 = posZ1 - 5;

                        int posX4 = posX1 + 5;
                        int posZ4 = posZ1;

                        int posX5 = posX1 + 4;
                        int posZ5 = posZ1;

                        int posX6 = posX1 - 1;
                        int posZ6 = posZ1 - 1;

                        int posX7 = posX1 + 4;
                        int posZ7 = posZ1 + 2;

                        int posX8 = posX1 - 2;
                        int posZ8 = posZ1 - 2;

                        //Top Leaves
                        this.leafs(worldIn, posX4 + posXLeafWidth - 2, topTrunkHeight4 - 10, posZ4 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX4 + posXLeafWidth, topTrunkHeight4 - 10, posZ4 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX4 + posXLeafWidth - 1, topTrunkHeight4 - 10, posZ4 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX4 + posXLeafWidth - 1, topTrunkHeight4 - 10, posZ4 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

//                        Middle Leaves
                        this.leafs(worldIn, posX5 + posXLeafWidth - 2, topTrunkHeight5 - 15, posZ5 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX5 + posXLeafWidth, topTrunkHeight5 - 15, posZ5 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX5 + posXLeafWidth - 1, topTrunkHeight5 - 15, posZ5 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX5 + posXLeafWidth - 1, topTrunkHeight5 - 15, posZ5 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

//                        Bottom Leaves
                        this.leafs(worldIn, posX2 + posXLeafWidth - 2, topTrunkHeight3 - 12, posZ2 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafWidth, topTrunkHeight3 - 12, posZ2 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafWidth - 1, topTrunkHeight3 - 12, posZ2 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafWidth - 1, topTrunkHeight3 - 12, posZ2 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3 - 1 + posXLeafWidth, topTrunkHeight3 - 5, posZ3 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 1 + posXLeafWidth, topTrunkHeight3 - 5, posZ3 + posZLeafWidthL0, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX6 + posXLeafWidth - 2, topTrunkHeight5 - 11, posZ6 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX6 + posXLeafWidth, topTrunkHeight5 - 11, posZ6 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX6 + posXLeafWidth - 1, topTrunkHeight5 - 11, posZ6 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX6 + posXLeafWidth - 1, topTrunkHeight5 - 11, posZ6 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX7 + posXLeafWidth - 2, topTrunkHeight5 - 7, posZ7 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX7 + posXLeafWidth, topTrunkHeight5 - 7, posZ7 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX7 + posXLeafWidth - 1, topTrunkHeight5 - 7, posZ7 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX7 + posXLeafWidth - 1, topTrunkHeight5 - 7, posZ7 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX8 + posXLeafWidth - 2, topTrunkHeight5 - 7, posZ8 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX8 + posXLeafWidth, topTrunkHeight5 - 7, posZ8 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX8 + posXLeafWidth - 1, topTrunkHeight5 - 7, posZ8 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX8 + posXLeafWidth - 1, topTrunkHeight5 - 7, posZ8 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

                        //3x3
                        if (posXLeafWidth <= 1 && posZLeafWidthL0 <= 1 && posZLeafWidthL0 >= -1 && posXLeafWidth >= -1) {

                            this.leafs(worldIn, posX4 + posXLeafWidth - 1, topTrunkHeight4 - 9, posZ4 + posZLeafWidthL0, boundsIn, treeBlockSet); //Top

                            this.leafs(worldIn, posX5 + posXLeafWidth - 1, topTrunkHeight5 - 14, posZ5 + posZLeafWidthL0, boundsIn, treeBlockSet); //Middle

                            this.leafs(worldIn, posX2 + posXLeafWidth - 1, topTrunkHeight3 - 11, posZ2 + posZLeafWidthL0, boundsIn, treeBlockSet); //Bottom

                            this.leafs(worldIn, posX3 + posXLeafWidth - 1, topTrunkHeight3 - 4, posZ3 + posZLeafWidthL0, boundsIn, treeBlockSet); //Bottom

                            this.leafs(worldIn, posX6 + posXLeafWidth - 1, topTrunkHeight5 - 10, posZ6 + posZLeafWidthL0, boundsIn, treeBlockSet); //Middle

                            this.leafs(worldIn, posX7 + posXLeafWidth - 1, topTrunkHeight5 - 6, posZ7 + posZLeafWidthL0, boundsIn, treeBlockSet); //Middle

                            this.leafs(worldIn, posX8 + posXLeafWidth - 1, topTrunkHeight5 - 6, posZ8 + posZLeafWidthL0, boundsIn, treeBlockSet); //Middle


                        }

                        //2x3
                        if (posXLeafWidth <= 0 && posZLeafWidthL0 <= 1 && posZLeafWidthL0 >= -1 && posXLeafWidth >= -1) {

                        }


                        //Top Leaves
                        this.leafs(worldIn, posX4 - 1, topTrunkHeight4 - 9, posZ4 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX4 - 3, topTrunkHeight4 - 9, posZ4, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX4 + 1, topTrunkHeight4 - 9, posZ4, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX4 - 1, topTrunkHeight4 - 9, posZ4 - 2, boundsIn, treeBlockSet);

                        //Middle Leaves
                        this.leafs(worldIn, posX5 - 1, topTrunkHeight5 - 14, posZ5 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX5 - 3, topTrunkHeight5 - 14, posZ5, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX5 + 1, topTrunkHeight5 - 14, posZ5, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX5 - 1, topTrunkHeight5 - 14, posZ5 - 2, boundsIn, treeBlockSet);

                        //Bottom Leaves
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight3 - 11, posZ2 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 3, topTrunkHeight3 - 11, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight3 - 11, posZ2 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight3 - 11, posZ2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3 - 1, topTrunkHeight3 - 4, posZ3 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 3, topTrunkHeight3 - 4, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 1, topTrunkHeight3 - 4, posZ3 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight3 - 4, posZ3, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX6 - 1, topTrunkHeight5 - 10, posZ6 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX6 - 3, topTrunkHeight5 - 10, posZ6, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX6 + 1, topTrunkHeight5 - 10, posZ6, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX6 - 1, topTrunkHeight5 - 10, posZ6 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX7 - 1, topTrunkHeight5 - 6, posZ7 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX7 - 3, topTrunkHeight5 - 6, posZ7, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX7 + 1, topTrunkHeight5 - 6, posZ7, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX7 - 1, topTrunkHeight5 - 6, posZ7 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX8 - 1, topTrunkHeight5 - 6, posZ8 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX8 - 3, topTrunkHeight5 - 6, posZ8, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX8 + 1, topTrunkHeight5 - 6, posZ8, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX8 - 1, topTrunkHeight5 - 6, posZ8 - 2, boundsIn, treeBlockSet);

                    }
                }
            }

            return true;
            //}
        } else {
            return false;
        }
    }

    private boolean doesTreeFit(IWorldGenerationBaseReader reader, BlockPos blockPos, int height) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int yOffset = 0; yOffset <= height + 1; ++yOffset) {
            //Distance/Density of trees. Positive Values ONLY
            int distance = 4;

            for (int xOffset = -distance; xOffset <= distance; ++xOffset) {
                for (int zOffset = -distance; zOffset <= distance; ++zOffset) {
                    if (!canTreeReplace(reader, pos.setPos(x + xOffset, y + yOffset, z + zOffset))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Log Placement
    private void treelog(Set<BlockPos> setlogblock, IWorldGenerationReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canTreeReplace(reader, pos)) {
            this.setFinalBlockState(setlogblock, reader, pos, LOG, boundingBox);
        }

    }

    //Leaves Placement
    private void leafs(IWorldGenerationReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, reader, blockpos, this.randomizer(), boundingBox);
        }

    }

    private BlockState randomizer() {
        return (random.nextInt(25) == 0) ? LEAVES2 : LEAVES;
    }
}