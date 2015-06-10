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

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (int tempHeight = 0; tempHeight < height; tempHeight++)
		{
			if(world.getBlock(x,y-tempHeight,z).getMaterial().equals(Material.air))
			{
				world.setBlock(x,y-tempHeight,z, EnumBlock.blue_vine.block);
			}
		}
		return true;
	}

}
