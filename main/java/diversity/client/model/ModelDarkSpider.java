package diversity.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSpider;

@SideOnly(Side.CLIENT)
public class ModelDarkSpider extends ModelSpider
{ 
	public ModelDarkSpider()
	{
	    textureWidth = 128;
	    textureHeight = 64;
	    float b0 = 5f;
	    spiderHead = new ModelRenderer(this, 64, 8);
	    spiderHead.addBox(-4F, -4F, -8F, 16, 16, 16);
	    spiderHead.setRotationPoint(-4F, b0, -12F);
	    spiderHead.setTextureSize(128, 64);
	    spiderHead.mirror = true;
	    spiderBody = new ModelRenderer(this, 0, 0);
	    spiderBody.addBox(-3F, -3F, -3F, 12, 12, 12);
	    spiderBody.setRotationPoint(-3F, b0, -1F);
	    spiderBody.setTextureSize(128, 64);
	    spiderBody.mirror = true;
	    spiderNeck = new ModelRenderer(this, 0, 24);
	    spiderNeck.addBox(-5F, -4F, -6F, 20, 16, 24);
	    spiderNeck.setRotationPoint(-5F, b0, 14F);
	    spiderNeck.setTextureSize(128, 64);
	    spiderNeck.mirror = true;
	    spiderLeg8 = new ModelRenderer(this, 36, 0);
	    spiderLeg8.addBox(-1F, -1F, -1F, 32, 4, 4);
	    spiderLeg8.setRotationPoint(4F, b0, -1F);
	    spiderLeg8.setTextureSize(128, 64);
	    spiderLeg8.mirror = true;
	    spiderLeg6 = new ModelRenderer(this, 36, 0);
	    spiderLeg6.addBox(-1F, -1F, -1F, 32, 4, 4);
	    spiderLeg6.setRotationPoint(4F, b0, 0F);
	    spiderLeg6.setTextureSize(128, 64);
	    spiderLeg6.mirror = true;
	    spiderLeg4 = new ModelRenderer(this, 36, 0);
	    spiderLeg4.addBox(-1F, -1F, -1F, 32, 4, 4);
	    spiderLeg4.setRotationPoint(4F, b0, 1F);
	    spiderLeg4.setTextureSize(128, 64);
	    spiderLeg4.mirror = true;
	    spiderLeg2 = new ModelRenderer(this, 36, 0);
	    spiderLeg2.addBox(-1F, -1F, -1F, 32, 4, 4);
	    spiderLeg2.setRotationPoint(4F, b0, 2F);
	    spiderLeg2.setTextureSize(128, 64);
	    spiderLeg2.mirror = true;
	    spiderLeg7 = new ModelRenderer(this, 36, 0);
	    spiderLeg7.addBox(-31F, -1F, -1F, 32, 4, 4);
	    spiderLeg7.setRotationPoint(-4F, b0, -1F);
	    spiderLeg7.setTextureSize(128, 64);
	    spiderLeg7.mirror = true;
	    spiderLeg5 = new ModelRenderer(this, 36, 0);
	    spiderLeg5.addBox(-31F, -1F, -1F, 32, 4, 4);
	    spiderLeg5.setRotationPoint(-4F, b0, 0F);
	    spiderLeg5.setTextureSize(128, 64);
	    spiderLeg5.mirror = true;
	    spiderLeg3 = new ModelRenderer(this, 36, 0);
	    spiderLeg3.addBox(-31F, -1F, -1F, 32, 4, 4);
	    spiderLeg3.setRotationPoint(-4F, b0, 1F);
	    spiderLeg3.setTextureSize(128, 64);
	    spiderLeg3.mirror = true;
	    spiderLeg1 = new ModelRenderer(this, 36, 0);
	    spiderLeg1.addBox(-31F, -1F, -1F, 32, 4, 4);
	    spiderLeg1.setRotationPoint(-4F, b0, 2F);
	    spiderLeg1.setTextureSize(128, 64);
	    spiderLeg1.mirror = true;
	}
}
