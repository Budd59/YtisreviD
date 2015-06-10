package diversity.suppliers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import diversity.configurations.ConfigCaveBiome;
import diversity.configurations.ConfigStructureBiome;
import diversity.structure.StructureDesert;
import diversity.structure.StructureForest;
import diversity.structure.StructureJungle;
import diversity.structure.StructurePlain;
import diversity.structure.StructureSwamp;
import diversity.structure.StructureTools;

public enum EnumStructure
{
	DESERT (new StructureDesert(), ConfigStructureBiome.DESERT),
	JUNGLE (new StructureJungle(), ConfigStructureBiome.JUNGLE),
	FOREST (new StructureForest(), ConfigStructureBiome.FOREST),
	SWAMP (new StructureSwamp(), ConfigStructureBiome.SWAMP),
	PLAIN (new StructurePlain(), ConfigStructureBiome.PLAIN);
	
	public StructureTools instance;
	public int totalWeight;
	public List<EnumStructurePiece> components = new ArrayList<EnumStructurePiece>();
	
	private final ConfigStructureBiome config;
	
	private static Map<BiomeGenBase, List<EnumStructure>> biomeEnumMap = new HashMap();
	
	private EnumStructure(StructureTools instance, ConfigStructureBiome config)
	{
		this.instance = instance;
		this.config = config;
	}
	
	public static void load() {
		biomeEnumMap.clear();
		for (EnumStructure enumStructure : EnumStructure.values())
		for (BiomeGenBase biome : enumStructure.config.biomes)
		{
			if (!biomeEnumMap.containsKey(biome))
			{
				biomeEnumMap.put(biome, new ArrayList<EnumStructure>(Arrays.asList(enumStructure)));
			}
			else 
			{
				if (!biomeEnumMap.get(biome).contains(enumStructure))
				{
					biomeEnumMap.get(biome).add(enumStructure);
				}
			}
		}
	}
	
	public static boolean canSpawnInBiome(BiomeGenBase biome) {
		return !(biomeEnumMap.get(biome) == null || biomeEnumMap.get(biome).isEmpty());
	}

	public static StructureTools getRandomStructure(BiomeGenBase biome, Random random) {
		List<EnumStructure> list = biomeEnumMap.get(biome);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		return list.get(random.nextInt(list.size())).instance;
	}
}