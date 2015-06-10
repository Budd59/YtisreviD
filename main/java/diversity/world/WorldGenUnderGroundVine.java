package diversity.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenUnderGroundVine
{
    public boolean generate(World world, Random random, int x, int y, int z, int h)
    {
        int l = x;

        for (int i1 = z; y < h; ++y)
        {
            if (world.isAirBlock(x, y, z))
            {
                for (int j1 = 2; j1 <= 5; ++j1)
                {
                    if (Blocks.vine.canPlaceBlockOnSide(world, x, y, z, j1))
                    {
                        world.setBlock(x, y, z, Blocks.vine, 1 << Direction.facingToDirection[Facing.oppositeSide[j1]], 2);
                        break;
                    }
                }
            }
            else
            {
                x = l + random.nextInt(4) - random.nextInt(4);
                z = i1 + random.nextInt(4) - random.nextInt(4);
            }
        }

        return true;
    }
}
