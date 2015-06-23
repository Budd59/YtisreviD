package diversity.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import cpw.mods.fml.common.Loader;

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
	
	private static final String configFile = Loader.instance().getConfigDir() + "/diversity-generation-rate.cfg";

	public static void saveConfig() {
		Properties properties = new Properties();
		for (ConfigGenerationRate config : ConfigGenerationRate.values()) {
			properties.setProperty(config.name(), config.value);
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
		
		for (ConfigGenerationRate config : ConfigGenerationRate.values()) {
			String value = properties.getProperty(config.name());
			if (value != null && !value.isEmpty()) {
				config.value = value;
			}
		}
	}
}
