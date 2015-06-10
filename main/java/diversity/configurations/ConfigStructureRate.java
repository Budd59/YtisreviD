package diversity.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import cpw.mods.fml.common.Loader;

public enum ConfigStructureRate
{
	maxDistanceBetweenStructures(32),
	minDistanceBetweenStructures(8);
	
	private String value;
	
	private ConfigStructureRate(int config) {
		this.value = String.valueOf(config);
	}
	
	public int getIntegerConfig() {
		return Integer.valueOf(value);
	}
	
	public String getStringConfig() {
		return value;
	}
	
	private static final String configFile = Loader.instance().getConfigDir() + "/diversity-structure-rate.cfg";

	public static void saveConfig() {
		Properties properties = new Properties();
		for (ConfigStructureRate config : ConfigStructureRate.values()) {
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
		
		for (ConfigStructureRate config : ConfigStructureRate.values()) {
			String value = properties.getProperty(config.name());
			if (value != null && !value.isEmpty()) {
				config.value = value;
			}
		}
	}
}
