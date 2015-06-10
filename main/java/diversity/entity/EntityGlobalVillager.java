package diversity.entity;

import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.Diversity;
import diversity.configurations.ConfigVillageRate;
import diversity.configurations.ConfigVillager;
import diversity.entity.ai.EntityAIChiefHurtByTarget;
import diversity.entity.ai.EntityAIOpenGate;
import diversity.item.ItemSpear;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;
import diversity.utils.VillageData;

public abstract class EntityGlobalVillager extends EntityVillager
{	
    private Village villageObj;
    public final EnumTribe tribe;
	private int randomTickDivider;
	
	private EntityAIBase askForHelp = new EntityAIHurtByTarget(this, true);
	protected EntityAIBase attackOnCollide = new EntityAIAttackOnCollide(this, 1.0D, false);
	protected EntityAIMoveTowardsRestriction aiMoveTowardsRestriction = new EntityAIMoveTowardsRestriction(this, 1.0D);
	private EntityAIBase chiefHurt = new EntityAIChiefHurtByTarget(this);
	private EntityAIBase entityHurt = new EntityAIHurtByTarget(this, true);
	private EntityAITradePlayer aiTradePlayer = new EntityAITradePlayer(this);
	private EntityAILookAtTradePlayer aiLookAtTradePlayer = new EntityAILookAtTradePlayer(this);
	
	public EntityGlobalVillager(World world) {
		this(world, EnumVillager.SETTLED_VILLAGER);
	}
	
	public EntityGlobalVillager(World world, EnumVillager type)
	{
		super(world, type.profession);
		this.tasks.taskEntries.clear();
		
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(2, aiTradePlayer);
        this.tasks.addTask(2, aiLookAtTradePlayer);
        this.tasks.addTask(3, new EntityAIMoveIndoors(this));
        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
        //this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(7, new EntityAIVillagerMate(this));
        this.tasks.addTask(8, new EntityAIFollowGolem(this));
        this.tasks.addTask(9, new EntityAIPlay(this, 0.32D));
        this.tasks.addTask(10, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
        this.tasks.addTask(10, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		
        this.tasks.addTask(5, new EntityAIOpenGate(this, true));
        this.tribe = type.tribe;
		updateTasks(type);
	}
	
	public void updateTasks(EnumVillager type) {
		if (canAskForHelp() && !isChild()) {
			this.targetTasks.addTask(1, askForHelp);
		} else {
			this.targetTasks.removeTask(askForHelp);
		}
		if (canDefend() && !isChild()) {
	        this.tasks.removeTask(aiTradePlayer);
	        this.tasks.removeTask(aiLookAtTradePlayer);
	        this.tasks.addTask(1, attackOnCollide);
	        this.tasks.addTask(1, aiMoveTowardsRestriction);
			this.targetTasks.addTask(2, chiefHurt);
	        this.targetTasks.addTask(3, entityHurt);
		} else {
			this.tasks.removeTask(attackOnCollide);
			this.tasks.removeTask(aiMoveTowardsRestriction);
			this.targetTasks.removeTask(chiefHurt);
			this.targetTasks.removeTask(entityHurt);
	        this.tasks.addTask(2, aiTradePlayer);
	        this.tasks.addTask(2, aiLookAtTradePlayer);
		}

		setCustomNameTag(Diversity.proxy.getI18format(type));
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        IAttributeInstance attribute = this.getEntityAttribute(SharedMonsterAttributes.attackDamage);
        if (attribute != null) {
        	attribute.setBaseValue(2.0D);
        } else {
        	this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        }
        
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.36000001192092896D);
    }

    protected abstract boolean canAskForHelp();
    
    public abstract boolean canDefend();
    
    public boolean isChief() {
    	return EnumVillager.findEnum(getProfession()).isChief;
    }
    
    public void setChief() {
    	EnumVillager villager = EnumVillager.findEnum(getProfession()).tribe.findChief();
    	if (villager != null && villager.isChief) {
    		this.setProfession(villager.profession);
        	this.updateTasks(villager);
    	}
    }
    
    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);
        this.setRandomProfession();
        return p_110161_1_;
    }
	
	private void setRandomProfession() {
		do {
			this.setProfession(getEnumTribe().getRandomVillager().profession);
		} while (this.isChief());
		this.updateTasks(EnumVillager.findEnum(this.getProfession()));
	}
    
    private EnumTribe getEnumTribe() {
    	return EnumTribe.getEnumTribe(this);
    }

	/**
     * main AI tick function, replaces updateEntityActionState
     */
    @Override
    protected void updateAITick()
    {
    	super.updateAITick();
    	
        if (--this.randomTickDivider <= 0)
        {
            this.randomTickDivider = 70 + this.rand.nextInt(50);
	        this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);
	    	if (this.villageObj != null) {
	    		if (this.villageObj.isAnnihilated()) {
	    			VillageData.onAnihilated(villageObj);
	    			return;
	    		}
	    		EntityGlobalVillager chief = (EntityGlobalVillager) getChief();
	    		if (this.isChief())
	    		{
	    			if (chief == null)
	    			{
	    				VillageData.addChief(villageObj, this);
	    			} else if (!chief.equals(this))
	    			{
	    				this.setRandomProfession();
	    			}
	    		} else {
	    			Random random = new Random();
		    		if (chief == null && random.nextInt(ConfigVillager.chanceVillagerBecomesChief.getIntegerConfig())==0)
		    		{
		    			VillageData.addChief(villageObj, this);
		    			if (this.isChief())
		    			{
		    				getRecipes(null).clear();
		    				VillagerRegistry.manageVillagerTrades(getRecipes(null), this, this.getProfession(), this.rand);
		    		    	worldObj.spawnParticle("reddust", posX, posY+1.666, posZ, 1.0D /*red*/,  1.0D/*green*/, 1.0D /*blue*/);
		    		    	worldObj.spawnParticle("reddust", posX-0.5, posY+1.666, posZ, 1.0D /*red*/,  1.0D/*green*/, 1.0D /*blue*/);
		    		    	worldObj.spawnParticle("reddust", posX+0.5, posY+1.666, posZ, 1.0D /*red*/,  1.0D/*green*/, 1.0D /*blue*/);
		    		    	worldObj.spawnParticle("reddust", posX, posY+1.666, posZ-0.5, 1.0D /*red*/,  1.0D/*green*/, 1.0D /*blue*/);
		    		    	worldObj.spawnParticle("reddust", posX, posY+1.666, posZ+0.5, 1.0D /*red*/,  1.0D/*green*/, 1.0D /*blue*/);
		    			}
		    		}
	    		} 


	    	}
        }
    }
    
    @Override
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_)
    {
        if (this.attackTime <= 0 && p_70785_2_ < 2.0F && p_70785_1_.boundingBox.maxY > this.boundingBox.minY && p_70785_1_.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(p_70785_1_);
        }
    }
    
    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (p_70652_1_ instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)p_70652_1_);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)p_70652_1_);
        }

        boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), f);

//        if (flag)
//        {
//            if (i > 0)
//            {
//                p_70652_1_.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
//                this.motionX *= 0.6D;
//                this.motionZ *= 0.6D;
//            }
//
//            int j = EnchantmentHelper.getFireAspectModifier(this);
//
//            if (j > 0)
//            {
//                p_70652_1_.setFire(j * 4);
//            }
//
//            if (p_70652_1_ instanceof EntityLivingBase)
//            {
//                EnchantmentHelper.func_151384_a((EntityLivingBase)p_70652_1_, this);
//            }
//
//            EnchantmentHelper.func_151385_b(this, p_70652_1_);
//        }

        return flag;
    }
    
    @Override
    protected void onDeathUpdate()
    {
    	if (this.isChief()) {
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);
    		VillageData.onDeadChief(villageObj);
    	}
    	super.onDeathUpdate();
    }
    
    
    protected Item getDropItem()
    {
        return Items.gold_nugget;
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
    	if (!canDefend() && !isChild()) {
    		this.dropItem(Items.gold_nugget, 2 + this.rand.nextInt(4));
    	}
    }
    
    /**
     * Drop the equipment for this entity.
     */
    @Override
    protected void dropEquipment(boolean p_82160_1_, int p_82160_2_)
    {
    	if (canDefend() && !isChild()) {
    		super.dropEquipment(p_82160_1_, p_82160_2_);
    	}
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_)
    {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast))
        {
            if (p_142018_1_ instanceof EntityWolf)
            {
                EntityWolf entitywolf = (EntityWolf)p_142018_1_;

                if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_)
                {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityGlobalVillager ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse)p_142018_1_).isTame();
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer p_70085_1_)
    {
    	if (!canDefend()) {
    		return super.interact(p_70085_1_);
    	} else {
    		return false;
    	}
    }

	public EntityLivingBase getChief()
	{
		if (villageObj != null) {
			return VillageData.getChief(villageObj);
		}
		return null;
	}
	
	@Override
    public boolean hasCustomNameTag()
    {
        return false;
    }
	
	
    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
	@Override
    public ItemStack getHeldItem()
    {
        return canDefend() && !isChild() ? super.getHeldItem() : null;
    }
	
    /**
     * Gets the Icon Index of the item currently held
     */
    @SideOnly(Side.CLIENT)
    public IIcon getItemIcon(ItemStack p_70620_1_, int p_70620_2_)
    {
    	if (p_70620_1_.getItem() instanceof ItemSpear) {
    		return ((ItemSpear)p_70620_1_.getItem()).getIcon(p_70620_1_, 0);
    	}
        return p_70620_1_.getItem().requiresMultipleRenderPasses() ? p_70620_1_.getItem().getIconFromDamageForRenderPass(p_70620_1_.getItemDamage(), p_70620_2_) : p_70620_1_.getIconIndex();
    }
}
