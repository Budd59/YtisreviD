package diversity.configurations;

import io.netty.util.internal.StringUtil;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.minecraft.util.StringUtils;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Loader;
import diversity.suppliers.EnumStructure;

public enum ConfigBiomeGroup
{
	EGYPTIAN_PYRAMID (BiomeGenBase.desert, BiomeGenBase.desertHills),
	AZTEC_PYRAMID (BiomeGenBase.jungle, BiomeGenBase.jungleHills),
	CATACOMB (BiomeGenBase.forest),
	WITCH_HUTT (BiomeGenBase.swampland),
	INN (BiomeGenBase.plains),
	JUNGLE_VALLEY (BiomeGenBase.jungle, BiomeGenBase.jungleHills),
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
	
	private static final String configNameFile = "diversity-biome-groups.cfg";
	private static final String configFile = Loader.instance().getConfigDir() + "/" + configNameFile;
	
	public static void saveConfig(boolean isWorld) {
		Properties properties = new Properties();
		for (ConfigBiomeGroup config : ConfigBiomeGroup.values()) {
			properties.setProperty(config.name(), AConfigTool.join(config.biomes));
		}
		
		try {
			File file;
			if (isWorld) {
				File folder = new File(DimensionManager.getCurrentSaveRootDirectory(), "config");
		    	folder.mkdir();
				file = new File(folder, configNameFile);
			} else {
				file = new File(configFile);
			}
			properties.store(new FileOutputStream(file), null);
		} catch (Exception e) {
		}
	}
	

	public static void loadConfig(boolean isWorld) {
		Properties properties = new Properties();
		try {
			FileInputStream inputStream;
			if (isWorld) {
				File folder = new File(DimensionManager.getCurrentSaveRootDirectory(), "config");
		    	folder.mkdir();
				inputStream = new FileInputStream(new File(folder, configNameFile));
			} else {
				inputStream = new FileInputStream(configFile);
			}
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
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
