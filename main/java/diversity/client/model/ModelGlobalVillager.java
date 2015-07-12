package diversity.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.entity.AGlobalEntityVillager;

@SideOnly(Side.CLIENT)
public abstract class ModelGlobalVillager extends ModelBiped
{	
    public ModelRenderer bipedVillagerArms;
    public ModelRenderer bipedVillagerNose;

	public ModelGlobalVillager(float p_i1163_1_) {
		this(p_i1163_1_, 0.0F, 64, 64);
	}

	public ModelGlobalVillager(float p_i1164_1_, float p_i1164_2_, int p_i1164_3_, int p_i1164_4_) {
		super(p_i1164_1_, p_i1164_2_, p_i1164_3_, p_i1164_4_);
		
        this.bipedHead = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i1164_1_);
        this.bipedVillagerNose = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedVillagerNose.setRotationPoint(0.0F, p_i1164_2_ - 2.0F, 0.0F);
        this.bipedVillagerNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, p_i1164_1_);
        this.bipedHead.addChild(this.bipedVillagerNose);
        this.bipedBody = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.bipedBody.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i1164_1_);
        this.bipedBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i1164_1_ + 0.5F);
        this.bipedVillagerArms = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedVillagerArms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
        this.bipedVillagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i1164_1_);
        this.bipedVillagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i1164_1_);
        this.bipedVillagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i1164_1_);
        this.bipedRightLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1164_1_);
        this.bipedLeftLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1164_1_);

        
        this.bipedRightArm = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1164_2_, 0.0F);
        this.bipedRightArm.setTextureOffset(44, 22).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
        this.bipedLeftArm = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1164_2_, 0.0F);
        this.bipedLeftArm.setTextureOffset(44, 22).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
	}
	
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        if (this.isChild)
        {
            GL11.glPushMatrix();
            GL11.glScalef(1.3F, 1.3F, 1.3F);
            //GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
            this.bipedHead.render(p_78088_7_);
            GL11.glPopMatrix();
        } else {
        	this.bipedHead.render(p_78088_7_);
        }
        this.bipedBody.render(p_78088_7_);
        if (p_78088_1_ instanceof AGlobalEntityVillager && ((AGlobalEntityVillager)p_78088_1_).canDefend() && !((AGlobalEntityVillager) p_78088_1_).isChild()) {
            this.bipedRightArm.render(p_78088_7_);
            this.bipedLeftArm.render(p_78088_7_);
        } else {
        	this.bipedVillagerArms.render(p_78088_7_);
        }
        this.bipedRightLeg.render(p_78088_7_);
        this.bipedLeftLeg.render(p_78088_7_);
    }
    
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
    	super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);

        this.bipedVillagerArms.rotationPointY = 3.0F;
        this.bipedVillagerArms.rotationPointZ = -1.0F;
        this.bipedVillagerArms.rotateAngleX = -0.75F;
    }
}
