package diversity.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class EntityTibetan extends EntityGlobalVillager
{
	public EntityTibetan(World world)
	{
		this(world, EnumTribe.TIBETAN.getRandomVillager());
	}

	public EntityTibetan(World world, EnumVillager type)
	{
		super(world, type);
	}
	
	@Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
		EntityGlobalVillager entityvillager = new EntityTibetan(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return false;
	}
	
	@Override
	public boolean canDefend() {
		return false;
	}
}