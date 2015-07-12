package diversity.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import diversity.entity.AGlobalEntityVillager;

public abstract class EntityAIGateInteract extends EntityAIDoorInteract
{
	
	protected BlockFenceGate field_151504_e;
    /**
     * If is true then the Entity has stopped Door Interaction and compoleted the task.
     */
    boolean hasStoppedDoorInteraction;
    float entityPositionX;
    float entityPositionZ;

    public EntityAIGateInteract(EntityLiving p_i1621_1_)
    {
    	super(p_i1621_1_);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        if (!this.theEntity.isCollidedHorizontally)
        {
            return false;
        }
        else
        {
            PathNavigate pathnavigate = this.theEntity.getNavigator();
            PathEntity pathentity = pathnavigate.getPath();

            if (pathentity != null && !pathentity.isFinished() && pathnavigate.getCanBreakDoors())
            {
                for (int i = 0; i < Math.min(pathentity.getCurrentPathIndex() + 2, pathentity.getCurrentPathLength()); ++i)
                {
                    PathPoint pathpoint = pathentity.getPathPointFromIndex(i);
                    this.entityPosX = pathpoint.xCoord;
                    this.entityPosY = pathpoint.yCoord + 1;
                    this.entityPosZ = pathpoint.zCoord;

                    if (this.theEntity.getDistanceSq((double)this.entityPosX, this.theEntity.posY, (double)this.entityPosZ) <= 2.25D)
                    {
                        this.field_151504_e = this.func_151503_a(this.entityPosX, this.entityPosY, this.entityPosZ);

                        if (this.field_151504_e != null)
                        {
                            return true;
                        }
                    }
                }

                this.entityPosX = MathHelper.floor_double(this.theEntity.posX);
                this.entityPosY = MathHelper.floor_double(this.theEntity.posY + 1.0D);
                this.entityPosZ = MathHelper.floor_double(this.theEntity.posZ);
                this.field_151504_e = this.func_151503_a(this.entityPosX, this.entityPosY, this.entityPosZ);
                return this.field_151504_e != null;
            }
            else
            {
                return false;
            }
        }
    }
    
    private BlockFenceGate func_151503_a(int p_151503_1_, int p_151503_2_, int p_151503_3_)
    {
        Block block = this.theEntity.worldObj.getBlock(p_151503_1_, p_151503_2_, p_151503_3_);
        return block != Blocks.fence_gate ? null : (BlockFenceGate)block;
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    protected boolean onGateActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, AGlobalEntityVillager p_149727_5_, BlockFenceGate gate)
    {
        int i1 = p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_);

        if (gate.isFenceGateOpen(i1))
        {
            p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, i1 & -5, 2);
        }
        else
        {
            int j1 = (MathHelper.floor_double((double)(p_149727_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
            int k1 = gate.getDirection(i1);

            if (k1 == (j1 + 2) % 4)
            {
                i1 = j1;
            }

            p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, i1 | 4, 2);
        }
        return true;
    }
}
