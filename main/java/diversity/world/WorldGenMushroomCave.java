package diversity.world;

import java.util.Random;

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

public class WorldGenMushroomCave extends WorldGenCave
{
	
	public WorldGenMushroomCave()
	{
		super(40, 7, 10, 30, 3, 8, BiomeGenBase.swampland);
	}
	
	public IWorldGenerator create() {
		return new WorldGenMushroomCave();
	}
	
	@Override
	protected boolean isBiomeViable(World world, int x, int y, int z, int size)
	{
		if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + maxSize + 5, z) > 0) {
			return false;
		}
		
		return super.isBiomeViable(world, x, y, z, size);
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
