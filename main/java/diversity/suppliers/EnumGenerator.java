package diversity.suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import diversity.configurations.ConfigCaveBiome;
import diversity.configurations.ConfigCaveRate;
import diversity.configurations.ConfigVillageRate;
import diversity.world.WorldGenAbyss;
import diversity.world.WorldGenCave;
import diversity.world.WorldGenDwarvesCave;
import diversity.world.WorldGenIceCave;
import diversity.world.WorldGenLostCave;
import diversity.world.WorldGenMushroomCave;
import diversity.world.WorldGenNewCave;
import diversity.world.WorldGenSpiderDen;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.feature.WorldGenerator;

public enum EnumGenerator implements IWorldGenerator
{	
	//ICE_CAVE (new WorldGenIceCave(), 15, ConfigCaveBiome.ICE_CAVE),
	//MUSHROOM_CAVE (new WorldGenMushroomCave(), 15, ConfigCaveBiome.MUSHROOM_CAVE),
	//LOST_CAVE (new WorldGenLostCave(), 10, ConfigCaveBiome.LOST_CAVE),
	// SPIDER_DEN (new WorldGenSpiderDen(), 5, ConfigCaveBiome.SPIDER_DEN),
	DWARVES_CAVE (new WorldGenDwarvesCave(), 15, ConfigCaveBiome.DWARVES_CAVE);
	
	private final ConfigCaveBiome config;
	public final IWorldGenerator worldGen;
	private final int priority;
	
	private EnumGenerator(IWorldGenerator worldGen, ConfigCaveBiome config) {
		this(worldGen, 10, config);
	}
	
	private EnumGenerator(IWorldGenerator worldGen, int priority, ConfigCaveBiome config) {
		this.worldGen = worldGen;
		this.config = config;
		this.priority = priority;
	}
	
	public boolean canGenerate(World world, int x, int y, int z) {
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		for (int i = 0; i < config.biomes.length; i++)
		{
			if (config.biomes[i]!=null && config.biomes[i].equals(biome)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.getWorldInfo().getTerrainType().equals(WorldType.FLAT))
			return;
		if (worldGen instanceof WorldGenNewCave) {
			int spawnRate = ConfigCaveRate.caveBiomeSpawnRate.getIntegerConfig();
			if (spawnRate <= 0) {
				spawnRate = 1;
			}
		    if (new Random().nextInt(100 + spawnRate*100) == 0)
		    {
		    	((WorldGenNewCave) worldGen).create().generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		    }
		}
	}
	
	public static void load() {
		for (EnumGenerator enumGenerator : EnumGenerator.values()) {
			GameRegistry.registerWorldGenerator(enumGenerator, 1);  
		}
	}
}
