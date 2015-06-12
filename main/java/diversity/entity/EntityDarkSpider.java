package diversity.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityDarkSpider extends EntitySpider
{
	
    public float squishb;
    public float squishc;
    public float squishd;
    public float squishe;
    public float squishh;
    
    public EntityDarkSpider(World p_i1743_1_)
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
			squishc = (float)((double)squishc + 4 * 0.8D);

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

			squishh = (float)((double)squishh * 0.9D);
			squishb += squishh * 2.0F;
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
    	return getPhase() == 2;
    }
    
    
    public void spawnSpiders()
    {
    	for(int i = 0; i < rand.nextInt(3) + 2 ; i++)
    	{
        if (!worldObj.isRemote)
        {
            EntitySpider entity = new EntitySpider(worldObj); //This creates the entity
            entity.setLocationAndAngles(this.posX + rand.nextDouble(), this.posY, this.posZ  + rand.nextDouble(), this.rotationYaw, 0.0F);
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
    
    
}
