package diversity.world;

import java.util.Random;

import diversity.suppliers.EnumBlock;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBlueVine extends WorldGenerator
{
	private int height;
	
	public WorldGenBlueVine(int height) {
		super();
		this.height = height;
	}
	
	public WorldGenBlueVine() {
		this(0);
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		if (height == 0) {
			for (int tempHeight = 0; tempHeight < 1+ random.nextInt(4) ; tempHeight++)
			{
				if(world.getBlock(x,y-tempHeight,z).getMaterial().equals(Material.air))
				{
					world.setBlock(x,y-tempHeight,z, EnumBlock.blue_vine.block);
				}
			}
		} else {
			for (int tempHeight = 0; tempHeight < height; tempHeight++)
			{
				if(world.getBlock(x,y-tempHeight,z).getMaterial().equals(Material.air))
				{
					world.setBlock(x,y-tempHeight,z, EnumBlock.blue_vine.block);
				}
			}
		}
		return true;
	}

}
