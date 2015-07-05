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
import diversity.structure.DwarvenCity;
import diversity.structure.DwarvenCave;
import diversity.structure.DwarvenScaffolding;
import diversity.structure.EgyptianPyramid;
import diversity.structure.GlobalFeature;
import diversity.structure.Inn;
import diversity.structure.WitchHouse;
import diversity.structure.WitchHutt;

public enum EnumStructure
{	
	EGYPTIAN_PYRAMID (ConfigBiomeGroup.EGYPTIAN_PYRAMID, EgyptianPyramid.class),
	AZTEC_PYRAMID (ConfigBiomeGroup.AZTEC_PYRAMID, AztecPyramid.class),
	CATACOMB (ConfigBiomeGroup.CATACOMB, Catacomb.class),
	WITCH_HUTT (ConfigBiomeGroup.WITCH_HUTT, WitchHutt.class),
	INN (ConfigBiomeGroup.INN, Inn.class),
	DWARVEN_CITY (DwarvenCity.class),
	DWARVEN_SCAFFOLDING (DwarvenScaffolding.class),
	WITCH_HOUSE (WitchHouse.class);
	
	public int totalWeight;
	private final ConfigBiomeGroup config;
	public final Class pieceClass;
	
	private static Map<BiomeGenBase, List<EnumStructure>> biomeEnumMap = new HashMap();
	
	private EnumStructure(ConfigBiomeGroup config, Class pieceClass)
	{
		this.config = config;
		this.pieceClass = pieceClass;
	}
	
	private EnumStructure(Class pieceClass)
	{
		this(null, pieceClass);
	}
	
	public static void load() {
		biomeEnumMap.clear();
		for (EnumStructure enumStructure : EnumStructure.values())
		{
			if (enumStructure.config != null)
			{
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
		if (pieceClass.equals(EgyptianPyramid.class)) {
			return new EgyptianPyramid(random, coordX, coordZ);
		}
		if (pieceClass.equals(AztecPyramid.class)) {
			return new AztecPyramid(random, coordX, coordZ);
		}
		if (pieceClass.equals(Catacomb.class)) {
			return new Catacomb(random, coordX, coordZ);
		}
		if (pieceClass.equals(WitchHutt.class)) {
			return new WitchHutt(random, coordX, coordZ);
		}
		if (pieceClass.equals(Inn.class)) {
			return new Inn(random, coordX, coordZ);
		}
		if (pieceClass.equals(DwarvenCity.class)) {
			return new DwarvenCity(random, coordX, coordZ);
		}
		if (pieceClass.equals(DwarvenScaffolding.class)) {
			return new DwarvenScaffolding(random, coordX, coordZ);
		}
		if (pieceClass.equals(WitchHouse.class)) {
			return new WitchHouse(random, coordX, coordZ);
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