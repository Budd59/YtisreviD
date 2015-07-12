package diversity.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class EntityEgyptian extends AGlobalEntityVillager
{
	public EntityEgyptian(World world)
	{
		this(world, EnumTribe.EGYPTIAN.getRandomVillager());
	}

	public EntityEgyptian(World world, EnumVillager type)
	{
		super(world, type);
		if (rand.nextBoolean()) {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
		} else {
			this.setCurrentItemOrArmor(0, new ItemStack(EnumItem.iron_spear.item));
		}
	}
	
	@Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
		AGlobalEntityVillager entityvillager = new EntityEgyptian(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return getProfession() == EnumVillager.EGYPTIAN_PHARAOH.profession;
	}
	
	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.EGYPTIAN_GUARD.profession;
	}
}