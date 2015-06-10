package diversity.suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.EntityRegistry;
import diversity.Diversity;
import diversity.entity.EntityApache;
import diversity.entity.EntityAztec;
import diversity.entity.EntityEgyptian;
import diversity.entity.EntityGlobalVillager;
import diversity.entity.EntityInuit;
import diversity.entity.EntityLakeside;
import diversity.entity.EntitySettled;
import diversity.entity.EntityTibetan;
import diversity.entity.EntityZulu;
import diversity.utils.PathTool;
import diversity.utils.ResourceTools;

public enum EnumTribe {	
	APACHE (10, "apache", EntityApache.class),
	AZTEC (20, "aztec", EntityAztec.class),
	EGYPTIAN (60, "egyptian", EntityEgyptian.class),
	INUIT (30, "inuit", EntityInuit.class),
	LAKESIDE (70, "lakeside", EntityLakeside.class),
	SETTLED (80, "settled", EntitySettled.class),
	TIBETAN (50, "tibetan", EntityTibetan.class),
	ZULU (40, "zulu", EntityZulu.class);

	public final List<EnumVillager> villagers = new ArrayList<EnumVillager>();
	
	public final int id;
	public final String path;
	public final Class entityClass;
	
	private EnumTribe(int id, String path, Class entityClass) {
		this.id = id;
		this.path = path + "/";
		this.entityClass = entityClass;
		
		ResourceTools.register(entityClass, PathTool.entityVillagerTexturePath + this.path + "child" + PathTool.ext);
	}
	
	public EnumVillager getRandomVillager()
	{
		return villagers.get(new Random().nextInt(villagers.size()));
	}

	public static EnumTribe getEnumTribe(EntityGlobalVillager villager) {
		for (EnumTribe tribe : EnumTribe.values()) {
			if (tribe.entityClass.isInstance(villager)) {
				return tribe;
			}
		}
		return null;
	}
	
	public EnumVillager findChief() {
		for (EnumVillager villager : villagers) {
			if (villager.isChief) {
				return villager;
			}
		}
		return null;
	}
	
	public static void register() {
	  	for (EnumTribe tribe : EnumTribe.values())
    	{
			int id = EntityRegistry.findGlobalUniqueEntityId();
			Integer[] eggcolor = Diversity.proxy.searchEggColor(tribe);
			EntityRegistry.registerGlobalEntityID(tribe.entityClass, Diversity.MODID + "." + tribe.name().toLowerCase(), id, eggcolor[0], eggcolor[1]);
			//EntityRegistry.registerModEntity(tribe.entityClass, Diversity.MODID + "." + tribe.name().toLowerCase(), id, Diversity.instance, 64, 1, true);
    	}
	}
}