package diversity.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import diversity.suppliers.EnumBlock;
import diversity.utils.Table3d;
import diversity.utils.EnumCubeType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLostCave extends WorldGenCave
{
	public WorldGenLostCave()
	{
		super(45, 9, 12, 35, 4, 4, BiomeGenBase.jungle);
	}
	
	public IWorldGenerator create() {
		return new WorldGenLostCave();
	}

	@Override
	protected void generateRoof(World world, Random random, int x, int y, int z) {
		if (y > 60 && !world.getBlock(x, y + 2, z).getMaterial().equals(Material.water))
		{
			int tempY = y + 10;
			if (world.isAirBlock(x, tempY, z)
					|| world.getBlock(x, tempY, z).getMaterial().equals(Material.wood)
					|| world.getBlock(x, tempY, z).getMaterial().equals(Material.leaves))
			{
				while (y < tempY)
		        {
					if (world.getBlock(x, y, z).getMaterial().equals(Material.wood))
					{
						for (int tempX = x - 2; tempX <= x + 2; tempX++)
						{
							for (int tempZ = z - 2; tempZ <= z + 2; tempZ++)
							{
					        	if (world.getBlock(tempX, y, tempZ).getMaterial().equals(Material.wood)
										|| world.getBlock(tempX, y, tempZ).getMaterial().equals(Material.leaves))
					        	{
					        		world.setBlockToAir(tempX, y, tempZ);
					        	}
							}
						}
					}
					else
					{
		        		world.setBlockToAir(x, y, z);
		        	}
					if (world.getBlock(x, tempY, z).getMaterial().equals(Material.wood)
							|| world.getBlock(x, tempY + 1, z).getMaterial().equals(Material.wood)
							|| world.getBlock(x, tempY + 2, z).getMaterial().equals(Material.wood))
					{
						tempY++;
					}
		            ++y;
		        }
			}
		}
		
		for (int tempX = -1; tempX <= 1; tempX++)
		for (int tempZ = -1; tempZ <= 1; tempZ++)
		{
			if (world.getBlock(tempX + x, y, tempZ + z) == Blocks.air && random.nextInt(5) == 0)
			{
				WorldGenUnderGroundVine gen = new WorldGenUnderGroundVine();
				gen.generate(world, random, x, y, z, blocks.lastKey().intValue());
			}
		}
	}
	
	@Override
	protected void generateGround(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(20) == 0)
		{
			WorldGenerator grassGenerator = biome.getRandomWorldGenForGrass(random);
			grassGenerator.generate(world, random, x, y + 1, z);
		} else if (random.nextInt(4) == 0)
		{
			WorldGenerator treeGenerator = biome.func_150567_a(random);
			treeGenerator.generate(world, random, x, y + 1, z);
		}
		
		world.setBlock(x, y, z, biome.topBlock);
	}

	@Override
	protected void generateWall(World world, Random random, int x, int y, int z) {
		if (random.nextInt(10) == 0 && y < 60) {
			world.setBlock(x, y , z, Blocks.vine);
			while (random.nextBoolean() || random.nextBoolean())
			{
				y--;
				if (blocks.containsKey(x, y, z) && blocks.get(x, y, z).equals(EnumCubeType.AIR))
				{
					world.setBlock(x, y , z, Blocks.vine);
				} else {
					return;
				}
			}
		}
	}

	@Override
	protected void generateWater(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, EnumBlock.poison_water.block);
		y--;
		if (random.nextInt(30) == 0) {
			if (!blocks.containsKey(x, y, z) || world.getBlock(x, y, z).getMaterial().equals(Material.rock) )
			{
				this.biome.theBiomeDecorator.goldGen.generate(world, random, x, y, z);
			}
		}
	}
	
	@Override
	protected void generateAir(World world, Random random, Integer x, Integer y, Integer z) {
		boolean flag = false;
		boolean flagCanAir = true;
		
		if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + 1, z) > 0) {
			flag = true;
		}
		for (int tempX = -1; tempX <= 1; tempX++)
		for (int tempZ = -1; tempZ <= 1; tempZ++)
		{
			if (world.getBlock(x + tempX, y + 1, z + tempZ).getMaterial().isLiquid()) {
				flag = true;
				break;
			}
		}
		
		if (flag) {
			blocks.remove(x, y + 3, z);
			blocks.put(x, y + 1, z, EnumCubeType.OUTSIDE);
			blocks.put(x, y, z, EnumCubeType.ROOF);
		} else if (world.getBlock(x, y, z) != Blocks.vine && (y<=60 || !world.getBlock(x, 62, z).getMaterial().equals(Material.water))) {
			world.setBlockToAir(x, y, z);		
		}
	}

	@Override
	protected void generateUnderGround(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, biome.fillerBlock);
	}
}
