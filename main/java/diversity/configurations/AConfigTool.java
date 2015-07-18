package diversity.configurations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import diversity.configurations.ConfigEconomy.EnumObject;
import diversity.configurations.ConfigEconomy.EnumGroupObject;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public abstract class AConfigTool
{
	static String join(BiomeGenBase[] biomes) {
		biomes = biomes.clone();
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
	    
	    Set<BiomeGenBase> toRemoveList = new HashSet<BiomeGenBase>();
	    List<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
    	List<BiomeGenBase> typeList = new ArrayList<BiomeGenBase>();

    	
	    for (BiomeDictionary.Type enumType : BiomeDictionary.Type.values()) {
	    	if (BiomeDictionary.getBiomesForType(enumType).length != 0) {
			    for (BiomeGenBase biome : biomes) {
			    	biomeList.add(biome);
			    }
			    for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(enumType)) {
			    	typeList.add(biome);
			    }
			    
		    	if (biomeList.containsAll(typeList)) {
		    		toRemoveList.addAll(typeList);
			        sb.append(loopDelim);
		    		sb.append(enumType.name());
			        loopDelim = ",";
		    	}
		    	
		    	biomeList.clear();
		    	typeList.clear();
	    	}
		}
	    
	    for (BiomeGenBase biome : biomes) {
	    	biomeList.add(biome);
	    }
	    biomeList.removeAll(toRemoveList);
	    
	    if (toRemoveList.isEmpty()) {
	        loopDelim = "";
	    }
	    
	    for(BiomeGenBase biome : biomeList) {
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
		Set<BiomeGenBase> biomes = new HashSet<BiomeGenBase>();
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
					boolean isInDictionnary = false;
					for (BiomeDictionary.Type enumType : BiomeDictionary.Type.values()) {
						if (value.equals(enumType.name())) {
					    	List<BiomeGenBase> typeList = new ArrayList<BiomeGenBase>();
							for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(enumType)) {
						    	typeList.add(biome);
						    }
							biomes.addAll(typeList);
							isInDictionnary = true;
							break;
						}
					}
					if (!isInDictionnary) {
						try {
							BiomeGenBase biome = BiomeGenBase.getBiome(Integer.valueOf(value));
							if (biome != null) {
								biomes.add(biome);
							}
						}
						catch (Exception e) {}
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
    	EnumGroupObject.values();
    	EnumObject.values();
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
