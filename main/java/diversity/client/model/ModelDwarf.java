package diversity.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDwarf extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer LegLeft;
    ModelRenderer LegRight;
    ModelRenderer ArmLeftShoulder;
    ModelRenderer ArmLeftHand;
    ModelRenderer ArmRightHand;
    ModelRenderer Nose;
    ModelRenderer Skirt;
    ModelRenderer Beard1;
    ModelRenderer Beard2;
    ModelRenderer Helmet;
    public ModelRenderer ArmRightHandC;
    ModelRenderer ArmLeftHandC;
    ModelRenderer Cape;
    ModelRenderer HelmLight;
  
  public ModelDwarf()
  {
    textureWidth = 64;
    textureHeight = 128;
    
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-4F, -9F, -4F, 8, 10, 8);
      Head.setRotationPoint(0F, 7F, -1F);
      Head.setTextureSize(64, 128);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 39);
      Body.addBox(-5F, 0F, -4F, 8, 10, 6);
      Body.setRotationPoint(1F, 8F, 0F);
      Body.setTextureSize(64, 128);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      LegLeft = new ModelRenderer(this, 0, 22);
      LegLeft.addBox(-2.1F, 0F, -2F, 4, 7, 4);
      LegLeft.setRotationPoint(2F, 17F, -1F);
      LegLeft.setTextureSize(64, 128);
      LegLeft.mirror = true;
      setRotation(LegLeft, 0F, 0F, 0F);
      LegRight = new ModelRenderer(this, 0, 22);
      LegRight.addBox(-1.9F, 0F, -2F, 4, 7, 4);
      LegRight.setRotationPoint(-2F, 17F, -1F);
      LegRight.setTextureSize(64, 128);
      LegRight.mirror = true;
      setRotation(LegRight, 0F, 0F, 0F);
      ArmLeftShoulder = new ModelRenderer(this, 40, 38);
      ArmLeftShoulder.addBox(-4F, 0.1F, -0.3F, 8, 4, 4);
      ArmLeftShoulder.setRotationPoint(0F, 15F, -5F);
      ArmLeftShoulder.setTextureSize(64, 128);
      ArmLeftShoulder.mirror = true;
      setRotation(ArmLeftShoulder, 2.356194F, 0F, 0F);
      ArmLeftHand = new ModelRenderer(this, 44, 22);
      ArmLeftHand.addBox(2F, -7F, 1F, 4, 6, 4);
      ArmLeftHand.setRotationPoint(2F, 11F, 1F);
      ArmLeftHand.setTextureSize(64, 128);
      ArmLeftHand.mirror = true;
      setRotation(ArmLeftHand, 2.356194F, 0F, 0F);
      ArmRightHand = new ModelRenderer(this, 44, 22);
      ArmRightHand.addBox(-6F, -7F, 1F, 4, 6, 4);
      ArmRightHand.setRotationPoint(-2F, 11F, 1F);
      ArmRightHand.setTextureSize(64, 128);
      ArmRightHand.mirror = true;
      setRotation(ArmRightHand, 2.356194F, 0F, 0F);
      Nose = new ModelRenderer(this, 24, 0);
      Nose.addBox(-1F, -2F, -7F, 2, 4, 2);
      Nose.setRotationPoint(0F, 7F, 0F);
      Nose.setTextureSize(64, 128);
      Nose.mirror = true;
      setRotation(Nose, 0F, 0F, 0F);
      Skirt = new ModelRenderer(this, 32, 4);
      Skirt.addBox(-5.5F, 8F, -4.5F, 9, 6, 7);
      Skirt.setRotationPoint(1F, 7F, 0F);
      Skirt.setTextureSize(64, 128);
      Skirt.mirror = true;
      setRotation(Skirt, 0F, 0F, 0F);
      Beard1 = new ModelRenderer(this, 28, 46);
      Beard1.addBox(-4.5F, -4F, -4.5F, 9, 18, 9);
      Beard1.setRotationPoint(0F, 7F, -1F);
      Beard1.setTextureSize(64, 128);
      Beard1.mirror = true;
      setRotation(Beard1, 0F, 0F, 0F);
      Beard2 = new ModelRenderer(this, 42, 75);
      Beard2.addBox(-5F, -5F, -5F, 10, 18, 1);
      Beard2.setRotationPoint(0F, 8F, -1F);
      Beard2.setTextureSize(64, 128);
      Beard2.mirror = true;
      setRotation(Beard2, 0F, 0F, 0F);
      Helmet = new ModelRenderer(this, 2, 75);
      Helmet.addBox(-4.5F, -9.5F, -4.5F, 9, 5, 9);
      Helmet.setRotationPoint(0F, 7F, -1F);
      Helmet.setTextureSize(64, 128);
      Helmet.mirror = true;
      setRotation(Helmet, 0F, 0F, 0F);
      ArmRightHandC = new ModelRenderer(this, 22, 22);
      ArmRightHandC.addBox(-4F, -1F, -2F, 4, 10, 4);
      ArmRightHandC.setRotationPoint(-4F, 9F, -1F);
      ArmRightHandC.setTextureSize(64, 128);
      ArmRightHandC.mirror = true;
      setRotation(ArmRightHandC, 0F, 0F, 0F);
      ArmLeftHandC = new ModelRenderer(this, 22, 22);
      ArmLeftHandC.addBox(0F, -1F, -2F, 4, 10, 4);
      ArmLeftHandC.setRotationPoint(4F, 9F, -1F);
      ArmLeftHandC.setTextureSize(64, 128);
      ArmLeftHandC.mirror = true;
      setRotation(ArmLeftHandC, 0F, 0F, 0F);
      Cape = new ModelRenderer(this, 0, 98);
      Cape.addBox(-8.5F, -1F, -7F, 17, 14, 8);
      Cape.setRotationPoint(0F, 8F, 3F);
      Cape.setTextureSize(64, 128);
      Cape.mirror = true;
      setRotation(Cape, 0F, 0F, 0F);
      HelmLight = new ModelRenderer(this, 2, 71);
      HelmLight.addBox(-1.5F, -8.5F, -5.6F, 3, 2, 1);
      HelmLight.setRotationPoint(0F, 7F, -1F);
      HelmLight.setTextureSize(64, 128);
      HelmLight.mirror = true;
      setRotation(HelmLight, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Head.render(f5);
    Body.render(f5);
    LegLeft.render(f5);
    LegRight.render(f5);
    ArmLeftShoulder.render(f5);
    ArmLeftHand.render(f5);
    ArmRightHand.render(f5);
    Nose.render(f5);
    Skirt.render(f5);
    Beard1.render(f5);
    Beard2.render(f5);
    Helmet.render(f5);
    ArmRightHandC.render(f5);
    ArmLeftHandC.render(f5);
    Cape.render(f5);
    HelmLight.render(f5);
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
    this.Head.rotateAngleY = f4 / (180F / (float)Math.PI);
    this.Head.rotateAngleX = f5 / (180F / (float)Math.PI);
    this.Nose.rotateAngleY = Helmet.rotateAngleY = Beard1.rotateAngleY = Beard2.rotateAngleY = HelmLight.rotateAngleY = Head.rotateAngleY;
    this.Nose.rotateAngleX = Helmet.rotateAngleX = Beard1.rotateAngleX = Beard2.rotateAngleX = HelmLight.rotateAngleX = Head.rotateAngleX;
    this.ArmRightHandC.rotateAngleX = MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.6F * f1;
    this.ArmLeftHandC.rotateAngleX = -MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.6F * f1;
    this.LegRight.rotateAngleX = MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.6F * f1;
    this.LegLeft.rotateAngleX = - MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.6F * f1;
  }

}