package voronoiaoc.byg.common.world.feature.features.overworld.trees.bluff;

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

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class BluffTree3 extends BYGAbstractTreeFeature<NoFeatureConfig> {
    //Blocks used for the tree.
    private static final BlockState LOG = Blocks.SPRUCE_LOG.getDefaultState();
    private static final BlockState LEAVES = Blocks.SPRUCE_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();
    Random random = new Random();

    public BluffTree3(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn) {
        super(configIn);
        //setSapling((net.minecraftforge.common.IPlantable) BYGBlockList.ASPEN_SAPLING);
    }

    protected static boolean canTreeReplace(IWorldGenerationBaseReader genBaseReader, BlockPos blockPos) {
        return canLogPlaceHere(
                genBaseReader, blockPos
        );
    }

    public boolean place(Set<BlockPos> treeBlockSet, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling) {
//This sets heights for trees. Rand.nextint allows for tree height randomization. The final int value sets the minimum for tree Height.
        int randTreeHeight = rand.nextInt(5) + 16;
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


                }
                //This allows a random rotation between 3 differently leave Presets in the same class. Optimizes Performance instead of the loading of several classes.

                int leavessquarespos = 2;
                for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                    for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {

                        int posX2 = posX1 + 1;
                        int posZ2 = posZ1 + 1;

                        //Bottom Leaves
//                        this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
//                        this.leafs(worldIn, posX1 + posXLeafWidth - 2, topTrunkHeight + 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
//                        this.leafs(worldIn, posX1 + posXLeafWidth - 1, topTrunkHeight + 1, posZ1 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
//                        this.leafs(worldIn, posX1 + posXLeafWidth - 1, topTrunkHeight + 1, posZ1 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

                        //3x3
                        if (posXLeafWidth <= 1 && posZLeafWidthL0 <= 1 && posZLeafWidthL0 >= -1 && posXLeafWidth >= -1) {

                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 2, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 4, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 6, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 8, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 10, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 12, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 14, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        }

                        //2x3
                        if (posXLeafWidth <= 0 && posZLeafWidthL0 <= 1 && posZLeafWidthL0 >= -1 && posXLeafWidth >= -1) {

                        }

                        //Bottom Leaves
                        this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 1, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 1, posZ1 + 1, boundsIn, treeBlockSet);
                        //---

                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);

                        //------
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);
                        //---

                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 6, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 6, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 6, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 6, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 6, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 6, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 6, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 6, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 6, posZ1 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 6, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 6, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 6, posZ1 + 2, boundsIn, treeBlockSet);

                        //------
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 9, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 9, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 9, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 9, posZ1 + 1, boundsIn, treeBlockSet);
                        //---

                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 10, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 10, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 10, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 10, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 10, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 10, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 10, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 10, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 10, posZ1 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 10, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 10, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 10, posZ1 + 2, boundsIn, treeBlockSet);

                        //------
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 11, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 11, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 11, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 11, posZ1 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 13, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 13, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 13, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 13, posZ1 + 1, boundsIn, treeBlockSet);
                        //---

                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 14, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 14, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 14, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 14, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 14, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 14, posZ1 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 14, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 14, posZ1 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 14, posZ1 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 14, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 14, posZ1 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 14, posZ1 + 2, boundsIn, treeBlockSet);

                        //------
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
            int distance = 2;

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
            this.setFinalBlockState(blockPos, reader, blockpos, LEAVES, boundingBox);
        }

    }

}