package diversity.client.model;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.EntityLivingBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWarriorSkeleton extends ModelSkeleton
{
	public ModelWarriorSkeleton(float p_i1163_1_)
	{
		super(p_i1163_1_);
	}
	
    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
    {}
}
