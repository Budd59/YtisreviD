package diversity.proxy;

import diversity.suppliers.EnumEntity;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class ServerProxy
{
	public void registerRenderers() {}

	public Integer[] searchEggColor(EnumTribe tribe) {
		return new Integer[] {0,0};
	}
	
	public Integer[] searchEggColor(EnumEntity monster) {
		return new Integer[] {0,0};
	}

	public void registerVillagerSkin(EnumVillager villager) {}
	
	public void registerEntityResource(EnumEntity entity) {}
	
	public String getI18format(EnumVillager villager) {
		return villager.villagerName;
	}

	public void registerItemRenderer(EnumItem item) {}
}
