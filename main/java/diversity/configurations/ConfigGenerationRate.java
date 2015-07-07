package diversity.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Loader;
import diversity.Diversity;

public enum ConfigGenerationRate
{
	MAXDISTANCEBETWEENVILLAGES(32),
	MINDISTANCEBETWEENVILLAGES(8),
	MAXDISTANCEBETWEENSTRUCTURES(32),
	MINDISTANCEBETWEENSTRUCTURES(8),
	MAXDISTANCEBETWEENCAVES(32),
	MINDISTANCEBETWEENCAVES(8);
	
	private String value;
	
	private ConfigGenerationRate(int config) {
		this.value = String.valueOf(config);
	}
	
	private ConfigGenerationRate(String config) {
		this.value = config;
	}
	
	public int getIntegerConfig() {
		return Integer.valueOf(value);
	}
	
	private static final String configNameFile = "diversity-generation-rate.cfg";
	private static final String configFile = Loader.instance().getConfigDir() + "/" + configNameFile;
	
	public static void saveConfig(boolean isWorld) {
		Properties properties = new Properties();
		for (ConfigGenerationRate config : ConfigGenerationRate.values()) {
			properties.setProperty(config.name(), config.value);
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
		
		for (ConfigGenerationRate config : ConfigGenerationRate.values()) {
			String value = properties.getProperty(config.name());
			if (value != null && !value.isEmpty()) {
				config.value = value;
			}
		}
	}
}
