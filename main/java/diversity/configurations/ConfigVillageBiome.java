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
import diversity.suppliers.EnumVillage;
import diversity.village.VillageApache;
import diversity.village.VillageAztec;
import diversity.village.VillageEgyptian;
import diversity.village.VillageInuit;
import diversity.village.VillageLakeside;
import diversity.village.VillageSettled;
import diversity.village.VillageTibetan;
import diversity.village.VillageZulu;

public enum ConfigVillageBiome
{
	APACHE (BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F),
	AZTEC (BiomeGenBase.jungle),
	INUIT (BiomeGenBase.icePlains),
	SETTLED (BiomeGenBase.plains),
	ZULU (BiomeGenBase.savanna),
	TIBETAN (BiomeGenBase.extremeHills), 
	EGYPTIAN (BiomeGenBase.desert),
	LAKESIDE (BiomeGenBase.swampland);
	
	public BiomeGenBase[] biomes;
	
	private ConfigVillageBiome(BiomeGenBase... biomes) {
		this.biomes = biomes;
	}
	
	private static final String configFile = Loader.instance().getConfigDir() + "/diversity-village-biomes.cfg";

	public static void saveConfig() {
		Properties properties = new Properties();
		for (ConfigVillageBiome config : ConfigVillageBiome.values()) {
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
		
		for (ConfigVillageBiome config : ConfigVillageBiome.values()) {
			BiomeGenBase[] biomes = AConfigTool.split(properties.getProperty(config.name()));
			if (biomes != null) {
				config.biomes = biomes;
			}
		}
		
		EnumVillage.load();
	}
}
