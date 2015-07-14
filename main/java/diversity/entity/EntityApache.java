package diversity.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class EntityApache extends AGlobalEntityVillager implements IRangedAttackMob
{
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
	
	public EntityApache(World world)
	{
		this(world, EnumTribe.APACHE.getRandomVillager());
	}

	public EntityApache(World world, EnumVillager type)
	{
		super(world, type);
		if (rand.nextBoolean()) {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
		} else {
			this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_axe));
		}
	}
	
	@Override
    public AGlobalEntityVillager createChild(EntityAgeable p_90011_1_)
    {
        AGlobalEntityVillager entityvillager = new EntityApache(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }
	
	@Override
	protected boolean canAskForHelp() {
		return getProfession() == EnumVillager.APACHE_CHIEF.profession;
	}

	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.APACHE_WARRIOR.profession;
	}
	
    /**
     * sets this entity's combat AI.
     */
    @Override
    public void updateTasks(EnumVillager type)
    {
    	if (this.aiArrowAttack != null) {
	        if (canDefend() && !isChild() && this.getHeldItem() != null && this.getHeldItem().getItem().equals(Items.bow)) {
	            this.tasks.addTask(1, this.aiArrowAttack);
	            this.tasks.removeTask(attackOnCollide);
				this.tasks.removeTask(aiMoveTowardsRestriction);
	        } else {
	        	this.tasks.removeTask(this.aiArrowAttack);
	        }
    	}
        super.updateTasks(type);
    }
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
	{
		EntityArrow entityArrow = new EntityArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        entityArrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityArrow);
	}
}