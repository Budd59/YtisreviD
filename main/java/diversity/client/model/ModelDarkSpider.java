package diversity.client.model;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.entity.EntityDarkSpider;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.entity.Entity;


@SideOnly(Side.CLIENT)
public class ModelDarkSpider extends ModelSpider
{ 
	public float scale;
	public ModelDarkSpider()
	{
		scale = 0;
	}
	
	@Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        EntityDarkSpider spider = (EntityDarkSpider)p_78088_1_;
        this.spiderHead.render(p_78088_7_);
        this.spiderNeck.render(p_78088_7_);
        this.spiderLeg1.render(p_78088_7_);
        this.spiderLeg2.render(p_78088_7_);
        this.spiderLeg3.render(p_78088_7_);
        this.spiderLeg4.render(p_78088_7_);
        this.spiderLeg5.render(p_78088_7_);
        this.spiderLeg6.render(p_78088_7_);
        this.spiderLeg7.render(p_78088_7_);
        this.spiderLeg8.render(p_78088_7_);
        
        if(spider.getPhase() < 2)
        {
        	GL11.glPushMatrix();
        	GL11.glScalef((scale/2) * 2.0f, 2f, 2f);
        	GL11.glTranslatef(0f, -0.45f, -0.1f);
        	this.spiderBody.render(p_78088_7_);
        	GL11.glPopMatrix();
        }
        else
        {
        	this.spiderBody.render(p_78088_7_);
        }
    }
	
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
		scale = p_78087_3_ + 2;
    }
}
