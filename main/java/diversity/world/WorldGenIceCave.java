package diversity.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.vecmath.Point4i;

import cpw.mods.fml.common.IWorldGenerator;
import diversity.entity.EntityYeti;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceCave extends WorldGenNewCave
{
	
	public WorldGenIceCave()
	{
		super(10, 55, 4, 7, 30, 3, 4, BiomeGenBase.icePlains.createMutation());
	}
	
	public IWorldGenerator create() {
		return new WorldGenIceCave();
	}

	@Override
	protected List<Point4i> getSphereCenter(World world, Random random, int initX, int initY, int initZ, int radius) {
		List<Point4i> sphereCenter = new ArrayList<Point4i>();
		sphereCenter.add(new Point4i(initX, initY, initZ, radius));
		int numberOfPoint = caveSize;
		
		int counter = 1000;
		
		while (counter-- > 0)
		{
			Point4i randomCenter = sphereCenter.get(sphereCenter.size()-1);
			int x = randomCenter.x + (random.nextBoolean()? -1 : 1) * random.nextInt(radius*2-1);
			int y = randomCenter.y + 2 + random.nextInt(radius-minRadius+1);
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
				
				int i = 255;
				for (; i > y; --i){
					if (world.getBlock(x, i, z) != Blocks.air){
						break;
					}
				}
				if (y+radius >= i) {
					return sphereCenter;
				}
			}
			
		}
		return sphereCenter;
	}
	
	@Override
	protected void postGenerate(World world, Random random, List<Point4i> sphereCenter) {
        EntityLiving entity = new EntityYeti(world);
        Point4i spawnPoint = sphereCenter.get(0);
        entity.setLocationAndAngles((double)spawnPoint.x + 0.5D, (double)spawnPoint.y - spawnPoint.w + 3D, (double)spawnPoint.z + 0.5D, 0.0F, 0.0F);
        world.spawnEntityInWorld(entity);
	}

	@Override
	protected void generateGround(World world, Random random, int x, int y, int z)
	{		
//		if (random.nextInt(200) == 0)
//		{
//			boolean canSpawn = true;
//
//			while (world.isAirBlock(x, y, z))
//	        {
//	        	if (y <= waterLevel) {
//	        		canSpawn = false;
//	        		break;
//	        	}
//	            --y;
//	        }
//
//	        for (int tempX = -5; tempX <= 5; tempX++)
//		    for (int tempZ = -5; tempZ <= 5; tempZ++)
//		    for (int tempY = y + 2; tempY <= y + 8; tempY++)
//		    {
//		    	if (world.getBlock(x + tempX, tempY, z + tempZ) == Blocks.packed_ice)
//		    	{
//		    		canSpawn = false;
//		    	}
//		    }
//	        
//	        if (canSpawn) {
//	        	WorldGenerator gen = new WorldGenIceSpike();
//				gen.generate(world, random, x, y, z);
//	        }
//		}
		
		if (random.nextInt(20) == 0)
		{
			world.setBlock(x, y+1, z, Blocks.stone);
			for (int tempX = -1; tempX <= 1; tempX = tempX+2)
			for (int tempZ = -1; tempZ <= 1; tempZ = tempZ+2)
			{
				if (random.nextInt(6) == 0 && world.getBlock(x + tempX, y+1, z + tempZ) == Blocks.air) {
					world.setBlock(x + tempX, y+1, z + tempZ, Blocks.stone);
				}
			}
			if (random.nextInt(12) == 0 && world.getBlock(x, y+2, z) == Blocks.air) {
				world.setBlock(x, y+2, z, Blocks.stone);
			}
		}
		
		world.setBlock(x, y, z, biome.topBlock);
	}

	@Override
	protected void generateRoof(World world, Random random, int x, int y, int z) {
		int i = 255;
		for (; i > y; --i){
			if (world.getBlock(x, i, z) != Blocks.air){
				break;
			}
		}
		if (y != i) {
//			if (random.nextInt(200) == 0)
//			{		
//				boolean canSpawn = true;
//				
//		        while (world.isAirBlock(x, y, z))
//		        {
//		        	if (y >= 60) {
//		        		canSpawn = false;
//		        		break;
//		        	}
//		            ++y;
//		        }
//		        
//		        for (int tempX = -5; tempX <= 5; tempX++)
//			    for (int tempZ = -5; tempZ <= 5; tempZ++)
//			    for (int tempY = y - 2; tempY >= y - 8; tempY--)
//			    {
//			    	if (world.getBlock(x + tempX, tempY, z + tempZ) == Blocks.packed_ice)
//			    	{
//			    		canSpawn = false;
//			    	}
//			    }
//		        
//				if (canSpawn) {
//					WorldGenerator gen = new WorldGenReverseIceSpike();
//					gen.generate(world, random, x, y, z);
//				}
//			}
//			else {
				world.setBlock(x, y, z, Blocks.packed_ice);
//			}
		}
		else {
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	protected void generateWall(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, biome.topBlock);
	}

	@Override
	protected void generateWater(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, Blocks.ice);		
	}

	@Override
	protected void generateAir(World world, Random random, Integer x, Integer y, Integer z) {
		world.setBlockToAir(x, y, z);		
	}

	@Override
	protected void generateUnderGround(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, biome.topBlock);
	}	
}