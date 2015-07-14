package diversity.entity.ai.villager;

import java.util.Iterator;
import java.util.List;

import diversity.entity.AGlobalEntityVillager;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;

public class EntityCustomAIFollowGolem extends EntityAIBase
{
    private AGlobalEntityVillager theVillager;
    private EntityIronGolem theGolem;
    private int takeGolemRoseTick;
    private boolean tookGolemRose;
    private static final String __OBFID = "CL_00001615";

    public EntityCustomAIFollowGolem(AGlobalEntityVillager p_i1656_1_)
    {
        this.theVillager = p_i1656_1_;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.theVillager.getGrowingAge() >= 0)
        {
            return false;
        }
        else if (!this.theVillager.worldObj.isDaytime())
        {
            return false;
        }
        else
        {
            List list = this.theVillager.worldObj.getEntitiesWithinAABB(EntityIronGolem.class, this.theVillager.boundingBox.expand(6.0D, 2.0D, 6.0D));

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
                Iterator iterator = list.iterator();

                while (iterator.hasNext())
                {
                    EntityIronGolem entityirongolem = (EntityIronGolem)iterator.next();

                    if (entityirongolem.getHoldRoseTick() > 0)
                    {
                        this.theGolem = entityirongolem;
                        break;
                    }
                }

                return this.theGolem != null;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.theGolem.getHoldRoseTick() > 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.takeGolemRoseTick = this.theVillager.getRNG().nextInt(320);
        this.tookGolemRose = false;
        this.theGolem.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.theGolem = null;
        this.theVillager.getNavigator().clearPathEntity();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.theVillager.getLookHelper().setLookPositionWithEntity(this.theGolem, 30.0F, 30.0F);

        if (this.theGolem.getHoldRoseTick() == this.takeGolemRoseTick)
        {
            this.theVillager.getNavigator().tryMoveToEntityLiving(this.theGolem, 0.5D);
            this.tookGolemRose = true;
        }

        if (this.tookGolemRose && this.theVillager.getDistanceSqToEntity(this.theGolem) < 4.0D)
        {
            this.theGolem.setHoldingRose(false);
            this.theVillager.getNavigator().clearPathEntity();
        }
    }
}