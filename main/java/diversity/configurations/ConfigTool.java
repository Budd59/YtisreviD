package diversity.configurations;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;

public class ConfigTool
{
	static String join(BiomeGenBase[] biomes) {
		int biomeNumber = 0;
		for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
			if (biome != null) {
				biomeNumber++;
			}
		}
		if (biomes.length == biomeNumber) {
			return "ALL";
		}
	    StringBuilder sb = new StringBuilder();
	    String loopDelim = "";
	    for(BiomeGenBase biome : biomes) {
	    	if (biome != null) {
		        sb.append(loopDelim);
		        sb.append(biome.biomeID);            
		        loopDelim = ",";
	    	}
	    }
	    return sb.toString();
	}
	
	static BiomeGenBase[] split(String config) {
		if (config == null) {
			return null;
		}
		List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
		if (config.equals("ALL")) {
			for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
				if (biome != null) {
					biomes.add(biome);
				}
			}
		} else {
			String[] values = config.split(",");
			for (String value : values) {
				if (value != null && !value.isEmpty()) {
					BiomeGenBase biome = BiomeGenBase.getBiome(Integer.valueOf(value));
					if (biome != null) {
						biomes.add(biome);
					}
				
				}
			}
		}
		return biomes.toArray(new BiomeGenBase[biomes.size()]);
	}
	
	public static void values() {
    	ConfigVillageRate.values();
       	ConfigVillageBiome.values();   	
    	ConfigStructureRate.values();    	
    	ConfigStructureBiome.values();    	
       	ConfigCaveRate.values();    	
    	ConfigCaveBiome.values();
    	ConfigVillager.values();
	}

	public static void loadAllConfig() {
    	ConfigVillageRate.loadConfig();
       	ConfigVillageBiome.loadConfig();   	
    	ConfigStructureRate.loadConfig();    	
    	ConfigStructureBiome.loadConfig();    	
       	ConfigCaveRate.loadConfig();    	
    	ConfigCaveBiome.loadConfig();
    	ConfigVillager.loadConfig();
	}
	
	public static void saveAllConfig() {
    	ConfigVillageRate.saveConfig();
    	ConfigVillageBiome.saveConfig();
    	ConfigStructureRate.saveConfig();
    	ConfigStructureBiome.saveConfig();
    	ConfigCaveRate.saveConfig();
    	ConfigCaveBiome.saveConfig();
    	ConfigVillager.saveConfig();
	}
}
