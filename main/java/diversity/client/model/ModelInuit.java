package diversity.client.model;

import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelInuit extends ModelGlobalVillager
{	
	public ModelRenderer villagerHoodie;
	
    public ModelInuit(float p_i1163_1_)
    {
        this(p_i1163_1_, 0.0F, 64, 64);
    }
    
    public ModelInuit(float p_i1164_1_, float p_i1164_2_, int p_i1164_3_, int p_i1164_4_)
	{
    	super(p_i1164_1_, p_i1164_2_, p_i1164_3_, p_i1164_4_);
        this.villagerHoodie = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.villagerHoodie.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.villagerHoodie.setTextureOffset(0, 18).addBox(-5.0F, -11.0F, -5.0F, 10, 12, 10, p_i1164_1_);
        this.bipedHead.addChild(villagerHoodie);
        this.bipedBody = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.bipedBody.setTextureOffset(0, 40).addBox(-4.5F, 0.0F, -3.5F, 9, 17, 7, p_i1164_1_ + 0.5F);
        this.bipedVillagerArms = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedVillagerArms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
        this.bipedVillagerArms.setTextureOffset(48, 0).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i1164_1_);
        this.bipedVillagerArms.setTextureOffset(48, 0).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i1164_1_);
        this.bipedVillagerArms.setTextureOffset(31, 19).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i1164_1_);
        this.bipedRightLeg = (new ModelRenderer(this, 32,0)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1164_1_);
        this.bipedLeftLeg = (new ModelRenderer(this, 32,0)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1164_1_);
        
        this.bipedRightArm = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1164_2_, 0.0F);
        this.bipedRightArm.setTextureOffset(48, 0).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
        this.bipedLeftArm = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1164_2_, 0.0F);
        this.bipedLeftArm.setTextureOffset(48, 0).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1164_1_);
	}
}

