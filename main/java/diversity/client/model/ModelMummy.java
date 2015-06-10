package diversity.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.entity.EntityMummy;

@SideOnly(Side.CLIENT)
public class ModelMummy extends ModelVillager
{
	public ModelMummy(float p_i1163_1_)
	{
		this(p_i1163_1_, 0.0F, 64, 64);
	}
	
	public ModelMummy(float p_i1164_1_, float p_i1164_2_, int p_i1164_3_, int p_i1164_4_)
	{
		super(p_i1164_1_, p_i1164_2_, p_i1164_3_, p_i1164_4_);
		 this.villagerArms = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
	     this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
	     this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
	}
	
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
	@Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
		
        this.villagerArms.rotationPointY = 3.0F;
        this.villagerArms.rotationPointZ = -1.0F;
		if (p_78087_7_ instanceof EntityMummy &&  ((EntityMummy)p_78087_7_).getEntityToAttack() != null )
			this.villagerArms.rotateAngleX = -1.50F;
		else
			this.villagerArms.rotateAngleX = 0.0F;
        //this.villagerArms.rotateAngleX = -0.75F;
		
    }
}
