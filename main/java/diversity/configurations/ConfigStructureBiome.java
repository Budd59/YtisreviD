package diversity.configurations;

import io.netty.util.internal.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.minecraft.util.StringUtils;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.Loader;
import diversity.structure.StructureDesert;
import diversity.structure.StructureForest;
import diversity.structure.StructureJungle;
import diversity.structure.StructurePlain;
import diversity.structure.StructureSwamp;
import diversity.suppliers.EnumStructure;
import diversity.village.VillageApache;
import diversity.village.VillageAztec;
import diversity.village.VillageEgyptian;
import diversity.village.VillageInuit;
import diversity.village.VillageLakeside;
import diversity.village.VillageSettled;
import diversity.village.VillageTibetan;
import diversity.village.VillageZulu;

public enum ConfigStructureBiome
{
	DESERT (BiomeGenBase.desert, BiomeGenBase.desertHills),
	JUNGLE (BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills),
	FOREST (BiomeGenBase.forest),
	SWAMP (BiomeGenBase.swampland),
	PLAIN (BiomeGenBase.plains);
	
	public BiomeGenBase[] biomes;
	
	private ConfigStructureBiome(BiomeGenBase... biomes) {
		this.biomes = biomes;
	}
	
	private static final String configFile = Loader.instance().getConfigDir() + "/diversity-structure-biomes.cfg";

	public static void saveConfig() {
		Properties properties = new Properties();
		for (ConfigStructureBiome config : ConfigStructureBiome.values()) {
			properties.setProperty(config.name(), ConfigTool.join(config.biomes));
		}
		
		try {
			File file = new File(configFile);
			properties.store(new FileOutputStream(file), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadConfig() {
		Properties properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(configFile);
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		for (ConfigStructureBiome config : ConfigStructureBiome.values()) {
			BiomeGenBase[] biomes = ConfigTool.split(properties.getProperty(config.name()));
			if (biomes != null) {
				config.biomes = biomes;
			}
		}
		
		EnumStructure.load();
	}
}
