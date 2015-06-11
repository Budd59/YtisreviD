package diversity.client.model;

import diversity.Diversity;
import diversity.entity.EntityYeti;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelYeti extends ModelBase
{
	  //fields
	    ModelRenderer Head;
	    ModelRenderer Body;
	    ModelRenderer LegLeft;
	    ModelRenderer LegRight;
	    ModelRenderer ArmLeft;
	    ModelRenderer Nose;
	    public ModelRenderer ArmRight;
	    ModelRenderer ArmLeftHide;
	    ModelRenderer ArmRightHide;
	    ModelRenderer HeadHide;
	    ModelRenderer LegRightHide;
	    ModelRenderer LegLeftHide;
	  
	  public ModelYeti()
	  {
	    textureWidth = 128;
	    textureHeight = 128;
	    
	      Head = new ModelRenderer(this, 0, 0);
	      Head.addBox(-4F, -9.5F, -5F, 8, 10, 8);
	      Head.setRotationPoint(0F, -9F, -1F);
	      Head.setTextureSize(128, 128);
	      Head.mirror = true;
	      setRotation(Head, 0F, 0F, 0F);
	      Body = new ModelRenderer(this, 0, 20);
	      Body.addBox(-8F, -10.5F, -6F, 16, 21, 12);
	      Body.setRotationPoint(0F, 0F, 3.5F);
	      Body.setTextureSize(128, 128);
	      Body.mirror = true;
	      setRotation(Body, 0.1745329F, 0F, 0F);
	      LegLeft = new ModelRenderer(this, 69, 3);
	      LegLeft.addBox(-3F, 0F, -3F, 6, 14, 6);
	      LegLeft.setRotationPoint(4F, 10F, 5F);
	      LegLeft.setTextureSize(128, 128);
	      LegLeft.mirror = true;
	      setRotation(LegLeft, 0F, 0F, 0F);
	      LegRight = new ModelRenderer(this, 69, 3);
	      LegRight.addBox(-3F, 0F, -3F, 6, 14, 6);
	      LegRight.setRotationPoint(-4F, 10F, 5F);
	      LegRight.setTextureSize(128, 128);
	      LegRight.mirror = true;
	      setRotation(LegRight, 0F, 0F, 0F);
	      LegRight.mirror = false;
	      ArmLeft = new ModelRenderer(this, 57, 24);
	      ArmLeft.addBox(-6F, -3F, -3F, 6, 23, 6);
	      ArmLeft.setRotationPoint(8F, -5F, 2F);
	      ArmLeft.setTextureSize(128, 128);
	      ArmLeft.mirror = true;
	      setRotation(ArmLeft, 0F, 3.141593F, 0F);
	      Nose = new ModelRenderer(this, 24, 0);
	      Nose.addBox(-1F, -2.5F, -7F, 2, 5, 2);
	      Nose.setRotationPoint(0F, -9F, -1F);
	      Nose.setTextureSize(128, 128);
	      Nose.mirror = true;
	      setRotation(Nose, -0.1570796F, 0F, 0F);
	      ArmRight = new ModelRenderer(this, 57, 24);
	      ArmRight.addBox(-6F, -3F, -3F, 6, 23, 6);
	      ArmRight.setRotationPoint(-8F, -5F, 2F);
	      ArmRight.setTextureSize(128, 128);
	      ArmRight.mirror = true;
	      setRotation(ArmRight, 0F, 0F, 0F);
	      ArmLeftHide = new ModelRenderer(this, 57, 55);
	      ArmLeftHide.addBox(-6.5F, -3.5F, -3.5F, 7, 17, 7);
	      ArmLeftHide.setRotationPoint(8F, -5F, 2F);
	      ArmLeftHide.setTextureSize(128, 128);
	      ArmLeftHide.mirror = true;
	      setRotation(ArmLeftHide, 0F, 3.141593F, 0F);
	      ArmRightHide = new ModelRenderer(this, 57, 55);
	      ArmRightHide.addBox(-6.5F, -3.533333F, -3.5F, 7, 17, 7);
	      ArmRightHide.setRotationPoint(-8F, -5F, 2F);
	      ArmRightHide.setTextureSize(128, 128);
	      ArmRightHide.mirror = true;
	      setRotation(ArmRightHide, 0F, 0F, 0F);
	      HeadHide = new ModelRenderer(this, 0, 54);
	      HeadHide.addBox(-4.5F, -10F, -5.5F, 9, 11, 9);
	      HeadHide.setRotationPoint(0F, -9F, -1F);
	      HeadHide.setTextureSize(128, 128);
	      HeadHide.mirror = true;
	      setRotation(HeadHide, 0F, 0F, 0F);
	      LegRightHide = new ModelRenderer(this, 83, 24);
	      LegRightHide.addBox(-3.5F, 0F, -3.5F, 7, 10, 7);
	      LegRightHide.setRotationPoint(-4F, 10F, 5F);
	      LegRightHide.setTextureSize(128, 128);
	      LegRightHide.mirror = true;
	      setRotation(LegRightHide, 0F, 0F, 0F);
	      LegLeftHide = new ModelRenderer(this, 83, 24);
	      LegLeftHide.addBox(-3.5F, 0F, -3.5F, 7, 10, 7);
	      LegLeftHide.setRotationPoint(4F, 10F, 5F);
	      LegLeftHide.setTextureSize(128, 128);
	      LegLeftHide.mirror = true;
	      setRotation(LegLeftHide, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    Head.render(f5);
	    Body.render(f5);
	    LegLeft.render(f5);
	    LegRight.render(f5);
	    ArmLeft.render(f5);
	    Nose.render(f5);
	    ArmRight.render(f5);
	    ArmLeftHide.render(f5);
	    ArmRightHide.render(f5);
	    HeadHide.render(f5);
	    LegRightHide.render(f5);
	    LegLeftHide.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    
	      EntityYeti yeti = (EntityYeti)entity;
	      float i = yeti.getAttackTimer();
	      //Diversity.Divlogger.info("" + i);

	      
	      if (i < 20)
	      {
	    	  ArmRight.rotateAngleX = -2.0F + 1.5F * this.func_78172_a(i - f5, 10.0F);
	    	  ArmLeft.rotateAngleX = 2.0F + 1.5F * this.func_78172_a(i - f5, 10.0F);
	      }
	      else
	      {
	    	  ArmRight.rotateAngleX = 1.5F * this.func_78172_a(f, 13.0F) * f1;;
	    	  ArmLeft.rotateAngleX = 1.5F * this.func_78172_a(f, 13.0F) * f1;
	      }
	      
	  
	      Head.rotateAngleY = f3 / 57.29578F;
	      Head.rotateAngleX = f4 / 57.29578F;
	      Nose.rotateAngleY = HeadHide.rotateAngleY = Head.rotateAngleY;
	      Nose.rotateAngleX = HeadHide.rotateAngleX = Head.rotateAngleX;
	      ArmRightHide.rotateAngleX = ArmRight.rotateAngleX;
	      ArmLeftHide.rotateAngleX = ArmLeft.rotateAngleX;
	      LegRight.rotateAngleX = 1.5F * this.func_78172_a(f, 13.0F) * f1;
	      LegRightHide.rotateAngleX = LegRight.rotateAngleX;
	      LegLeft.rotateAngleX = -1.5F * this.func_78172_a(f, 13.0F) * f1;
	      LegLeftHide.rotateAngleX = LegLeft.rotateAngleX;
	  }
	  
	    private float func_78172_a(float p_78172_1_, float p_78172_2_)
	    {
	        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	    }


	/*
    ModelRenderer leftBipedEar;
    ModelRenderer rightBipedEar;
    ModelRenderer bipedLips;
    ModelRenderer bipedNeck;
    ModelRenderer bipedVortex;
    ModelRenderer upperBipedBody;    
    
	public ModelYeti()
	{
		textureWidth = 128;
		textureHeight = 128;

		bipedRightLeg = new ModelRenderer(this, 0, 100);
		bipedRightLeg.addBox(-5F, -1F, -5F, 10, 18, 10);
		bipedRightLeg.setRotationPoint(-5F, 7F, -1F);
		bipedRightLeg.setTextureSize(128, 128);
		setRotation(bipedRightLeg, 0F, 0F, 0F);
		bipedLeftLeg = new ModelRenderer(this, 0, 100);
		bipedLeftLeg.addBox(-5F, -1F, -5F, 10, 18, 10);
		bipedLeftLeg.setRotationPoint(5F, 7F, -1F);
		bipedLeftLeg.setTextureSize(128, 128);
		bipedLeftLeg.mirror = true;
		setRotation(bipedLeftLeg, 0F, 0F, 0F);
		
		bipedBody = new ModelRenderer(this, 41, 102);
		bipedBody.addBox(-11F, -7F, -6F, 22, 14, 12);
		bipedBody.setRotationPoint(0F, -1F, -1F);
		bipedBody.setTextureSize(128, 128);
		setRotation(bipedBody, 0F, 0F, 0F);
		upperBipedBody = new ModelRenderer(this, 41, 72);
		upperBipedBody.addBox(-11.5F, -21F, -8F, 23, 14, 15);
		upperBipedBody.setRotationPoint(0F, 0F, 0F);
		upperBipedBody.setTextureSize(128, 128);
		setRotation(upperBipedBody, 0F, 0F, 0F);
		bipedBody.addChild(upperBipedBody);
		bipedNeck = new ModelRenderer(this, 41, 57);
		bipedNeck.addBox(-11F, -22F, -7F, 22, 1, 13);
		bipedNeck.setRotationPoint(0F, 0F, 0F);
		bipedNeck.setTextureSize(128, 128);
		setRotation(bipedNeck, 0F, 0F, 0F);
		bipedBody.addChild(bipedNeck);

		bipedRightArm = new ModelRenderer(this, 0, 50);
		bipedRightArm.addBox(-5F, -3F, -5F, 10, 39, 10);
		bipedRightArm.setRotationPoint(-16.5F, -21F, -1F);
		bipedRightArm.setTextureSize(128, 128);
		setRotation(bipedRightArm, 0F, 0F, 0F);
		bipedLeftArm = new ModelRenderer(this, 0, 50);
		bipedLeftArm.addBox(-5F, -3F, -5F, 10, 39, 10);
		bipedLeftArm.setRotationPoint(16.5F, -21F, -1F);
		bipedLeftArm.setTextureSize(128, 128);
		bipedLeftArm.mirror = true;
		setRotation(bipedLeftArm, 0F, 0F, 0F);
		
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-8F, -17F, -8F, 16, 16, 13);
		bipedHead.setRotationPoint(0F, -22F, -1F);
		bipedHead.setTextureSize(128, 128);
		setRotation(bipedHead, 0F, 0F, 0F);
		leftBipedEar = new ModelRenderer(this, 29, 30);
		leftBipedEar.addBox(7.5F, -11F, -1F, 1, 4, 3);
		leftBipedEar.setRotationPoint(0F, 0F, 0F);
		leftBipedEar.setTextureSize(128, 128);
		setRotation(leftBipedEar, 0.1047198F, 0.5235988F, 0F);
		bipedHead.addChild(leftBipedEar);
		rightBipedEar = new ModelRenderer(this, 29, 30);
		rightBipedEar.addBox(-8.5F, -11F, -1F, 1, 4, 3);
		rightBipedEar.setRotationPoint(0F, 0F, 0F);
		rightBipedEar.setTextureSize(128, 128);
		setRotation(rightBipedEar, 0.1047198F, -0.5235988F, 0F);
		bipedHead.addChild(rightBipedEar);
		bipedLips = new ModelRenderer(this, 0, 30);
		bipedLips.addBox(-6F, -8F, -10F, 12, 7, 2);
		bipedLips.setRotationPoint(0F, 0F, 0F);
		bipedLips.setTextureSize(128, 128);
		setRotation(bipedLips, 0F, 0F, 0F);
		bipedHead.addChild(bipedLips);
		bipedVortex = new ModelRenderer(this, 59, 0);
		bipedVortex.addBox(-6F, -19F, -6F, 12, 12, 13);
		bipedVortex.setRotationPoint(0F, 0F, 0F);
		bipedVortex.setTextureSize(128, 128);
		setRotation(bipedVortex, 0F, 0F, 0F);
		bipedHead.addChild(bipedVortex);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		//super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		//leftBipedEar.render(f5);
		//rightBipedEar.render(f5);
		//bipedLips.render(f5);
		bipedLeftLeg.render(f5);
		bipedRightLeg.render(f5);
		//bipedNeck.render(f5);
		bipedRightArm.render(f5);
		//upperBipedBody.render(f5);
		bipedBody.render(f5);
		bipedLeftArm.render(f5);
		bipedHead.render(f5);
		//bipedVortex.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.bipedHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleX = (22.5F + p_78087_5_) / (180F / (float)Math.PI);
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 16.5F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 16.5F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 16.5F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 16.5F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
        }

        this.bipedBody.rotateAngleX = 0.0F;
        //this.bipedRightLeg.rotationPointZ = 0.1F;
        //this.bipedLeftLeg.rotationPointZ = 0.1F;
        //this.bipedRightLeg.rotationPointY = 12.0F;
        //this.bipedLeftLeg.rotationPointY = 12.0F;
        //this.bipedHead.rotationPointY = 0.0F;
        //this.bipedHeadwear.rotationPointY = 0.0F;

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
    }
*/
}

