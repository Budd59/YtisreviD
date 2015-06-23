package diversity.world;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.logging.log4j.Level;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IWorldGenerator;
import diversity.configurations.ConfigGenerationRate;
import diversity.suppliers.EnumGenerator;
import diversity.utils.Table3d;
import diversity.utils.EnumCubeType;

public abstract class WorldGenCave implements IWorldGenerator
{
	protected final int height;
	protected final int maxSize;
	protected final int minSize;
	protected final int waterLevel;
	protected final int leveler;
	protected final int sizeRandomer;
	protected final BiomeGenBase biome;
	
	public final Table3d blocks;
			
	public WorldGenCave(int height, int minSize, int maxSize, int waterLevel, int leveler, int sizeRandomer, BiomeGenBase biome)
	{
		if (height < waterLevel) {
			this.height = this.waterLevel;
		} else {
			this.height = height;
		}
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.waterLevel = waterLevel;
		this.leveler = leveler;
		this.sizeRandomer = sizeRandomer;
		this.biome = biome;
		
		this.blocks = new Table3d();
	}
	
	public abstract IWorldGenerator create();

    /**
     * Generate some world
     *
     * @param random the chunk specific {@link Random}.
     * @param chunkX the chunk X coordinate of this chunk.
     * @param chunkZ the chunk Z coordinate of this chunk.
     * @param world : additionalData[0] The minecraft {@link World} we're generating for.
     * @param chunkGenerator : additionalData[1] The {@link IChunkProvider} that is generating.
     * @param chunkProvider : additionalData[2] {@link IChunkProvider} that is requesting the world generation.
     *
     */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
    	int y = 0;
    	do {
    		y = this.height + random.nextInt(12);
    	} while (y < this.waterLevel);
    	int size = this.maxSize + random.nextInt(2);
    	if (isBiomeViable(world, chunkX*16, y, chunkZ*16, size)) {
			generateCave(world, random, chunkX*16, y, chunkZ*16, size);
			generateBlocks(world, random);
			blocks.clear();
		}
	}
	
	public WorldGenCave duplicate() {
		try {
			return (WorldGenCave) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected boolean isBiomeViable(World world, int x, int y, int z, int size)
	{
		for (int tempX = x - size; tempX <= x + size; tempX = tempX + size)
		for (int tempZ = z - size; tempZ <= z + size; tempZ = tempZ + size)
		{
			BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
			for (EnumGenerator generator : EnumGenerator.values()) {
				if (generator.worldGen.getClass().equals(getClass())) {
					return generator.canGenerate(world, tempX, y, tempZ);
				}
			}
		}
		return false;
	}
			
	protected abstract void generateAir(World world, Random random, Integer x, Integer y, Integer z);

	protected abstract void generateGround(World world, Random random, int x, int y, int z);
	
	protected abstract void generateUnderGround(World world, Random random, int x, int y, int z);

	protected abstract void generateRoof(World world, Random random, int x, int y, int z);

	protected abstract void generateWall(World world, Random random, int x, int y, int z);

	protected abstract void generateWater(World world, Random random, int x, int y, int z);
	
	protected void generateOutside(World world, Random random, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			if (world.getBlock(x, y, z).getMaterial().equals(Material.water)
					&& !world.getBlock(x, y-1, z).getMaterial().isSolid())
			{
					world.setBlock(x, y, z, Blocks.gravel);
			} else {
				world.setBlock(x, y, z, Blocks.stone);
			}
		}
	}

	protected boolean generateCave(World world, Random random, int x, int y, int z, int size)
	{
		if (!isBiomeViable(world, x, y, z, size)) 
		{
			return false;
		}
		int radius = size - 2 + random.nextInt(5);
		createSphere(world, random, x, y, z, radius);
		//createSphere(world, random, x - 5 + random.nextInt(11), y, z, radius);
		if (size > this.minSize) {
			for (int nextSphere = 0; nextSphere < 4 + random.nextInt(3); nextSphere++)
			{
				int tempX = 0;
				int tempY = 0;
				int tempZ = 0;
				
				do {
					tempY = leveler - random.nextInt(leveler*2 + 1);
				} while ((y + tempY) < waterLevel);
				while ((Math.abs(tempX) + Math.abs(tempZ) < radius * 3 / 2) && (!world.getBlock(x + tempX, y + tempY, z + tempZ).equals(Blocks.bedrock)))
				{
					tempX += 7 - random.nextInt(15);
				    tempZ += 7 - random.nextInt(15);
				}
				generateCave(world, random, x + tempX, y + tempY, z + tempZ, size - 1);
			}
		}
		return true;
	}
	
	protected void createSphere(World world, Random random, int x, int y, int z, int radius)
	{
		int minY = -radius - sizeRandomer / 2 + random.nextInt(sizeRandomer + 1);
		int maxY = radius - sizeRandomer / 2 + random.nextInt(sizeRandomer + 1);
		
		int minX = -radius - sizeRandomer / 2 + random.nextInt(sizeRandomer + 1);
		int maxX = radius - sizeRandomer / 2 + random.nextInt(sizeRandomer + 1);
		
		int minZ = -radius - sizeRandomer / 2 + random.nextInt(sizeRandomer + 1);
		int maxZ = radius - sizeRandomer / 2 + random.nextInt(sizeRandomer + 1);
		
		for (int tempX = minX; tempX <= maxX; tempX++)
		for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
		for (int tempY = maxY; tempY >= minY; tempY--)
		{
			if (world.getBlock(x + tempX, y + tempY, z + tempZ).equals(Blocks.bedrock)) {
				continue;
			}
			if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
			{
				if (y + tempY <= this.waterLevel)
				{
					if (Math.pow(tempX+1, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D)
						&& Math.pow(tempX-1, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D)
						&& Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ+1, 2.0D) < Math.pow(radius, 2.0D)
						&& Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ-1, 2.0D) < Math.pow(radius, 2.0D))
					{
						addPoint(x + tempX, y + tempY, z + tempZ, EnumCubeType.WATER);
					} else if (y + tempY == this.waterLevel)
					{
						addPoint(x + tempX, y + tempY, z + tempZ, EnumCubeType.GROUND);
						addPoint(x + tempX, y + tempY - 1, z + tempZ, EnumCubeType.UNDERGROUND);
						addPoint(x + tempX, y + tempY - 2, z + tempZ, EnumCubeType.UNDERGROUND);
					} else {
						addPoint(x + tempX, y + tempY, z + tempZ, EnumCubeType.UNDERGROUND);
						addPoint(x + tempX, y + tempY - 1, z + tempZ, EnumCubeType.UNDERGROUND);
						addPoint(x + tempX, y + tempY - 2, z + tempZ, EnumCubeType.UNDERGROUND);
					}
				}
				else if (tempY == minY || Math.pow(tempX, 2.0D) + Math.pow(tempY-1, 2.0D) + Math.pow(tempZ, 2.0D) >= Math.pow(radius, 2.0D))
				{
					addPoint(x + tempX, y + tempY, z + tempZ, EnumCubeType.GROUND);
					addPoint(x + tempX, y + tempY - 1, z + tempZ, EnumCubeType.UNDERGROUND);
					addPoint(x + tempX, y + tempY - 2, z + tempZ, EnumCubeType.UNDERGROUND);
				}
				else if (tempY == maxY || Math.pow(tempX, 2.0D) + Math.pow(tempY+1, 2.0D) + Math.pow(tempZ, 2.0D) >= Math.pow(radius, 2.0D))
				{
					addPoint(x + tempX, y + tempY, z + tempZ, EnumCubeType.ROOF);
					addPoint(x + tempX, y + tempY + 1, z + tempZ, EnumCubeType.OUTSIDE);
					addPoint(x + tempX, y + tempY + 2, z + tempZ, EnumCubeType.OUTSIDE);
				}
				else
				{
					addPoint(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
				}
				if (tempX == maxX || Math.pow(tempX+1, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) >= Math.pow(radius, 2.0D)) {
					addPoint(x + tempX + 1, y + tempY, z + tempZ, EnumCubeType.WALL);
					addPoint(x + tempX + 1, y + tempY + 1, z + tempZ, EnumCubeType.OUTSIDE);
					addPoint(x + tempX + 1, y + tempY + 2, z + tempZ, EnumCubeType.OUTSIDE);
				}
				if (tempX == minX || Math.pow(tempX-1, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) >= Math.pow(radius, 2.0D)) {
					addPoint(x + tempX - 1, y + tempY, z + tempZ, EnumCubeType.WALL);
					addPoint(x + tempX - 1, y + tempY + 1, z + tempZ, EnumCubeType.OUTSIDE);
					addPoint(x + tempX - 1, y + tempY + 2, z + tempZ, EnumCubeType.OUTSIDE);
				}
				if (tempZ == maxZ || Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ+1, 2.0D) >= Math.pow(radius, 2.0D)) {
					addPoint(x + tempX, y + tempY, z + tempZ + 1, EnumCubeType.WALL);
					addPoint(x + tempX, y + tempY + 1, z + tempZ + 1, EnumCubeType.OUTSIDE);
					addPoint(x + tempX, y + tempY + 2, z + tempZ + 1, EnumCubeType.OUTSIDE);
				}
				if (tempZ == minZ || Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ-1, 2.0D) >= Math.pow(radius, 2.0D)) {
					addPoint(x + tempX, y + tempY, z + tempZ - 1, EnumCubeType.WALL);
					addPoint(x + tempX, y + tempY + 1, z + tempZ - 1, EnumCubeType.OUTSIDE);
					addPoint(x + tempX, y + tempY + 2, z + tempZ - 1, EnumCubeType.OUTSIDE);
				}
			}
		}
	}
	
	protected void generateBlocks(World world, Random random)
	{
		for (Integer y : blocks.descendingKeySet())
		for (Integer x : blocks.rowKeySet(y))
		for (Integer z : blocks.columnKeySet(y))
		{
			if (blocks.containsKey(x, y, z))
			{
				if (blocks.get(x, y, z).equals(EnumCubeType.AIR))
				{
					generateAir(world, random, x, y, z);
				}
				else if (blocks.get(x, y, z).equals(EnumCubeType.WATER))
				{
					generateWater(world, random, x, y, z);
				}
			}
		}
		for (Integer y : blocks.keySet())
		for (Integer x : blocks.rowKeySet(y))
		for (Integer z : blocks.columnKeySet(y))
		{
			if (blocks.containsKey(x, y, z))
			{
				if (blocks.get(x, y, z).equals(EnumCubeType.ROOF))
				{
					generateRoof(world, random, x, y, z);
				}
				else if (blocks.get(x, y, z).equals(EnumCubeType.WALL))
				{
					generateWall(world, random, x, y, z);
				}
				else if (blocks.get(x, y, z).equals(EnumCubeType.GROUND))
				{
					generateGround(world, random, x, y, z);
				}
				else if (blocks.get(x, y, z).equals(EnumCubeType.UNDERGROUND))
				{
					generateUnderGround(world, random, x, y, z);
				}
				else if (blocks.get(x, y, z).equals(EnumCubeType.OUTSIDE))
				{
					generateOutside(world, random, x, y, z);
				}
			}
		}
	}
	
	protected void addPoint(int x, int y, int z, EnumCubeType type)
	{
		if (blocks.containsKey(x, y, z) && (blocks.get(x, y, z).equals(type))) {
			return;
		}
		
		if (type.equals(EnumCubeType.AIR)) {
			blocks.put(x, y, z, type);
		}
		else if (type.equals(EnumCubeType.WATER)) {
			blocks.put(x, y, z, type);
		}
		else if (type.equals(EnumCubeType.ROOF)) {
			if (!blocks.containsKey(x, y, z)
					|| blocks.get(x, y, z).equals(EnumCubeType.OUTSIDE)) {
				blocks.put(x, y, z, type);
			}
		}
		else if (type.equals(EnumCubeType.OUTSIDE)) {
			if (!blocks.containsKey(x, y, z)) {
				blocks.put(x, y, z, type);
			}
		}
		else if (type.equals(EnumCubeType.GROUND)) {
			if (!blocks.containsKey(x, y, z)
					|| blocks.get(x, y, z).equals(EnumCubeType.OUTSIDE)
					|| blocks.get(x, y, z).equals(EnumCubeType.UNDERGROUND)
					|| blocks.get(x, y, z).equals(EnumCubeType.WALL)) {
				blocks.put(x, y, z, type);
			}
		}
		else if (type.equals(EnumCubeType.UNDERGROUND)) {
			if (!blocks.containsKey(x, y, z)
					|| blocks.get(x, y, z).equals(EnumCubeType.OUTSIDE)
					|| blocks.get(x, y, z).equals(EnumCubeType.WALL)) {
				blocks.put(x, y, z, type);
			}
		}
		else if (type.equals(EnumCubeType.WALL)) {
			if (!blocks.containsKey(x, y, z)
					|| blocks.get(x, y, z).equals(EnumCubeType.OUTSIDE)) {
				blocks.put(x, y, z, type);
			}
		}
	}
}
