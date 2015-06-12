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
	private int index;
	public ModelDarkSpider(int i)
	{
		index = i;
		scale = 0;
	}
	
	@Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        EntityDarkSpider spider = (EntityDarkSpider)p_78088_1_;
        
        if(index == 0)
        {
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

        }
        
        if(index == 1 && spider.getPhase() < 2)
        {
            GL11.glPushMatrix();
            GL11.glScalef(scale, scale, scale);
            
            GL11.glScalef(0.1f, 0.1f, 0.1f);
            GL11.glTranslatef(0f, 2f, 3f);
            GL11.glRotatef(80f, 0f, 1f, 0f);
            this.renderMiniSpider(p_78088_7_);

            GL11.glRotatef(100f, 0f, 1f, 0f);
            GL11.glTranslatef(-3f, 3f, -1f);
            GL11.glRotatef(90f, 0f, 0f, -1f);
            this.renderMiniSpider(p_78088_7_);
            
            GL11.glRotatef(90f, 0f, 0f, 1f);
            GL11.glTranslatef(6f, -0.2f, 1.5f);
            GL11.glRotatef(180f, 0f, 1f, 0f);
            GL11.glRotatef(90f, 0f, 0f, -1f);
            this.renderMiniSpider(p_78088_7_);
            
            GL11.glRotatef(180f, 0f, -1f, 0f);
            GL11.glTranslatef(0f, 3f, -3.5f);
            GL11.glRotatef(90f, 1f, 0f, 0f);
            this.renderMiniSpider(p_78088_7_);
        	GL11.glPopMatrix();
            
        	GL11.glPushMatrix();
        	GL11.glScalef((scale/2) * 1.5f, (scale/2) * 1.5f, (scale/2) * 1.5f);
        	GL11.glTranslatef(0f, -0.35f, -0.1f);
        	this.spiderBody.render(p_78088_7_);
        	GL11.glPopMatrix();
        	

        }
        else
        {
        	this.spiderBody.render(p_78088_7_);
        }
    }
	
	public void renderMiniSpider(float p_78088_7_){
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
        this.spiderBody.render(p_78088_7_);
	}
	
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {

		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
		if(index == 1)
		{
	        this.spiderHead.rotateAngleY = 0f;
	        this.spiderHead.rotateAngleX = 0f;
			scale = p_78087_3_ + 2;
		}


    }
}
