package diversity.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import diversity.entity.EntityGlobalVillager;

public class EntityAIChiefHurtByTarget extends EntityAITarget
{
	EntityGlobalVillager theDefendingVillager;
    EntityLivingBase theChiefAttacker;
    private int field_142051_e;

    public EntityAIChiefHurtByTarget(EntityGlobalVillager p_i1667_1_)
    {
        super(p_i1667_1_, false);
        this.theDefendingVillager = p_i1667_1_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.theDefendingVillager.isChild())
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.theDefendingVillager.getChief();

            if (entitylivingbase == null)
            {
                return false;
            }
            else
            {
                this.theChiefAttacker = entitylivingbase.getAITarget();
                int i = entitylivingbase.func_142015_aE();
                return i != this.field_142051_e && this.isSuitableTarget(this.theChiefAttacker, false) && this.theDefendingVillager.func_142018_a(this.theChiefAttacker, entitylivingbase);
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.theChiefAttacker);
        EntityLivingBase entitylivingbase = this.theDefendingVillager.getChief();

        if (entitylivingbase != null)
        {
            this.field_142051_e = entitylivingbase.func_142015_aE();
        }

        super.startExecuting();
    }

}
