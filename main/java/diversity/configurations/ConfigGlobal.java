package diversity.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraft.entity.EntityList;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Loader;
import diversity.Diversity;

public enum ConfigGlobal
{
	TICK_UNTIL_RANDOM_VILLAGER_BECOMES_CHIEF ("5000"),
	REMOVE_VANILLA_SPAWN_EGG ("true"),
	CAN_SPAWN_MOD_VILLAGES ("true"),
	CAN_SPAWN_MOD_STRUCTURES ("true"),
	CAN_SPAWN_MOD_CAVES ("true"),
	CAN_SPAWN_VANILLA_VILLAGES ("false"),
	CAN_SPAWN_VANILLA_STRUCTURES ("false");
	
	private String value;
	
	private ConfigGlobal(String value) {
		this.value = value;
	}

	public int getIntegerConfig() {
		return Integer.valueOf(value);
	}
	
	public String getStringConfig() {
		return value;
	}
	
	public boolean getBooleanConfig() {
		return Boolean.valueOf(value);
	}
	
	private static final String configNameFile = "diversity-global.cfg";
	private static final String configFile = Loader.instance().getConfigDir() + "/" + configNameFile;
	
	public static void saveConfig(boolean isWorld) {
		Properties properties = new Properties();
		for (ConfigGlobal config : ConfigGlobal.values()) {
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
		
		for (ConfigGlobal config : ConfigGlobal.values()) {
			String value = properties.getProperty(config.name());
			if (value != null && !value.isEmpty()) {
				config.value = value;
				Diversity.Divlogger.log(Level.INFO, Boolean.valueOf(value)+"");

			}
		}
		
		if (REMOVE_VANILLA_SPAWN_EGG.getBooleanConfig() && EntityList.entityEggs.containsKey(Integer.valueOf(120))) {
			EntityList.entityEggs.remove(Integer.valueOf(120));
		} else if (!REMOVE_VANILLA_SPAWN_EGG.getBooleanConfig() && !EntityList.entityEggs.containsKey(Integer.valueOf(120))) {
			EntityList.entityEggs.put(Integer.valueOf(120), new EntityList.EntityEggInfo(120, 5651507, 12422002));
		}
	}
}
