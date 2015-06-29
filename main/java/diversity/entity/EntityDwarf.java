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

public class EntityDwarf extends EntityGlobalVillager
{
	public EntityDwarf(World world)
	{
		this(world, EnumTribe.DWARF.getRandomVillager());
	}

	public EntityDwarf(World world, EnumVillager type)
	{
		super(world, type);
		
		if (rand.nextBoolean()) {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_axe));
		} else {
			this.setCurrentItemOrArmor(0, new ItemStack(EnumItem.iron_spear.item));
		}
	}
	
	@Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
		EntityGlobalVillager entityvillager = new EntityDwarf(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return getProfession() == EnumVillager.DWARF_KING.profession;
	}
	
	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.DWARF_WARRIOR.profession;
	}
}