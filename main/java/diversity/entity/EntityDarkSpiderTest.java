package diversity.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityDarkSpiderTest extends EntitySpider
{
	public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity;
    /** Cooldown time between target loss and new target aquirement. */
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;
	
	
    public float squishb;
    public float squishc;
    public float squishd;
    public float squishe;
    public float squishh;
    
    public EntityDarkSpiderTest(World p_i1743_1_)
    {
        super(p_i1743_1_);

        this.setSize(2.8F, 1.8F);
        squishb = 0.0F;
        squishc = 0.0F;
        squishh = 1.0F;
        setPhase(0);
    }
    
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(120.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10D);
    }
    
    public void onUpdate() 
	{
		super.onUpdate();
		if(getPhase() != 2)
		{
			squishe = squishb;
			squishd = squishc;
			squishc = (float)((double)squishc + 4 * 0.2D);

			if (squishc < 0.0F)
			{
				squishc = 0.0F;
			}

			if (squishc > 0.2F)
	        {
	            squishc = 0.2F;
	        }

			if (squishh < 0.2F)
			{
				squishh = 0.2F;
			}

			squishh = (float)((double)squishh * 0.2D);
			squishb += squishh * 1.5F;
		}
        
        if(getPhase() == 0)
        {   	
        	if(getHealth() < 80)
        	{
        		spawnSpiders();
        		setPhase(1);
        	}
        }
        else if(getPhase() == 1)
        {
        	if(getHealth() < 60)
        	{
        		spawnSpiders();
        		setPhase(2);
        	}
        }
        else if(getPhase() == 2)
        {
            squishb = 0.0F;
            squishc = 0.0F;
            squishd = 0.0F;
            squishe = 0.0F;
            squishh = 1.0F;
        }
        
    	if(findSpiders(8D) == null && (this.getAttackTarget() != null || this.getEntityToAttack() != null))
    	{
    		if(this.getAttackTarget() != null && this.getAttackTarget().getDistanceSqToEntity(this) < 100D 
    				|| this.getEntityToAttack() != null && this.getEntityToAttack().getDistanceSqToEntity(this) < 100D)
    		{
    			spawnSpiders();
    		}
    	}
    	
    	super.onUpdate();
    }
    
    
    public void onLivingUpdate() {
    	super.onLivingUpdate();
    	if (getPhase() < 2) {
    		this.rotationYaw = prevRotationYaw;
    	}
    }
    
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_)
    {
    	if(getPhase() == 2)
    	{
            if (p_70785_2_ > 2.0F && p_70785_2_ < 6.0F && this.rand.nextInt(10) == 0)
            {
                if (this.onGround)
                {
                    double d0 = p_70785_1_.posX - this.posX;
                    double d1 = p_70785_1_.posZ - this.posZ;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                    this.motionX = d0 / (double)f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                    this.motionZ = d1 / (double)f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                    this.motionY = 0.4000000059604645D;
                }
            }
            else
            {
                if (this.attackTime <= 0 && p_70785_2_ < 3.6F && p_70785_1_.boundingBox.maxY > this.boundingBox.minY && p_70785_1_.boundingBox.minY < this.boundingBox.maxY)
                {
                    this.attackTime = 20;
                    this.attackEntityAsMob(p_70785_1_);
                }
            }
    	}
    }
    
    public boolean isMovementCeased()
    {
    	return getPhase() != 2;
    }
    
    
    public void spawnSpiders()
    {
    	for(int i = 0; i < rand.nextInt(3) + 2 ; i++)
    	{
        if (!worldObj.isRemote)
        {
            EntitySpider entity = new EntitySpider(worldObj); //This creates the entity
            entity.setLocationAndAngles(this.posX + rand.nextDouble(), this.posY + 1, this.posZ  + rand.nextDouble(), this.rotationYaw, 0.0F);
            if(this.getAttackTarget() != null)
            {
            	entity.setAttackTarget(getAttackTarget());
            }
            if(this.getEntityToAttack() != null)
            {
            	if(getEntityToAttack() instanceof EntityPlayer)
            	{
            		entity.attackEntityFrom(DamageSource.causeMobDamage((EntityPlayer)getEntityToAttack()), 0);
            	}
            	else if(getEntityToAttack() instanceof EntityLivingBase)
            	{
            		entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)getEntityToAttack()), 0);
            	}
            }
            worldObj.spawnEntityInWorld(entity);
        }
        
        
        for (int j = 0; j < 12; j++)
        {
            worldObj.spawnParticle("cloud", this.posX + (rand.nextFloat() - rand.nextFloat()), this.posY + 1D + (rand.nextFloat() - rand.nextFloat()) , this.posZ + (rand.nextFloat() - rand.nextFloat()), 0.0D, 0.0D, 0.0D);
        }
    	}
    }
    
    public EntitySpider findSpiders(double d)
    {
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(d, 4D, d));

        for (int i = 0; i < list.size(); i++)
        {
        Entity entity = (Entity)list.get(i);
        if (entity != null)
        {
            if (!(entity instanceof EntitySpider))
            {
                continue;
            }
            else
            {
            EntitySpider entityspider = (EntitySpider)entity;

            return entityspider;
            }
        }
        }
        return null;
    }
    

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }
    
    public int getPhase()
    {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setPhase(int par1)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)par1));
    }
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
	@Override
    protected boolean canDespawn()
    {
        return false;
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Phase", getPhase());
        
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        this.setPhase(nbttagcompound.getInteger("Phase"));
    }
    
    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);

        if (p_110161_1_1 == null)
        {
            p_110161_1_1 = new EntitySpider.GroupData();

            if (this.worldObj.difficultySetting == EnumDifficulty.HARD && this.worldObj.rand.nextFloat() < 0.1F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ))
            {
                ((EntitySpider.GroupData)p_110161_1_1).func_111104_a(this.worldObj.rand);
            }
        }

        if (p_110161_1_1 instanceof EntitySpider.GroupData)
        {
            int i = ((EntitySpider.GroupData)p_110161_1_1).field_111105_a;

            if (i > 0 && Potion.potionTypes[i] != null)
            {
                this.addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
            }
        }
        return (IEntityLivingData)p_110161_1_1;
    }

    protected void updateEntityActionState()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }

        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double d0 = this.waypointX - this.posX;
        double d1 = this.waypointY - this.posY;
        double d2 = this.waypointZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d3 = (double)MathHelper.sqrt_double(d3);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
            {
                this.motionX += d0 / d3 * 0.1D;
                this.motionY += d1 / d3 * 0.1D;
                this.motionZ += d2 / d3 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.isDead)
        {
            this.targetedEntity = null;
        }

        if (this.targetedEntity == null || this.aggroCooldown-- <= 0)
        {
            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

            if (this.targetedEntity != null)
            {
                this.aggroCooldown = 20;
            }
        }

        double d4 = 64.0D;
        
		if (getPhase()<2) {
	        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
	        {
	            double d5 = this.targetedEntity.posX - this.posX;
	            double d6 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
	            double d7 = this.targetedEntity.posZ - this.posZ;
	            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;
	
	            if (this.canEntityBeSeen(this.targetedEntity))
	            {
	                if (this.attackCounter == 10)
	                {
	                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
	                }
	
	                ++this.attackCounter;
	
	                if (this.attackCounter == 20)
	                {
	                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
	                    EntitySnowball entitylargefireball = new EntitySnowball(this.worldObj, this);
	                    double d8 = 4.0D;
	                    Vec3 vec3 = this.getLook(1.0F);
	                    entitylargefireball.posX = this.posX + vec3.xCoord * d8;
	                    entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
	                    entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
	                    this.worldObj.spawnEntityInWorld(entitylargefireball);
	                    this.attackCounter = -40;
	                }
	            }
	            else if (this.attackCounter > 0)
	            {
	                --this.attackCounter;
	            }
	        }
	        else
	        {
	            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
	
	            if (this.attackCounter > 0)
	            {
	                --this.attackCounter;
	            }
	        }
		}

        if (!this.worldObj.isRemote)
        {
            byte b1 = this.dataWatcher.getWatchableObjectByte(16);
            byte b0 = (byte)(this.attackCounter > 10 ? 1 : 0);

            if (b1 != b0)
            {
                this.dataWatcher.updateObject(16, Byte.valueOf(b0));
            }
        }
    }
    
    /**
     * True if the ghast has an unobstructed line of travel to the waypoint.
     */
    private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_)
    {
        double d4 = (this.waypointX - this.posX) / p_70790_7_;
        double d5 = (this.waypointY - this.posY) / p_70790_7_;
        double d6 = (this.waypointZ - this.posZ) / p_70790_7_;
        AxisAlignedBB axisalignedbb = this.boundingBox.copy();

        for (int i = 1; (double)i < p_70790_7_; ++i)
        {
            axisalignedbb.offset(d4, d5, d6);

            if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
            {
                return false;
            }
        }

        return true;
    }
}
