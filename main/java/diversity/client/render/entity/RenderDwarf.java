package diversity.client.render.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelAztec;
import diversity.client.model.ModelDwarf;
import diversity.client.model.ModelGlobalVillager;
import diversity.entity.EntityDwarf;
import diversity.entity.AGlobalEntityVillager;
import diversity.utils.ResourceTools;


@SideOnly(Side.CLIENT)
public class RenderDwarf extends RenderLiving
{
    private static final ResourceLocation defaultTexture = new ResourceLocation("textures/entity/villager/villager.png");
    protected ModelDwarf dwarfModel;
    
	public RenderDwarf()
	{
		super(new ModelDwarf(), 0.5f);
		this.dwarfModel = (ModelDwarf)this.mainModel;
	}
	
    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(AGlobalEntityVillager p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityLiving)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2)
    {
    	float var3 = 1.0F;
        GL11.glColor3f(var3, var3, var3);
        super.renderEquippedItems(par1EntityLiving, par2);
        
    	if(par1EntityLiving.getHeldItem() != null)
    	{
            GL11.glPushMatrix();

            this.dwarfModel.ArmRightHandC.postRender(0.0625F);
            GL11.glTranslatef(0.1F, -0.5F, 0.15F);
            GL11.glRotatef(145F, 1F, 0F, 0F);
            renderItem(par1EntityLiving, par1EntityLiving.getHeldItem());

            GL11.glPopMatrix();
        }
    }
    
    
    public void renderItem(EntityLivingBase par1EntityLiving, ItemStack itemstack)
    {
        Item item;
        if (itemstack != null && itemstack.getItem() != null)
        {
            item = itemstack.getItem();

        float f1;
    	if (this.mainModel.isChild)
        {
            f1 = 0.5F;
            GL11.glTranslatef(-0.2F, 0.625F, 0.0F);
            GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
            GL11.glScalef(f1, f1, f1);
        }

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
        }
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(AGlobalEntityVillager entity)
    {
    	if (!entity.isChild()) {
    		return VillagerRegistry.getVillagerSkin(entity.getProfession(), defaultTexture);
    	} else {
    		return ResourceTools.getResource(entity.getClass());
    	}
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((AGlobalEntityVillager)p_110775_1_);
    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((AGlobalEntityVillager)p_77041_1_, p_77041_2_);
    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(AGlobalEntityVillager p_77041_1_, float p_77041_2_)
    {
    	super.preRenderCallback(p_77041_1_, p_77041_2_);
        float f1 = 0.9375F;

        if (p_77041_1_.getGrowingAge() < 0)
        {
            f1 = (float)((double)f1 * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GL11.glScalef(f1, f1, f1);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(AGlobalEntityVillager p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        super.doRender((AGlobalEntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((AGlobalEntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((AGlobalEntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((AGlobalEntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}