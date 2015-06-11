package diversity.client.render.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelYeti;
import diversity.entity.EntityYeti;
import diversity.utils.ResourceTools;

@SideOnly(Side.CLIENT)
public class RenderYeti extends RenderLiving
{	
	private ModelYeti yetiModel;

	public RenderYeti() {
		super(new ModelYeti(), 0.5F);  
        this.yetiModel = (ModelYeti)this.mainModel;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return ResourceTools.getResource(EntityYeti.class);
	}
	
    protected void rotateCorpse(EntityYeti p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);

        if ((double)p_77043_1_.limbSwingAmount >= 0.01D)
        {
            float f3 = 13.0F;
            float f4 = p_77043_1_.limbSwing - p_77043_1_.limbSwingAmount * (1.0F - p_77043_4_) + 6.0F;
            float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
            GL11.glRotatef(6.5F * f5, 0.0F, 0.0F, 1.0F);
        }
    }
    
    protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        this.rotateCorpse((EntityYeti)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }
    
	  protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2)
	    {
		  float var3 = 1.0F;
	        GL11.glColor3f(var3, var3, var3);
	        super.renderEquippedItems(par1EntityLiving, par2);
	        ItemStack itemstack = par1EntityLiving.getHeldItem();
	        Item item;
	        float f1;

	        if (itemstack != null && itemstack.getItem() != null)
	        {
	            item = itemstack.getItem();
	            GL11.glPushMatrix();

	            this.yetiModel.ArmRight.postRender(0.0625F);
	            GL11.glTranslatef(-0.2225F, 1.0375F, 0.0325F);

	            net.minecraftforge.client.IItemRenderer customRenderer = net.minecraftforge.client.MinecraftForgeClient.getItemRenderer(itemstack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
	            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));

	            if (item instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType())))
	            {
	                f1 = 0.5F;
	                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
	                f1 *= 0.75F;
	                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	                GL11.glScalef(-f1, -f1, f1);
	            }
	            else if (item == Items.bow)
	            {
	                f1 = 0.625F;
	                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
	                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
	                GL11.glScalef(f1, -f1, f1);
	                GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	            }
	            else if (item.isFull3D())
	            {
	                f1 = 0.625F;

	                if (item.shouldRotateAroundWhenRendering())
	                {
	                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
	                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
	                }

	                GL11.glScalef(f1, -f1, f1);
	                GL11.glTranslatef(-0.3F, 1.4F, -0.8F);
	                GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	            }
	            else
	            {
	                f1 = 0.375F;
	                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
	                GL11.glScalef(f1, f1, f1);
	                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
	                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
	            }

	            float f2;
	            int i;
	            float f5;

	            if (itemstack.getItem().requiresMultipleRenderPasses())
	            {
	                for (i = 0; i < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++i)
	                {
	                    int j = itemstack.getItem().getColorFromItemStack(itemstack, i);
	                    f5 = (float)(j >> 16 & 255) / 255.0F;
	                    f2 = (float)(j >> 8 & 255) / 255.0F;
	                    float f3 = (float)(j & 255) / 255.0F;
	                    GL11.glColor4f(f5, f2, f3, 1.0F);
	                    this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, i);
	                }
	            }
	            else
	            {
	                i = itemstack.getItem().getColorFromItemStack(itemstack, 0);
	                float f4 = (float)(i >> 16 & 255) / 255.0F;
	                f5 = (float)(i >> 8 & 255) / 255.0F;
	                f2 = (float)(i & 255) / 255.0F;
	                GL11.glColor4f(f4, f5, f2, 1.0F);
	                this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, 0);
	            }

	            GL11.glPopMatrix();
	        }
	    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
    {
    	GL11.glScalef(1.5F, 1.5F, 1.5F);
    }
}

