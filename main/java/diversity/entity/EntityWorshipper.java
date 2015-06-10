package diversity.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityWorshipper extends EntityMob implements IRangedAttackMob
{
    /** List of items a witch should drop on death. */
    private static final Item[] witchDrops = new Item[] {Items.iron_ingot, Items.glass_bottle, Items.gunpowder, Items.stick, Items.stick};
    /**
     * Timer used as interval for a witch's attack, decremented every tick if aggressive and when reaches zero the witch
     * will throw a potion at the target entity.
     */
    private int witchAttackTimer;
    private int witchSwordAttackTimer;
    
    private EntityAIWander aiWander = new EntityAIWander(this, 0.9D);
    private boolean canWander;
    
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false);
    private EntityAIArrowAttack aiAttackArrow = new EntityAIArrowAttack(this, 1.0D, 60, 10.0F);
    private EntityAIMoveTowardsRestriction aiMoveTowardsRestriction = new EntityAIMoveTowardsRestriction(this, 1.0D);
    private boolean goToCollide = false;
	private boolean shouldUsePotion = false;
	private boolean choosePotion = true;
    
    public EntityWorshipper(World p_i1744_1_) {
    	this(p_i1744_1_, true);
    }

    public EntityWorshipper(World p_i1744_1_, boolean canWander)
    {
        super(p_i1744_1_);
        this.canWander = canWander;
        this.tasks.addTask(1, new EntityAISwimming(this));
        if (canWander) {
        	this.tasks.addTask(3, aiWander);
        }
        this.tasks.addTask(2, aiAttackArrow);
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(21, Byte.valueOf((byte)0));
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("CanWander", this.canWander);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.canWander = p_70037_1_.getBoolean("CanWander");
        
        if (!canWander) {
        	this.tasks.removeTask(aiWander);
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.witch.idle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.witch.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.witch.death";
    }

    /**
     * Set whether this witch is aggressive at an entity.
     */
    public void setAggressive(boolean p_82197_1_)
    {
        this.getDataWatcher().updateObject(21, Byte.valueOf((byte)(p_82197_1_ ? 1 : 0)));
    }

    /**
     * Return whether this witch is aggressive at an entity.
     */
    public boolean getAggressive()
    {
        return this.getDataWatcher().getWatchableObjectByte(21) == 1;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
	/**
     * main AI tick function, replaces updateEntityActionState
     */
    @Override
    protected void updateAITick()
    {
    	super.updateAITick();
    	
    	if (this.goToCollide) {
    		this.tasks.removeTask(aiAttackArrow);
    		this.tasks.addTask(2, aiAttackOnCollide);
    		this.tasks.addTask(2, aiMoveTowardsRestriction);
    		this.goToCollide = false;
    		this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
    	}
    	
    	if (this.shouldUsePotion ) {
    		this.tasks.removeTask(aiAttackOnCollide);
    		this.tasks.removeTask(aiMoveTowardsRestriction);
    		this.tasks.addTask(2, aiAttackArrow);
    		this.shouldUsePotion = false;
    		this.choosePotion = true;
    	}
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
        	if (this.choosePotion) {
	            if (this.getAggressive())
	            {
	                if (this.witchAttackTimer-- <= 0)
	                {
	                    this.setAggressive(false);
	                    ItemStack itemstack = this.getHeldItem();
	                    this.setCurrentItemOrArmor(0, (ItemStack)null);
	
	                    if (itemstack != null && itemstack.getItem() == Items.potionitem)
	                    {
	                        List list = Items.potionitem.getEffects(itemstack);
	
	                        if (list != null)
	                        {
	                            Iterator iterator = list.iterator();
	
	                            while (iterator.hasNext())
	                            {
	                                PotionEffect potioneffect = (PotionEffect)iterator.next();
	                                this.addPotionEffect(new PotionEffect(potioneffect));
	                            }
	                        }
	                    }
	                    
	                    this.tasks.removeTask(aiAttackOnCollide);
	                    this.tasks.addTask(2, aiAttackArrow);
	
	                }
	            }
	            else
	            {
	            	short short1 = -1;
	
	                if (this.rand.nextFloat() < 0.15F && this.isInsideOfMaterial(Material.water) && !this.isPotionActive(Potion.waterBreathing))
	                {
	                    short1 = 8237;
	                }
	                else if (this.rand.nextFloat() < 0.15F && this.isBurning() && !this.isPotionActive(Potion.fireResistance))
	                {
	                    short1 = 16307;
	                }
	                else if (this.rand.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth())
	                {
	                    short1 = 16341;
	                }
	                else if (this.rand.nextFloat() < 0.25F && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0D)
	                {
	                    short1 = 16274;
	                }
	                else if (this.rand.nextFloat() < 0.25F && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0D)
	                {
	                    short1 = 16274;
	                }
	
	                if (short1 > -1)
	                {
	                    this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, short1));
	                    this.witchAttackTimer = this.getHeldItem().getMaxItemUseDuration();
	                    this.setAggressive(true);
	                }
	            }
	
	            if (this.rand.nextFloat() < 7.5E-4F)
	            {
	                this.worldObj.setEntityState(this, (byte)15);
	            }
        	} else {
        		if (this.witchSwordAttackTimer-- <= 0) {
        			this.shouldUsePotion = true;
        		}
        	}
        }

        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 15)
        {
            for (int i = 0; i < this.rand.nextInt(35) + 10; ++i)
            {
                this.worldObj.spawnParticle("witchMagic", this.posX + this.rand.nextGaussian() * 0.12999999523162842D, this.boundingBox.maxY + 0.5D + this.rand.nextGaussian() * 0.12999999523162842D, this.posZ + this.rand.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            super.handleHealthUpdate(p_70103_1_);
        }
    }

    /**
     * Reduces damage, depending on potions
     */
    protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_)
    {
        p_70672_2_ = super.applyPotionDamageCalculations(p_70672_1_, p_70672_2_);

        if (p_70672_1_.getEntity() == this)
        {
            p_70672_2_ = 0.0F;
        }

        if (p_70672_1_.isMagicDamage())
        {
            p_70672_2_ = (float)((double)p_70672_2_ * 0.15D);
        }

        return p_70672_2_;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.rand.nextInt(3) + 1;

        for (int k = 0; k < j; ++k)
        {
            int l = this.rand.nextInt(3);
            Item item = witchDrops[this.rand.nextInt(witchDrops.length)];

            if (p_70628_2_ > 0)
            {
                l += this.rand.nextInt(p_70628_2_ + 1);
            }

            for (int i1 = 0; i1 < l; ++i1)
            {
                this.dropItem(item, 1);
            }
        }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        if (!this.getAggressive())
        {
            EntityPotion entitypotion = new EntityPotion(this.worldObj, this, 32732);
            entitypotion.rotationPitch -= -20.0F;
            double d0 = p_82196_1_.posX + p_82196_1_.motionX - this.posX;
            double d1 = p_82196_1_.posY + (double)p_82196_1_.getEyeHeight() - 1.100000023841858D - this.posY;
            double d2 = p_82196_1_.posZ + p_82196_1_.motionZ - this.posZ;
            float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

            
            if (rand.nextBoolean()) {
            	entitypotion.setPotionDamage(16392);
            } else {
            	entitypotion.setPotionDamage(16394);
            }

            entitypotion.setThrowableHeading(d0, d1 + (double)(f1 * 0.2F), d2, 0.75F, 8.0F);
            this.worldObj.spawnEntityInWorld(entitypotion);
            
            this.goToCollide = true;
            this.choosePotion = false;
            this.witchSwordAttackTimer = 100;
        }
    }
    
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return canWander;
    }
}
