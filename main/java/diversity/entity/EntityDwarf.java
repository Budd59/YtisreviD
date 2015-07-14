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

public class EntityDwarf extends AGlobalEntityVillager
{
	public EntityDwarf(World world)
	{
		this(world, EnumTribe.DWARF.getRandomVillager());
	}

	public EntityDwarf(World world, EnumVillager type)
	{
		super(world, type);
		this.setSize(0.6F, 1.2F);
		if (rand.nextBoolean()) {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_axe));
		} else {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
		}
	}
	
    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
	@Override
    public ItemStack getHeldItem()
    {
        return (canDefend() && !isChild()) ? super.getHeldItem() : (isMiner() && !isChild()) ? new ItemStack(Items.iron_pickaxe) : null;
    }
	
	@Override
    public AGlobalEntityVillager  createChild(EntityAgeable p_90011_1_)
    {
		AGlobalEntityVillager entityvillager = new EntityDwarf(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return getProfession() == EnumVillager.DWARF_KING.profession;
	}
	
	public boolean isMiner() {
		return getProfession() == EnumVillager.DWARF_MINER.profession;
	}
	
	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.DWARF_WARRIOR.profession;
	}
}