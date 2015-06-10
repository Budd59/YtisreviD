package diversity.utils;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.village.Village;
import diversity.entity.EntityGlobalVillager;

public class VillageData
{
	private static final Map<Village, EntityGlobalVillager> map = new HashMap<Village, EntityGlobalVillager>();

	public static void addChief(Village village, EntityGlobalVillager chief)
	{
		if (!map.containsKey(village) || map.get(village)==null)
		{
			chief.setChief();
			map.put(village, chief);
		}
	}
	
	public static EntityGlobalVillager getChief(Village village)
	{
		return map.get(village);
	}
	
	public static void onDeadChief(Village village)
	{
		map.remove(village);
	}

	public static void onAnihilated(Village village) {
		map.remove(village);
	}
}
