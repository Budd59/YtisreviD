package diversity.entity.ai.villager;

import diversity.entity.AGlobalEntityVillager;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class EntityCustomAILookAtTradePlayer extends EntityAIWatchClosest
{
    private final AGlobalEntityVillager theMerchant;
    private static final String __OBFID = "CL_00001593";

    public EntityCustomAILookAtTradePlayer(AGlobalEntityVillager p_i1633_1_)
    {
        super(p_i1633_1_, EntityPlayer.class, 8.0F);
        this.theMerchant = p_i1633_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.theMerchant.isTrading())
        {
            this.closestEntity = this.theMerchant.getCustomer();
            return true;
        }
        else
        {
            return false;
        }
    }
}