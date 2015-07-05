package diversity.suppliers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import diversity.configurations.ConfigBiomeGroup;
import diversity.structure.DwarvenCave;
import diversity.structure.GlobalFeature;
import diversity.structure.JungleValley;
import diversity.structure.ShroomCave;
import diversity.structure.SpiderDen;
import diversity.structure.YetiDen;

public enum EnumCave
{
	DWARVEN_CAVE (ConfigBiomeGroup.DWARVEN_CAVE, DwarvenCave.class, EnumStructure.DWARVEN_CITY, EnumStructure.DWARVEN_SCAFFOLDING),
	JUNGLE_VALLEY (ConfigBiomeGroup.JUNGLE_VALLEY, JungleValley.class),
	SHROOM_CAVE (ConfigBiomeGroup.SHROOM_CAVE, ShroomCave.class, EnumStructure.WITCH_HOUSE),
	YETI_DEN (ConfigBiomeGroup.YETI_DEN, YetiDen.class),
	SPIDER_DEN (ConfigBiomeGroup.SPIDER_DEN, SpiderDen.class);
	
	private final ConfigBiomeGroup config;
	public final Class pieceClass;
	private EnumStructure[] structure;
	
	private static Map<BiomeGenBase, List<EnumCave>> biomeEnumMap = new HashMap();
	
	private EnumCave(ConfigBiomeGroup config, Class pieceClass)
	{
		this.config = config;
		this.pieceClass = pieceClass;
	}
	
	private EnumCave(ConfigBiomeGroup config, Class pieceClass, EnumStructure... structure)
	{
		this.config = config;
		this.structure = structure;
		this.pieceClass = pieceClass;
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
	
	public static EnumCave getRandomCave(BiomeGenBase biome, Random random) {
		List<EnumCave> list = biomeEnumMap.get(biome);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		return list.get(random.nextInt(list.size()));
	}
		
	public GlobalFeature getCaveComponent(Random random, int coordX, int coordZ) {	
		if (pieceClass.equals(DwarvenCave.class)) {
			return new DwarvenCave(random, coordX, coordZ);
		}
		if (pieceClass.equals(JungleValley.class)) {
			return new JungleValley(random, coordX, coordZ);
		}
		if (pieceClass.equals(ShroomCave.class)) {
			return new ShroomCave(random, coordX, coordZ);
		}
		if (pieceClass.equals(YetiDen.class)) {
			return new YetiDen(random, coordX, coordZ);
		}
		if (pieceClass.equals(SpiderDen.class)) {
			return new SpiderDen(random, coordX, coordZ);
		}
		return null;
	}
	
	public GlobalFeature getStructureComponent(int structureNumber, Random random, int coordX, int coordZ) {
		if (structure != null) {
			return structure[structureNumber].getStructureComponent(random, coordX, coordZ);
		}
		return null;
	}

	public static void register() {
    	for (EnumCave structure : EnumCave.values())
    	{
            MapGenStructureIO.func_143031_a(structure.pieceClass, structure.name());
    	}
	}
}
