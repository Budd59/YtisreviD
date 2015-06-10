package diversity.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTzitzimime extends ModelVillager
{
    public ModelRenderer villagerEar;
    public ModelRenderer[] villagerPens = new ModelRenderer[5];
	
	public ModelTzitzimime(float p_i1163_1_) {
		this(p_i1163_1_, 0.0F, 64, 64);
	}

	public ModelTzitzimime(float p_i1164_1_, float p_i1164_2_, int p_i1164_3_, int p_i1164_4_)
	{
		super(p_i1164_1_, p_i1164_2_, p_i1164_3_, p_i1164_4_);
		this.villagerArms = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
	    this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
	    this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
	    
        this.villagerHead = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.villagerHead.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i1164_1_);
        
        this.villagerNose = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.villagerNose.setTextureSize(p_i1164_3_, p_i1164_4_);
        this.villagerNose.setRotationPoint(0.0F, p_i1164_2_ - 2.0F, 0.0F);
        this.villagerNose.setTextureOffset(32,10).addBox(-3.0F, -1.0F, -5.0F, 6, 4, 2, p_i1164_1_);
        this.villagerHead.addChild(villagerNose);
		this.villagerEar = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
	    this.villagerEar.setTextureOffset(40,0).addBox(3.0F, -5.0F, -2.0F, 2, 4, 4, p_i1164_1_);
	    this.villagerEar.setTextureOffset(40,0).addBox(-5.0F, -5.0F, -2.0F, 2, 4, 4, p_i1164_1_);
	    this.villagerHead.addChild(villagerEar);
        

	    float rotation = -0.5235988F;
	    for (int k = 0; k < this.villagerPens.length; k++) {
	    	ModelRenderer villagerPen = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
	    	villagerPen.setTextureOffset(32,0).addBox(-1.0F, -14.0F, -1.0F, 2, 8, 2, p_i1164_1_);
	    	villagerPen.rotateAngleZ = rotation;
	    	this.villagerPens[k] = villagerPen;
		    this.villagerHead.addChild(villagerPen);
		    rotation += 0.2617994F;
	    }
	}
	
	
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
    	super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        this.villagerHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.villagerHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.villagerArms.rotationPointY = 3.0F;
        this.villagerArms.rotationPointZ = -1.0F;
        this.villagerArms.rotateAngleX = -0.75F;
        this.rightVillagerLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_ * 0.5F;
        this.leftVillagerLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_ * 0.5F;
        this.rightVillagerLeg.rotateAngleY = 0.0F;
        this.leftVillagerLeg.rotateAngleY = 0.0F;
    }
}
