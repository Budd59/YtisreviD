package diversity.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.vecmath.Point4i;

import cpw.mods.fml.common.IWorldGenerator;
import diversity.suppliers.EnumBlock;
import diversity.suppliers.EnumGenerator;
import diversity.utils.Table3d;
import diversity.utils.EnumCubeType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMushroomCave extends WorldGenNewCave
{
	
	public WorldGenMushroomCave()
	{
		super(14, 35, 6, 10, 30, 3, 8, BiomeGenBase.swampland);
	}
	
	public IWorldGenerator create() {
		return new WorldGenMushroomCave();
	}
	
	@Override
	protected boolean isBiomeViable(World world, int x, int y, int z, int size)
	{
		if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + maxRadius + 5, z) > 0) {
			return false;
		}
		
		return super.isBiomeViable(world, x, y, z, size);
	}
	
	@Override
	protected List<Point4i> getSphereCenter(World world, Random random, int initX, int initY, int initZ, int radius) {
		List<Point4i> sphereCenter = new ArrayList<Point4i>();
		sphereCenter.add(new Point4i(initX, initY, initZ, radius));
		int numberOfPoint = caveSize;
		
		int counter = 1000;
		
		while (numberOfPoint > 0 && radius >= minRadius && counter-- > 0)
		{
			Point4i randomCenter = sphereCenter.get(random.nextInt(sphereCenter.size()));
			int x = randomCenter.x + (random.nextBoolean()? -1 : 1) * random.nextInt(radius*2-1);
			int y = randomCenter.y + (random.nextBoolean()? -1 : 1) * random.nextInt(2);
			int z = randomCenter.z + (random.nextBoolean()? -1 : 1) * random.nextInt(radius*2-1);
			
			boolean canTakeThisPoint = true;
			for (Point4i center : sphereCenter)
			{
				float dist = (float) Math.sqrt(
						Math.pow(x-center.x, 2) +
						Math.pow(y-center.y, 2) +
						Math.pow(z-center.z, 2));
				if (dist < radius * 0.9 || dist > radius * 1.7) {
					canTakeThisPoint = false;
					break;
				}
			}
			if (canTakeThisPoint && isBiomeViable(world, x, y, z, radius))
			{
				radius += random.nextInt(10)==0 ? -1 : 0;
				sphereCenter.add(new Point4i(x, y, z, radius));
				numberOfPoint--;
			}
			
		}
		return sphereCenter;
	}
	
	@Override
	protected void generateGround(World world, Random random, int x, int y, int z)
	{
		world.setBlock(x, y, z, EnumBlock.fungal.block);
		
		if (blocks.containsKey(x, y+1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.AIR))
		{
			boolean flag = false;
			if (random.nextInt(40) == 0) {				
				flag = new WorldGenPhosMushroom().generate(world, random, x, y + 1, z);
				if (!flag && random.nextInt(3) > 0)
				{
					flag = new WorldGenBlueMushroom().generate(world, random, x, y + 1, z);
				}
			}
			else if (!flag && random.nextInt(20) == 0)
			{
				if (random.nextInt(10) > 2) {
					world.setBlock(x, y + 1, z, EnumBlock.blue_mushroom.block);
				} else {
					world.setBlock(x, y + 1, z, EnumBlock.phos_mushroom.block);
				}
			}
		}
	}

	@Override
	protected void generateRoof(World world, Random random, int x, int y, int z) {
		world.setBlockToAir(x, y, z);
		if (random.nextInt(8) == 0) {
			if ((blocks.containsKey(x, y+1, z) && !blocks.get(x, y+1, z).equals(EnumCubeType.AIR))
					|| !world.getBlock(x, y+1, z).getMaterial().equals(Material.air)) {
				WorldGenerator worldgenbluevine = new WorldGenBlueVine(1 + random.nextInt(3));
				worldgenbluevine.generate(world, random, x, y, z);
			}
		}
	}

	@Override
	protected void generateWall(World world, Random random, int x, int y, int z) {
		super.generateOutside(world, random, x, y, z);
		if (random.nextInt(120) == 0) {
			WorldGenerator worldGenFungus = new WorldGenFungus(1, 1);
			worldGenFungus.generate(world, random, x, y, z);
		}
	}

	@Override
	protected void generateWater(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, EnumBlock.phos_water.block);		
	}
	
	@Override
	protected void generateAir(World world, Random random, Integer x, Integer y, Integer z) {
		//world.setBlockToAir(x, y, z);
		world.setBlock(x, y, z, Blocks.air, 0, 2);
	}

	@Override
	protected void generateUnderGround(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, biome.fillerBlock);
	}
}
