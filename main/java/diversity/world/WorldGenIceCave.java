package diversity.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceCave extends WorldGenCave
{
	
	public WorldGenIceCave()
	{
		super(38, 6, 8, 27, 3, 4, BiomeGenBase.icePlains.createMutation());
	}
	
	public IWorldGenerator create() {
		return new WorldGenIceCave();
	}
	
	@Override
	protected void generateGround(World world, Random random, int x, int y, int z)
	{		
		if (random.nextInt(200) == 0)
		{
			boolean canSpawn = true;

			while (world.isAirBlock(x, y, z))
	        {
	        	if (y <= waterLevel) {
	        		canSpawn = false;
	        		break;
	        	}
	            --y;
	        }

	        for (int tempX = -5; tempX <= 5; tempX++)
		    for (int tempZ = -5; tempZ <= 5; tempZ++)
		    for (int tempY = y + 2; tempY <= y + 8; tempY++)
		    {
		    	if (world.getBlock(x + tempX, tempY, z + tempZ) == Blocks.packed_ice)
		    	{
		    		canSpawn = false;
		    	}
		    }
	        
	        if (canSpawn) {
	        	WorldGenerator gen = new WorldGenIceSpike();
				gen.generate(world, random, x, y, z);
	        }
		}
		
		world.setBlock(x, y, z, biome.topBlock);
	}

	@Override
	protected void generateRoof(World world, Random random, int x, int y, int z) {
		
		if (random.nextInt(200) == 0)
		{		
			boolean canSpawn = true;
			
	        while (world.isAirBlock(x, y, z))
	        {
	        	if (y >= 60) {
	        		canSpawn = false;
	        		break;
	        	}
	            ++y;
	        }
	        
	        for (int tempX = -5; tempX <= 5; tempX++)
		    for (int tempZ = -5; tempZ <= 5; tempZ++)
		    for (int tempY = y - 2; tempY >= y - 8; tempY--)
		    {
		    	if (world.getBlock(x + tempX, tempY, z + tempZ) == Blocks.packed_ice)
		    	{
		    		canSpawn = false;
		    	}
		    }
	        
			if (canSpawn) {
				WorldGenerator gen = new WorldGenReverseIceSpike();
				gen.generate(world, random, x, y, z);
			}
		}
		else {
			world.setBlock(x, y, z, Blocks.packed_ice);
		}
	}

	@Override
	protected void generateWall(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, Blocks.packed_ice);
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