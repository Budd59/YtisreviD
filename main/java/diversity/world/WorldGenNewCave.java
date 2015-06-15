package diversity.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import javax.vecmath.Point4i;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import diversity.Diversity;
import diversity.suppliers.EnumGenerator;
import diversity.utils.EnumCubeType;
import diversity.utils.Table3d;

public abstract class WorldGenNewCave implements IWorldGenerator
{
	protected final int caveSize;
	protected final int minRadius;
	protected final int maxRadius;
	protected final int height;
	protected final int waterLevel;
	protected final int leveler;
	protected final int radiusRandomer;

	protected final BiomeGenBase biome;
	
	public final Table3d blocks;
			
	public WorldGenNewCave(int caveSize, int height, int minSize, int maxSize, int waterLevel, int leveler, int sizeRandomer, BiomeGenBase biome)
	{
		this.caveSize = caveSize;
		if (height < waterLevel) {
			this.height = this.waterLevel;
		} else {
			this.height = height;
		}
		this.minRadius = minSize;
		this.maxRadius = maxSize;
		this.waterLevel = waterLevel;
		this.leveler = leveler;
		this.radiusRandomer = sizeRandomer;
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
    	int y;
    	do {
    		y = this.height + random.nextInt(5);
    	} while (y < this.waterLevel);

    	int radius = this.maxRadius + random.nextInt(2);
    	if (isBiomeViable(world, chunkX*16, y, chunkZ*16, radius)) {
    		Diversity.Divlogger.log(Level.INFO, chunkX*16 + " " + chunkZ*16);
            List<Point4i> sphereCenter = getSphereCenter(world, random, chunkX*16, y, chunkZ*16, radius);
            getCave(sphereCenter, random);
            getBlockType();
			generateBlocks(world, random);
			postGenerate(world, random, sphereCenter);
			blocks.clear();
			
		}
	}
	
	protected void postGenerate(World world, Random random, List<Point4i> sphereCenter) {}
	
	protected abstract List<Point4i> getSphereCenter(World world, Random random, int initX, int initY, int initZ, int radius);
	
	protected void getCave(List<Point4i> sphereCenter, Random random)
	{
		for(Point4i center : sphereCenter)
		{
			int x = center.x;
			int y = center.y;
			int z = center.z;
			int radius = center.w;
	
			int minY = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxY = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			
			int minX = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxX = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			
			int minZ = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxZ = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			
			for (int tempX = minX; tempX <= maxX; tempX++)
			for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
			for (int tempY = maxY; tempY >= minY; tempY--)
			{
				if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
				{
					blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
				}
			}
		}
	}
	
	private void getBlockType()
	{
		for (Integer y : blocks.descendingKeySet())
		for (Integer x : blocks.rowKeySet(y))
		for (Integer z : blocks.columnKeySet(y))
		{
			if (blocks.containsKey(x, y, z))
			{
				if (!blocks.containsKey(x, y+1, z) && blocks.containsKey(x+1, y-1, z) && blocks.containsKey(x-1, y-1, z) && blocks.containsKey(x, y-1, z+1) && blocks.containsKey(x, y-1, z-1)) {
					blocks.put(x, y, z, EnumCubeType.ROOF);
				} else if (!blocks.containsKey(x+1, y, z) || !blocks.containsKey(x-1, y, z) || !blocks.containsKey(x, y, z+1) || !blocks.containsKey(x, y, z-1)) {
					if (blocks.containsKey(x, y+1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.ROOF) && blocks.containsKey(x+1, y-1, z) && blocks.containsKey(x-1, y-1, z) && blocks.containsKey(x, y-1, z+1) && blocks.containsKey(x, y-1, z-1)) {
						blocks.put(x, y, z, EnumCubeType.ROOF);
					} else if (blocks.containsKey(x, y+1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.AIR)) {
						if (y < waterLevel) {
							blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
						} else {
							blocks.put(x, y, z, EnumCubeType.GROUND);
						}
					} else if (blocks.containsKey(x, y+1, z) && (blocks.get(x, y+1, z).equals(EnumCubeType.GROUND) || blocks.get(x, y+1, z).equals(EnumCubeType.UNDERGROUND)) && (!blocks.containsKey(x, y+3, z) || !blocks.get(x, y+2, z).equals(EnumCubeType.UNDERGROUND) || !blocks.get(x, y+2, z).equals(EnumCubeType.GROUND))) {
						blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
					} else {
						blocks.put(x, y, z, EnumCubeType.WALL);
					}
				} else if (!blocks.containsKey(x, y-1, z))
				{
					if (y < waterLevel) {
						blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
					} else {
						blocks.put(x, y, z, EnumCubeType.GROUND);
					}
				} else if (y <= waterLevel) {
					blocks.put(x, y, z, EnumCubeType.WATER);
				}
			}
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
	
	protected boolean isBiomeViable(World world, int x, int y, int z, int radius)
	{
		for (int tempX = x - radius; tempX <= x + radius; tempX = tempX + radius)
		for (int tempZ = z - radius; tempZ <= z + radius; tempZ = tempZ + radius)
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
}