package diversity.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAbyss  extends WorldGenCave
{
	public WorldGenAbyss()
	{
		super(25, 6, 8, 20, 3, 6, BiomeGenBase.ocean);
	}
	
	public IWorldGenerator create() {
		return new WorldGenAbyss();
	}
	
	@Override
	protected void generateGround(World world, Random random, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.gravel);
	}

	@Override
	protected void generateRoof(World world, Random random, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.water);
	}

	@Override
	protected void generateWall(World world, Random random, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.gravel);
	}

	@Override
	protected void generateWater(World world, Random random, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.water);		
	}

	@Override
	protected void generateAir(World world, Random random, Integer x, Integer y, Integer z)
	{
		world.setBlock(x, y, z, Blocks.water);		
	}

	@Override
	protected void generateUnderGround(World world, Random random, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.gravel);
	}
}
