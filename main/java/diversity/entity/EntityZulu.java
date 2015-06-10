package diversity.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class EntityZulu extends EntityGlobalVillager
{
	public EntityZulu(World world)
	{
		this(world, EnumTribe.ZULU.getRandomVillager());
	}

	public EntityZulu(World world, EnumVillager type)
	{
		super(world, type);
		this.setCurrentItemOrArmor(0, new ItemStack(EnumItem.stone_spear.item));
	}
	
	@Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
		EntityGlobalVillager entityvillager = new EntityZulu(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return getProfession() == EnumVillager.ZULU_CHIEF.profession;
	}
	
	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.ZULU_WARRIOR.profession;
	}
}
