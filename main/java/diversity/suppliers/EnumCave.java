package diversity.suppliers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import diversity.configurations.ConfigBiomeGroup;
import diversity.structure.GlobalFeature;

public enum EnumCave
{
	DWARVES_CAVE (ConfigBiomeGroup.DWARVES_VILLAGE);
	
	public int totalWeight;
	public List<EnumCavePiece> components = new ArrayList<EnumCavePiece>();
	
	private final ConfigBiomeGroup config;
	
	private static Map<BiomeGenBase, List<EnumCave>> biomeEnumMap = new HashMap();
	
	private EnumCave(ConfigBiomeGroup config)
	{
		this.config = config;
	}
	
	public static void load() {
		biomeEnumMap.clear();
		for (EnumCave enumStructure : EnumCave.values())
		for (BiomeGenBase biome : enumStructure.config.getBiomes())
		{
			if (!biomeEnumMap.containsKey(biome))
			{
				biomeEnumMap.put(biome, new ArrayList<EnumCave>(Arrays.asList(enumStructure)));
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
		
	public static GlobalFeature getRandomComponent(BiomeGenBase biome, Random random, int coordX, int coordZ) {
		List<EnumCave> list = biomeEnumMap.get(biome);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		EnumCave structure = list.get(random.nextInt(list.size()));
		
		try {
			EnumCavePiece piece = structure.components.get(random.nextInt(structure.components.size()));
			return (GlobalFeature)piece.pieceClass.getConstructors()[1].newInstance(random, coordX, coordZ);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
