package diversity.world;

import java.util.Random;

import diversity.suppliers.EnumBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBlueMushroom extends WorldGenerator
{
	/** The mushroom type. 0 for brown, 1 for red. */

	public boolean generate(World world, Random random, int x, int y, int z)
	{
        int i1 = random.nextInt(2) + 4;
        boolean flag = true;

        if (y >= 1 && y + i1 + 1 < 256)
        {
            int tempY;
            int tempX;
            int tempZ;
            
            Block block;

            for (tempY = y; tempY <= y + 1 + i1; ++tempY)
            {
                byte b0 = 3;

                if (tempY <= y + 2)
                {
                    b0 = 0;
                }

                for (tempX = x - b0; tempX <= x + b0 && flag; ++tempX)
                {
                    for (tempZ = z - b0; tempZ <= z + b0 && flag; ++tempZ)
                    {
                        if (tempY >= 0 && tempY < 256)
                        {
                        	block = world.getBlock(tempX, tempY, tempZ);
                            
                            if (!block.isAir(world, tempX, tempY, tempZ) && !block.isLeaves(world, tempX, tempY, tempZ))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                block = world.getBlock(x, y - 1, z);

                if (!block.equals(EnumBlock.fungal.block) && block != Blocks.dirt)
                {
                    return false;
                }
                else
                {
                    int j2 = y + i1 - 3;

                    //i1--;
                    for (tempY = j2; tempY <= y + i1; ++tempY)
                    {
                        int temp = 1;

                        if (tempY < y + i1)
                        {
                            ++temp;
                        }

                        for (tempX = x - temp; tempX <= x + temp; ++tempX)
                        {
                            for (tempZ = z - temp; tempZ <= z + temp; ++tempZ)
                            {
                                int l2 = 5;

                                if (tempX == x - temp)
                                {
                                    --l2;
                                }

                                if (tempX == x + temp)
                                {
                                    ++l2;
                                }

                                if (tempZ == z - temp)
                                {
                                    l2 -= 3;
                                }

                                if (tempZ == z + temp)
                                {
                                    l2 += 3;
                                }
                                if (tempY < y + i1)
                                {
	                                if ((tempX == x - temp || tempX == x + temp) && (tempZ == z - temp || tempZ == z + temp))
	                                {
	                                    continue;
	                                }
	
	                                if (tempX == x - (temp - 1) && tempZ == z - temp)
	                                {
	                                    l2 = 1;
	                                }
	
	                                if (tempX == x - temp && tempZ == z - (temp - 1))
	                                {
	                                    l2 = 1;
	                                }
	
	                                if (tempX == x + (temp - 1) && tempZ == z - temp)
	                                {
	                                    l2 = 3;
	                                }
	
	                                if (tempX == x + temp && tempZ == z - (temp - 1))
	                                {
	                                    l2 = 3;
	                                }
	
	                                if (tempX == x - (temp - 1) && tempZ == z + temp)
	                                {
	                                    l2 = 7;
	                                }
	
	                                if (tempX == x - temp && tempZ == z + (temp - 1))
	                                {
	                                    l2 = 7;
	                                }
	
	                                if (tempX == x + (temp - 1) && tempZ == z + temp)
	                                {
	                                    l2 = 9;
	                                }
	
	                                if (tempX == x + temp && tempZ == z + (temp - 1))
	                                {
	                                    l2 = 9;
	                                }
                                }
                                if (l2 == 5 && tempY < y + i1)
                                {
                                    l2 = 0;
                                }

                                block = world.getBlock(tempX, tempY, tempZ);

                                if ((l2 != 0 || y >= y + i1 - 1) && (block.canBeReplacedByLeaves(world, tempX, tempY, tempZ)))
                                {
                                    this.setBlockAndNotifyAdequately(world, tempX, tempY, tempZ, EnumBlock.blue_mushroom_cap.block, l2);
                                }
                            }
                        }
                    }
                    //i1++;
                    for (tempY = 0; tempY < i1; ++tempY)
                    {
                        block = world.getBlock(x, y + tempY, z);

                        if (block.canBeReplacedByLeaves(world, x, y + tempY, z))
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + tempY, z, EnumBlock.blue_mushroom_cap.block , 10);
                        }
                    }

                    return true;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
