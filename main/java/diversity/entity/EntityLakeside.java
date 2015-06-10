package diversity.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class EntityLakeside extends EntityGlobalVillager
{
	public EntityLakeside(World world)
	{
		this(world, EnumTribe.LAKESIDE.getRandomVillager());
	}

	public EntityLakeside(World world, EnumVillager type)
	{
		super(world, type);
		if (rand.nextBoolean()) {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_axe));
		} else {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
		}
	}
	
	@Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
		EntityGlobalVillager entityvillager = new EntityLakeside(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return isChief();
	}
	
	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.LAKESIDE_GUARD.profession;
	}
}