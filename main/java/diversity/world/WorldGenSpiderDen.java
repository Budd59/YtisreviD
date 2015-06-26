package diversity.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import diversity.entity.EntityDarkSpider;
import diversity.utils.EnumCubeType;

public class WorldGenSpiderDen
{
	
	public WorldGenSpiderDen()
	{
		//super(55, 4, 5, 45, 0, 2, BiomeGenBase.roofedForest);
	}

//	@Override
//	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
//	{
//    	int y = this.height;
//		while (world.getSavedLightValue(EnumSkyBlock.Sky, chunkX*16, y + maxSize, chunkZ*16) == 0) {
//			y++;
//		}
//    	int size = this.maxSize + random.nextInt(2);
//    	if (isBiomeViable(world, chunkX*16, y, chunkZ*16, size)) {
//			boolean flag = generateCave(world, random, chunkX*16, y, chunkZ*16, size);
//			generateBlocks(world, random);
//			if (flag) {
//				EntityDarkSpider darkSpider = new EntityDarkSpider(world);
//				darkSpider.setPosition(chunkX*16, y, chunkZ*16);
//				world.spawnEntityInWorld(darkSpider);
//			}
//			blocks.clear();
//		}
//	}
//	
//	@Override
//	protected boolean generateCave(World world, Random random, int x, int y, int z, int size)
//	{
//		if (!isBiomeViable(world, x, y, z, size)) 
//		{
//			return false;
//		}
//		int radius = size - 2 + random.nextInt(5);
//		createSphere(world, random, x, y, z, radius);
//		//createSphere(world, random, x - 5 + random.nextInt(11), y, z, radius);
//		if (size > this.minSize) {
//			for (int nextSphere = 0; nextSphere < 4 + random.nextInt(3); nextSphere++)
//			{
//				int tempX = 0;
//				int tempY = 0;
//				int tempZ = 0;
//				
//				while (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + maxSize/2, z) == 0) {
//					y++;
//				}
//				while ((Math.abs(tempX) + Math.abs(tempZ) < radius * 3 / 2) && (!world.getBlock(x + tempX, y + tempY, z + tempZ).equals(Blocks.bedrock)))
//				{
//					tempX += 7 - random.nextInt(15);
//				    tempZ += 7 - random.nextInt(15);
//				}
//				generateCave(world, random, x + tempX, y + tempY, z + tempZ, size - 1);
//			}
//		}
//		return true;
//	}
//	
//	@Override
//	protected void generateGround(World world, Random random, int x, int y, int z) {		
//		if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + 1, z) == 0
//				&& !world.getBlock(x, y, z).equals(Material.wood)) {
//			world.setBlock(x, y, z, Blocks.dirt, 1, 3);
//			for (int tempY = 1; tempY < 5; tempY++) {
//				if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + tempY + 1, z) == 0
//						&& !world.getBlock(x, y + tempY, z).equals(Material.wood))
//				{
//					if (random.nextInt(8 - tempY) > 1) {
//						world.setBlock(x, y + tempY, z, Blocks.web);
//					}
//				} else {
//					break;
//				}
//			}
//		}
//	}
//
//	@Override
//	protected void generateRoof(World world, Random random, int x, int y, int z)
//	{
//		if (blocks.containsKey(x, y+1, z)
//				&& blocks.get(x, y+1, z).equals(EnumCubeType.OUTSIDE)
//				&& world.getBlock(x, y+2, z).getMaterial().equals(Material.wood)) {
//			y++;
//			blocks.remove(x, y, z);
//		}
//		boolean flagWood = false;
//		boolean flagSurface = false;
//		
//		for (int tempX = 0; tempX <= 0; tempX++)
//		for (int tempZ = 0; tempZ <= 0; tempZ++)
//		{
//			if (world.getBlock(x + tempX, y + 1, z + tempZ).getMaterial().equals(Material.wood))
//			{
//				flagWood = true;
//				break;
//			}
//		}
//		if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + 1, z) > 0);
//		{
//			flagSurface = true;
//		}
//		if (flagWood)
//		{
//			if (world.getBlock(x, y + 1, z).getMaterial().equals(Material.wood))
//			{
//				Block block = world.getBlock(x, y + 1, z);
//				int metadata = world.getBlockMetadata(x, y + 1, z);
//				if (!block.equals(Blocks.brown_mushroom_block)
//						&& !block.equals(Blocks.red_mushroom_block))  {
//					WorldGenerator worldGen = new WorldGenRoot(block, metadata);
//					worldGen.generate(world, random, x, y, z);
//					worldGen.generate(world, random, x, y, z);
//					if (random.nextBoolean()) {
//						worldGen.generate(world, random, x, y, z);
//					}
//				} else if (metadata == 0) {
//					WorldGenerator worldGen = new WorldGenRoot(block, metadata);
//					worldGen.generate(world, random, x, y, z);
//				}
//			}
//			
//		} else if (flagSurface)
//		{
//			world.setBlockToAir(x, y, z);
//		}
//	}
//
//	@Override
//	protected void generateWall(World world, Random random, int x, int y, int z) {
//		
//	}
//
//	@Override
//	protected void generateWater(World world, Random random, int x, int y, int z) {
//		world.setBlock(x, y, z, Blocks.water);		
//	}
//
//	@Override
//	protected void generateAir(World world, Random random, Integer x, Integer y, Integer z) {	
//		if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) > 0
//				|| world.getBlock(x, y, z).getMaterial().equals(Material.wood))
//		{
//			blocks.put(x, y, z, EnumCubeType.OUTSIDE);
//			while (blocks.containsKey(x, y+1, z)) {
//				blocks.remove(x, y+1, z);
//				y++;
//			}
//		}
//		else if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y+1, z) > 0)
//		{
//			blocks.put(x, y, z, EnumCubeType.ROOF);
//		}
//		else
//		{
//			world.setBlockToAir(x, y, z);
//		}
//	}
//
//	@Override
//	protected void generateUnderGround(World world, Random random, int x, int y, int z) {
//		if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y + 1, z) == 0
//				&& !world.getBlock(x, y, z).equals(Material.wood)) {
//			world.setBlock(x, y, z, Blocks.dirt);
//		}
//	}
	
	
}