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
import net.minecraft.world.gen.structure.MapGenStructureIO;
import diversity.Diversity;
import diversity.configurations.ConfigBiomeGroup;
import diversity.structure.AztecPyramid;
import diversity.structure.Catacomb;
import diversity.structure.DwarvesCave;
import diversity.structure.EgyptianPyramid;
import diversity.structure.GlobalFeature;
import diversity.structure.Inn;
import diversity.structure.WitchHouse;
import diversity.structure.WitchHutt;

public enum EnumStructure
{	
	EGYPTIAN_PYRAMID (ConfigBiomeGroup.EGYPTIAN_PYRAMID, 25, EgyptianPyramid.class),
	AZTEC_PYRAMID (ConfigBiomeGroup.AZTEC_PYRAMID, 25, AztecPyramid.class),
	CATACOMB (ConfigBiomeGroup.CATACOMB, 25, Catacomb.class),
	WITCH_HUTT (ConfigBiomeGroup.WITCH_HUTT, 25, WitchHutt.class),
	INN (ConfigBiomeGroup.INN, 25, Inn.class),
	DWARVES_CITY (ConfigBiomeGroup.DWARVES_CAVE, 25, DwarvesCave.class),
	WITCH_HOUSE (ConfigBiomeGroup.MUSHROOM_CAVE, 25, WitchHouse.class);
	
	public int totalWeight;
	private final ConfigBiomeGroup config;
	public final int weight;
	public final Class pieceClass;
	
	private static Map<BiomeGenBase, List<EnumStructure>> biomeEnumMap = new HashMap();
	
	private EnumStructure(ConfigBiomeGroup config, int weight, Class pieceClass)
	{
		this.config = config;
		this.weight = weight;
		this.pieceClass = pieceClass;
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
	
	public static EnumStructure getRandomStructure(BiomeGenBase biome, Random random) {
		List<EnumStructure> list = biomeEnumMap.get(biome);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		return list.get(random.nextInt(list.size()));
	}
	
	public GlobalFeature getStructureComponent(Random random, int coordX, int coordZ) {
		try {
			Diversity.Divlogger.log(Level.INFO, "EnumStructure : " + name());
			return (GlobalFeature)pieceClass.getConstructors()[0].newInstance(random, coordX, coordZ);
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
	
	public static void register() {
    	for (EnumStructure structure : EnumStructure.values())
    	{
            MapGenStructureIO.func_143031_a(structure.pieceClass, structure.name());
    	}
	}
}