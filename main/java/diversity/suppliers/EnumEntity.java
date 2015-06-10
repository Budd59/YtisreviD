package diversity.suppliers;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import cpw.mods.fml.common.registry.EntityRegistry;
import diversity.Diversity;
import diversity.configurations.ConfigCaveBiome;
import diversity.entity.EntityDarkSpider;
import diversity.entity.EntityMummy;
import diversity.entity.EntityTzitzimime;
import diversity.entity.EntityWarriorSkeleton;
import diversity.entity.EntityWorshipper;
import diversity.entity.EntityYeti;
import diversity.utils.PathTool;

public enum EnumEntity
{
	MUMMY (EntityMummy.class),
	TZITZIMIME (EntityTzitzimime.class),
	WARRIOR_SKELETON (EntityWarriorSkeleton.class, 75, 2, 5, EnumCreatureType.monster, BiomeGenBase.swampland),
	WORSHIPPER (EntityWorshipper.class, 25, 1, 2, EnumCreatureType.monster, ConfigCaveBiome.MUSHROOM_CAVE),
	DARKSPIDER (EntityDarkSpider.class),
	YETI (EntityYeti.class);
	
	public final Class entityClass;
	public final String resourcePath;
	public final int weight;
	public final int min;
	public final int max;
	public final EnumCreatureType spawnList;
	public final BiomeGenBase[] biomes;
	public final ConfigCaveBiome config;
		
	private EnumEntity (Class entityClass)
	{
		this(entityClass, 0, 0, 0, null, null, (BiomeGenBase[])null);
	}
	
	private EnumEntity (Class entityClass, int weight, int min, int max, EnumCreatureType spawnList, BiomeGenBase...biomes)
	{
		this(entityClass, weight, min, max, spawnList, null, biomes);
	}
	
	private EnumEntity (Class entityClass, int weight, int min, int max, EnumCreatureType spawnList, ConfigCaveBiome config)
	{
		this(entityClass, weight, min, max, spawnList, config, (BiomeGenBase[])null);
	}
	
	private EnumEntity (Class entityClass, int weight, int min, int max, EnumCreatureType spawnList, ConfigCaveBiome config, BiomeGenBase...biomes)
	{
		this.entityClass = entityClass;
		this.resourcePath = PathTool.entityMonsterTexturePath + name().toLowerCase() + PathTool.ext;
		this.weight = weight;
		this.min = min;
		this.max = max;
		this.spawnList = spawnList;
		this.config = config;
		this.biomes = biomes;
	}
	
	public static void register() {
	  	for (EnumEntity entity : EnumEntity.values())
    	{
			Diversity.proxy.registerEntityResource(entity);
			//int id = EntityRegistry.findGlobalUniqueEntityId();
			int id = getUniqueEntityId();
			Integer[] eggcolor = Diversity.proxy.searchEggColor(entity);
			EntityRegistry.registerModEntity(entity.entityClass, Diversity.MODID + "." + entity.name().toLowerCase(), id, Diversity.instance, 64, 1, true);
			//EntityRegistry.registerGlobalEntityID(entity.entityClass, Diversity.MODID + "." + entity.name().toLowerCase(), id, eggcolor[0], eggcolor[1]);
			EntityList.IDtoClassMapping.put(id, entity.entityClass);
			EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, eggcolor[0], eggcolor[1]));
    	}
	}
	
	static int startEntityId = 300;

	public static int getUniqueEntityId()
	{
		do {
			startEntityId++;
		} while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}
	
	public static void load() {
	  	for (EnumEntity entity : EnumEntity.values())
    	{
			if (entity.biomes != null && entity.biomes.length > 0) {
				EntityRegistry.addSpawn(entity.entityClass, entity.weight, entity.min, entity.max, entity.spawnList, entity.biomes);
			} else if (entity.config != null) {
				EntityRegistry.addSpawn(entity.entityClass, entity.weight, entity.min, entity.max, entity.spawnList, entity.config.biomes);
			}
    	}	
	}
}