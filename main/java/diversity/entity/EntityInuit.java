package diversity.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class EntityInuit extends AGlobalEntityVillager
{
	public EntityInuit(World world)
	{
		this(world, EnumTribe.INUIT.getRandomVillager());
	}

	public EntityInuit(World world, EnumVillager type)
	{
		super(world, type);
        this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
	}
	
	@Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
		AGlobalEntityVillager entityvillager = new EntityInuit(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return getProfession() == EnumVillager.INUIT_CHIEF.profession;
	}
	
	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.INUIT_HUNTER.profession;
	}
}
