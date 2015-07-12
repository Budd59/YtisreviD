package diversity.suppliers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import diversity.configurations.ConfigBiomeGroup;
import diversity.village.StructureVillageApache;
import diversity.village.StructureVillageAztec;
import diversity.village.StructureVillageEgyptian;
import diversity.village.StructureVillageInuit;
import diversity.village.StructureVillageLakeside;
import diversity.village.StructureVillageSettled;
import diversity.village.StructureVillageTibetan;
import diversity.village.AGlobalStructureVillage;
import diversity.village.StructureVillageZulu;

public enum EnumVillage
{
	APACHE (StructureVillageApache.class, ConfigBiomeGroup.APACHE_VILLAGE, true),
	AZTEC (StructureVillageAztec.class, ConfigBiomeGroup.AZTEC_VILLAGE, true),
	INUIT (StructureVillageInuit.class, ConfigBiomeGroup.INUIT_VILLAGE, true),
	SETTLED (StructureVillageSettled.class, ConfigBiomeGroup.SETTLED_VILLAGE, true),
	ZULU (StructureVillageZulu.class, ConfigBiomeGroup.ZULU_VILLAGE, true),
	TIBETAN (StructureVillageTibetan.class, ConfigBiomeGroup.TIBETAN_VILLAGE, true), 
	EGYPTIAN (StructureVillageEgyptian.class, ConfigBiomeGroup.EGYPTIAN_VILLAGE, true),
	LAKESIDE (StructureVillageLakeside.class, ConfigBiomeGroup.LAKESIDE_VILLAGE, true);	
	public AGlobalStructureVillage instance;
	private final boolean canSpawnRandomly;
	private final ConfigBiomeGroup config;
	public final List<EnumVillagePiece> pieces = new ArrayList<EnumVillagePiece>();
	public final List<EnumVillageBasicPiece> defaultPieces = new ArrayList<EnumVillageBasicPiece>();
	
	private static Map<BiomeGenBase, List<EnumVillage>> biomeEnumMap = new HashMap();
	
	private EnumVillage(Class villageClass, ConfigBiomeGroup config, boolean canSpawnRandomly)
	{
		if (villageClass.getSuperclass()==AGlobalStructureVillage.class)
		{
			try
			{
				this.instance = (AGlobalStructureVillage)villageClass.getConstructors()[0].newInstance(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		this.config = config;
		this.canSpawnRandomly = canSpawnRandomly;
	}
	
	public static void load() {
    	biomeEnumMap.clear();
		for (EnumVillage enumVillage : EnumVillage.values())
		{
			if (enumVillage.canSpawnRandomly)
			{
				for (BiomeGenBase biome : enumVillage.config.getBiomes())
				{
					if (!biomeEnumMap.containsKey(biome))
					{
						biomeEnumMap.put(biome, new ArrayList<EnumVillage>(Arrays.asList(enumVillage)));
					}
					else 
					{
						if (!biomeEnumMap.get(biome).contains(enumVillage))
						{
							biomeEnumMap.get(biome).add(enumVillage);
						}
					}
				}
			}
		}
	}
	
	public static boolean canSpawnInBiome(BiomeGenBase biome) {
		return biomeEnumMap.get(biome) != null && !biomeEnumMap.get(biome).isEmpty();
	}
	
	public static AGlobalStructureVillage getRandomVillage(BiomeGenBase biome, Random rand)
	{
		List<EnumVillage> list = biomeEnumMap.get(biome);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		return list.get(rand.nextInt(list.size())).instance;
	}
}