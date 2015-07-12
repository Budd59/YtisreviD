package diversity.configurations;

import java.util.ArrayList;
import java.util.List;

import diversity.configurations.ConfigEconomy.EPrice;
import diversity.configurations.ConfigEconomy.GPrice;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class AConfigTool
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
    	ConfigGenerationRate.values();
    	ConfigBiomeGroup.values();    	
    	ConfigGlobal.values();
    	GPrice.values();
    	EPrice.values();
	}

	public static void loadAllConfig(boolean isWorld) {
    	ConfigGenerationRate.loadConfig(isWorld);
    	ConfigBiomeGroup.loadConfig(isWorld);    	
    	ConfigGlobal.loadConfig(isWorld);
    	ConfigEconomy.loadConfig(isWorld);
	}
	
	public static void saveAllConfig(boolean isWorld) {
    	ConfigGenerationRate.saveConfig(isWorld);
    	ConfigBiomeGroup.saveConfig(isWorld);
    	ConfigGlobal.saveConfig(isWorld);
    	ConfigEconomy.saveConfig(isWorld);
	}
}
