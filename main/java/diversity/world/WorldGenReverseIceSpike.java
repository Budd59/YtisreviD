package diversity.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenReverseIceSpike extends WorldGenerator
{
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        while (world.isAirBlock(x, y, z) && y < 70)
        {
            ++y;
        }

        if (world.getBlock(x, y, z) != Blocks.snow
        		&& world.getBlock(x, y, z) != Blocks.packed_ice)
        {
            return false;
        }
        else
        {
            y -= random.nextInt(4);
            int height = random.nextInt(4) + 7;
            int width = height / 4 + random.nextInt(2);

            if (width > 1 && random.nextInt(60) == 0)
            {
                y -= 10 + random.nextInt(30);
            }

            int tempY;
            int currentWidth;
            int tempX;

            for (tempY = height; tempY > 0; --tempY)
            {
                float f = (1.0F - (float)(height - tempY) / (float)height) * (float)width;
                currentWidth = MathHelper.ceiling_float_int(f);

                for (tempX = -currentWidth; tempX <= currentWidth; ++tempX)
                {
                    float f1 = (float)MathHelper.abs_int(tempX) - 0.25F;

                    for (int tempZ = -currentWidth; tempZ <= currentWidth; ++tempZ)
                    {
                        float f2 = (float)MathHelper.abs_int(tempZ) - 0.25F;

                        if ((tempX == 0 && tempZ == 0 || f1 * f1 + f2 * f2 <= f * f) && (tempX != -currentWidth && tempX != currentWidth && tempZ != -currentWidth && tempZ != currentWidth || random.nextFloat() <= 0.75F))
                        {
                            Block block = world.getBlock(x + tempX, y + tempY, z + tempZ);

                            if (block.getMaterial() == Material.air || block == Blocks.dirt || block == Blocks.snow || block == Blocks.ice)
                            {
                                this.func_150515_a(world, x + tempX, y + tempY, z + tempZ, Blocks.packed_ice);
                            }

                            if (tempY != 0 && currentWidth > 1)
                            {
                                block = world.getBlock(x + tempX, y - tempY, z + tempZ);

                                if (block.getMaterial() == Material.air || block == Blocks.dirt || block == Blocks.snow || block == Blocks.ice)
                                {
                                    this.func_150515_a(world, x + tempX, y - tempY, z + tempZ, Blocks.packed_ice);
                                }
                            }
                        }
                    }
                }
            }

            tempY = width - 1;

            if (tempY < 0)
            {
                tempY = 0;
            }
            else if (tempY > 1)
            {
                tempY = 1;
            }

            for (int j2 = -tempY; j2 <= tempY; ++j2)
            {
                currentWidth = -tempY;

                while (currentWidth <= tempY)
                {
                    tempX = y + 1;
                    int k2 = 50;

                    if (Math.abs(j2) == 1 && Math.abs(currentWidth) == 1)
                    {
                        k2 = random.nextInt(5);
                    }

                    while (true)
                    {
                        if (tempX < 62)
                        {
                            Block block1 = world.getBlock(x + j2, tempX, z + currentWidth);

                            if (block1.getMaterial() == Material.air || block1 == Blocks.dirt || block1 == Blocks.snow || block1 == Blocks.ice || block1 == Blocks.packed_ice)
                            {
                                this.func_150515_a(world, x + j2, tempX, z + currentWidth, Blocks.packed_ice);
                                ++tempX;
                                --k2;

                                if (k2 <= 0)
                                {
                                    tempX += random.nextInt(5) + 1;
                                    k2 = random.nextInt(5);
                                }

                                continue;
                            }
                        }

                        ++currentWidth;
                        break;
                    }
                }
            }

            return true;
        }
    }
}