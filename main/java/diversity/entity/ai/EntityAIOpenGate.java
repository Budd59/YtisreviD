package diversity.entity.ai;

import net.minecraft.entity.EntityLiving;
import diversity.entity.EntityGlobalVillager;

public class EntityAIOpenGate  extends EntityAIGateInteract
{
    boolean field_75361_i;
    int field_75360_j;

    public EntityAIOpenGate(EntityLiving p_i1644_1_, boolean p_i1644_2_)
    {
        super(p_i1644_1_);
        this.theEntity = p_i1644_1_;
        this.field_75361_i = p_i1644_2_;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.field_75361_i && this.field_75360_j > 0 && super.continueExecuting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.field_75360_j = 20;
        onGateActivated(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, (EntityGlobalVillager)this.theEntity, this.field_151504_e);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        if (this.field_75361_i)
        {
            onGateActivated(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, (EntityGlobalVillager)this.theEntity, this.field_151504_e);
        }
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        --this.field_75360_j;
        super.updateTask();
    }

    
    
}
