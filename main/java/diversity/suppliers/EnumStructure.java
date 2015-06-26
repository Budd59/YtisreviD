package diversity.suppliers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.world.biome.BiomeGenBase;
import diversity.Diversity;
import diversity.configurations.ConfigBiomeGroup;
import diversity.structure.GlobalFeature;

public enum EnumStructure
{
	EGYPTIAN_PYRAMID (ConfigBiomeGroup.EGYPTIAN_PYRAMID),
	JUNGLE (ConfigBiomeGroup.AZTEC_PYRAMID),
	FOREST (ConfigBiomeGroup.CATACOMB),
	SWAMP (ConfigBiomeGroup.WITCH_HUTT),
	PLAIN (ConfigBiomeGroup.INN),
	SNOW_MOUNTAINS (ConfigBiomeGroup.DWARVES_VILLAGE);
	
	public int totalWeight;
	public final List<EnumStructurePiece> components = new ArrayList<EnumStructurePiece>();	
	private final ConfigBiomeGroup config;
	
	private static Map<BiomeGenBase, List<EnumStructure>> biomeEnumMap = new HashMap();
	
	private EnumStructure(ConfigBiomeGroup config)
	{
		this.config = config;
	}
	
	public static void load() {
		biomeEnumMap.clear();
		for (EnumStructure enumStructure : EnumStructure.values())
		for (BiomeGenBase biome : enumStructure.config.getBiomes())
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
	
	public static GlobalFeature getRandomComponent(BiomeGenBase biome, Random random, int coordX, int coordZ) {
		List<EnumStructure> list = biomeEnumMap.get(biome);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		EnumStructure structure = list.get(random.nextInt(list.size()));
		
		try {
			EnumStructurePiece piece = structure.components.get(random.nextInt(structure.components.size()));
			Diversity.Divlogger.log(Level.INFO, "structure : " + piece.name());
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