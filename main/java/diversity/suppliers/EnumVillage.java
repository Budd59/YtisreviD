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
import diversity.village.VillageApache;
import diversity.village.VillageAztec;
import diversity.village.VillageEgyptian;
import diversity.village.VillageInuit;
import diversity.village.VillageLakeside;
import diversity.village.VillageSettled;
import diversity.village.VillageTibetan;
import diversity.village.VillageTools;
import diversity.village.VillageZulu;

public enum EnumVillage
{
	APACHE (VillageApache.class, ConfigBiomeGroup.APACHE_VILLAGE, true),
	AZTEC (VillageAztec.class, ConfigBiomeGroup.AZTEC_VILLAGE, true),
	INUIT (VillageInuit.class, ConfigBiomeGroup.INUIT_VILLAGE, true),
	SETTLED (VillageSettled.class, ConfigBiomeGroup.SETTLED_VILLAGE, true),
	ZULU (VillageZulu.class, ConfigBiomeGroup.ZULU_VILLAGE, true),
	TIBETAN (VillageTibetan.class, ConfigBiomeGroup.TIBETAN_VILLAGE, true), 
	EGYPTIAN (VillageEgyptian.class, ConfigBiomeGroup.EGYPTIAN_VILLAGE, true),
	LAKESIDE (VillageLakeside.class, ConfigBiomeGroup.LAKESIDE_VILLAGE, true);	
	public VillageTools instance;
	private final boolean canSpawnRandomly;
	private final ConfigBiomeGroup config;
	public final List<EnumVillagePiece> pieces = new ArrayList<EnumVillagePiece>();
	public final List<EnumVillageBasicPiece> defaultPieces = new ArrayList<EnumVillageBasicPiece>();
	
	private static Map<BiomeGenBase, List<EnumVillage>> biomeEnumMap = new HashMap();
	
	private EnumVillage(Class villageClass, ConfigBiomeGroup config, boolean canSpawnRandomly)
	{
		if (villageClass.getSuperclass()==VillageTools.class)
		{
			try
			{
				this.instance = (VillageTools)villageClass.getConstructors()[0].newInstance(this);
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
    	for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
    		BiomeManager.addVillageBiome(biome, true);
    	}
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
	
	public static VillageTools getRandomVillage(BiomeGenBase biome, Random rand)
	{
		List<EnumVillage> list = biomeEnumMap.get(biome);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		return list.get(rand.nextInt(list.size())).instance;
	}
}