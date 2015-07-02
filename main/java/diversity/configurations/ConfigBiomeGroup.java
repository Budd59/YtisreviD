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
import diversity.suppliers.EnumStructure;

public enum ConfigBiomeGroup
{
	EGYPTIAN_PYRAMID (BiomeGenBase.desert, BiomeGenBase.desertHills),
	AZTEC_PYRAMID (BiomeGenBase.jungle, BiomeGenBase.jungleHills),
	CATACOMB (BiomeGenBase.forest),
	WITCH_HUTT (BiomeGenBase.swampland),
	INN (BiomeGenBase.plains),
	LOST_CAVE (BiomeGenBase.jungle, BiomeGenBase.jungleHills),
	SHROOM_CAVE (BiomeGenBase.swampland),
	YETI_DEN (BiomeGenBase.iceMountains, BiomeGenBase.icePlains.createMutation()),
	SPIDER_DEN (BiomeGenBase.roofedForest),
	DWARVEN_CAVE (BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsPlus),
	APACHE_VILLAGE (BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F),
	AZTEC_VILLAGE (BiomeGenBase.jungle),
	INUIT_VILLAGE (BiomeGenBase.icePlains),
	SETTLED_VILLAGE (BiomeGenBase.plains),
	ZULU_VILLAGE (BiomeGenBase.savanna),
	TIBETAN_VILLAGE (BiomeGenBase.extremeHills), 
	EGYPTIAN_VILLAGE (BiomeGenBase.desert),
	LAKESIDE_VILLAGE (BiomeGenBase.swampland);
	
	private BiomeGenBase[] biomes;
	
	private ConfigBiomeGroup(BiomeGenBase... biomes) {
		this.biomes = biomes;
	}
	
	private static final String configFile = Loader.instance().getConfigDir() + "/diversity-biome-groups.cfg";

	public static void saveConfig() {
		Properties properties = new Properties();
		for (ConfigBiomeGroup config : ConfigBiomeGroup.values()) {
			properties.setProperty(config.name(), AConfigTool.join(config.biomes));
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
		
		for (ConfigBiomeGroup config : ConfigBiomeGroup.values()) {
			BiomeGenBase[] biomes = AConfigTool.split(properties.getProperty(config.name()));
			if (biomes != null) {
				config.biomes = biomes;
			}
		}
	}

	public BiomeGenBase[] getBiomes() {
		return biomes;
	}
}
