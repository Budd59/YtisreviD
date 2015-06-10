package diversity.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;

public class EntityAztec extends EntityGlobalVillager implements IRangedAttackMob
{
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
	
	public EntityAztec(World world)
	{
		this(world, EnumTribe.AZTEC.getRandomVillager());
	}

	public EntityAztec(World world, EnumVillager type)
	{
		super(world, type);
		switch (rand.nextInt(3)) {
			case 0 :
				this.setCurrentItemOrArmor(0, new ItemStack(EnumItem.blowgun.item));
				break;
			case 1 :
				this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_axe));
				break;
			case 2 :
				this.setCurrentItemOrArmor(0, new ItemStack(EnumItem.wooden_spear.item));
				break;
		}
	}
	
	@Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
		EntityGlobalVillager entityvillager = new EntityAztec(this.worldObj);
        entityvillager.onSpawnWithEgg((IEntityLivingData)null);
        return entityvillager;
    }

	@Override
	protected boolean canAskForHelp() {
		return getProfession() == EnumVillager.AZTEC_CHIEF.profession;
	}
	
	@Override
	public boolean canDefend() {
		return getProfession() == EnumVillager.AZTEC_HUNTER.profession;
	}
    
    /**
     * sets this entity's combat AI.
     */
    @Override
    public void updateTasks(EnumVillager type)
    {
    	if (this.aiArrowAttack != null) {
	        if (canDefend() && !isChild() && this.getHeldItem() != null && this.getHeldItem().getItem().equals(EnumItem.blowgun.item)) {
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
        EntityDart entityDart = new EntityDart(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        entityDart.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityDart);
	}
}