package diversity.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.vecmath.Point4i;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.IWorldGenerator;
import diversity.utils.EnumCubeType;

public class WorldGenDwarvesCave extends WorldGenNewCave
{
	Point4i startPoint;
	
	
	public WorldGenDwarvesCave()
	{
		super(40, 25, 7, 15, 19, 4, 4, BiomeGenBase.extremeHills);
	}
	
	public IWorldGenerator create() {
		return new WorldGenDwarvesCave();
	}
	
	@Override
	protected List<Point4i> getSphereCenter(World world, Random random, int initX, int initY, int initZ, int radius) {
		this.startPoint = new Point4i(initX, initY, initZ, radius);
		List<Point4i> sphereCenter = new ArrayList<Point4i>();
		sphereCenter.add(startPoint);
		
		int numberOfPoint = caveSize;
		
		int counter = 3000;
		
		while (numberOfPoint > 0 && radius >= minRadius && counter-- > 0)
		{
			Point4i randomCenter = sphereCenter.get(random.nextInt(sphereCenter.size()));
			int x = randomCenter.x + (random.nextBoolean()? -1 : 1) * random.nextInt(radius*2-1);
			int y = randomCenter.y;
			int z = randomCenter.z + (random.nextBoolean()? -1 : 1) * random.nextInt(radius*2-1);
			
			boolean canTakeThisPoint = true;
			for (Point4i center : sphereCenter)
			{
				float dist = (float) Math.sqrt(
						Math.pow(x-center.x, 2) +
						Math.pow(y-center.y, 2) +
						Math.pow(z-center.z, 2));
				if (dist < radius * 1.4 || dist > radius * 3) {
					canTakeThisPoint = false;
					break;
				}
			}
			if (canTakeThisPoint && isBiomeViable(world, x, y, z, radius))
			{
				radius += random.nextInt(5)==0 ? -1 : 0;
				sphereCenter.add(new Point4i(x, y, z, radius));
				numberOfPoint--;
			}
			
		}
		return sphereCenter;
	}
	
	@Override
	protected void getCave(List<Point4i> sphereCenter, Random random)
	{
		for(Point4i center : sphereCenter)
		{
			int x = center.x;
			int y = center.y;
			int z = center.z;
			int radius = center.w;
	
			int minY = -radius;
			int maxY = radius;
			
			int minX = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxX = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			
			int minZ = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxZ = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			
			for (int tempX = minX*2; tempX <= maxX*2; tempX++)
			for (int tempZ = minZ*2; tempZ <= maxZ*2; tempZ++)
			for (int tempY = maxY*2; tempY >= minY*2; tempY--)
			{
				if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius*2, 2.0D)
						&& ((y+tempY) >= 20 || Math.pow(x+tempX-this.startPoint.x, 2.0D) + Math.pow(z+tempZ-this.startPoint.z, 2.0D) > Math.pow(30+(20-y)/2, 2.0D)))
				{
					blocks.put(x + tempX, y + tempY/2, z + tempZ, EnumCubeType.AIR);
				}
			}
		}
	}

	@Override
	protected void generateRoof(World world, Random random, int x, int y, int z) {
//		if (y > 60 && !world.getBlock(x, y + 2, z).getMaterial().equals(Material.water))
//		{
//			int tempY = y + 10;
//			if (world.isAirBlock(x, tempY, z)
//					|| world.getBlock(x, tempY, z).getMaterial().equals(Material.wood)
//					|| world.getBlock(x, tempY, z).getMaterial().equals(Material.leaves))
//			{
//				while (y < tempY)
//		        {
//					if (world.getBlock(x, y, z).getMaterial().equals(Material.wood))
//					{
//						for (int tempX = x - 2; tempX <= x + 2; tempX++)
//						{
//							for (int tempZ = z - 2; tempZ <= z + 2; tempZ++)
//							{
//					        	if (world.getBlock(tempX, y, tempZ).getMaterial().equals(Material.wood)
//										|| world.getBlock(tempX, y, tempZ).getMaterial().equals(Material.leaves))
//					        	{
//					        		world.setBlockToAir(tempX, y, tempZ);
//					        	}
//							}
//						}
//					}
//					else
//					{
//		        		world.setBlockToAir(x, y, z);
//		        	}
//					if (world.getBlock(x, tempY, z).getMaterial().equals(Material.wood)
//							|| world.getBlock(x, tempY + 1, z).getMaterial().equals(Material.wood)
//							|| world.getBlock(x, tempY + 2, z).getMaterial().equals(Material.wood))
//					{
//						tempY++;
//					}
//		            ++y;
//		        }
//			}
//		}
//		
//		for (int tempX = -1; tempX <= 1; tempX++)
//		for (int tempZ = -1; tempZ <= 1; tempZ++)
//		{
//			if (world.getBlock(tempX + x, y, tempZ + z) == Blocks.air && random.nextInt(5) == 0)
//			{
//				WorldGenUnderGroundVine gen = new WorldGenUnderGroundVine();
//				gen.generate(world, random, x, y, z, blocks.lastKey().intValue());
//			}
//		}
	}
	
	@Override
	protected void generateGround(World world, Random random, int x, int y, int z)
	{
//		if (random.nextInt(20) == 0)
//		{
//			WorldGenerator grassGenerator = biome.getRandomWorldGenForGrass(random);
//			grassGenerator.generate(world, random, x, y + 1, z);
//		} else if (random.nextInt(4) == 0)
//		{
//			WorldGenerator treeGenerator = biome.func_150567_a(random);
//			treeGenerator.generate(world, random, x, y + 1, z);
//		}
//		
		world.setBlock(x, y, z, Blocks.stone);
	}

	@Override
	protected void generateWall(World world, Random random, int x, int y, int z) {
		world.setBlockToAir(x, y , z);
	}

	@Override
	protected void generateWater(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, Blocks.lava);
	}
	
	@Override
	protected void generateAir(World world, Random random, Integer x, Integer y, Integer z) {
		world.setBlockToAir(x, y, z);		
	}

	@Override
	protected void generateUnderGround(World world, Random random, int x, int y, int z) {
		if (!blocks.containsKey(x, y-1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.WATER)) {
			if (random.nextInt(30) == 0 && world.getBlock(x, y, z).getMaterial().equals(Material.rock)) {
				this.biome.theBiomeDecorator.goldGen.generate(world, random, x, y, z);
			}
		} else {
		world.setBlock(x, y, z, Blocks.stone);
		}
	}
}
