package diversity.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.Loader;
import diversity.world.WorldGenIceCave;
import diversity.world.WorldGenLostCave;
import diversity.world.WorldGenMushroomCave;

public enum ConfigCaveBiome
{
	MUSHROOM_CAVE (BiomeGenBase.swampland),
	LOST_CAVE (BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills),
	ICE_CAVE (BiomeGenBase.iceMountains, BiomeGenBase.icePlains.createMutation()),
	SPIDER_DEN (BiomeGenBase.roofedForest),
	DWARVES_CAVE (BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus);
	
	public BiomeGenBase[] biomes;
	
	private ConfigCaveBiome(BiomeGenBase... biomes) {
		this.biomes = biomes;
	}
	
	private static final String configFile = Loader.instance().getConfigDir() + "/diversity-cave-biomes.cfg";

	public static void saveConfig() {
		Properties properties = new Properties();
		for (ConfigCaveBiome config : ConfigCaveBiome.values()) {
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
		
		for (ConfigCaveBiome config : ConfigCaveBiome.values()) {
			BiomeGenBase[] biomes = ConfigTool.split(properties.getProperty(config.name()));
			if (biomes != null) {
				config.biomes = biomes;
			}
		}
	}
}
