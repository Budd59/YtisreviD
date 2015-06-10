package diversity.client.render.item;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import diversity.item.ItemBlowgun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderBlowgun implements IItemRenderer {
	 private RenderManager renderManager;
	 private Minecraft mc;
	 
	 public RenderBlowgun() {
		 this.renderManager = RenderManager.instance;
		 this.mc = Minecraft.getMinecraft();
	 }
	
	 @Override
	 public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		 //This renderer overrides the rendering when held in first and third person.
		 return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	 }
	 
	 @Override
	 public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack stack, ItemRendererHelper helper) {
		 //Rendering helpers aren't needed.
		 return false;
	 }
	 
	 @Override
	 //Called when the item is rendered. This is all copied from the bow rendering code, with little tweaks.
	 public void renderItem(ItemRenderType type, ItemStack stack, Object... data)
	 {
	 
		 EntityPlayer player = null;
		 EntityLivingBase baseEntity = (EntityLivingBase) data[1];
		 
		 if(data[1] instanceof EntityPlayer) {
			 player = (EntityPlayer)data[1];
		 }
		 
		 //Make sure the item isn't already rotated/translated/scaled.
		 GL11.glPopMatrix();
		 
		 if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
		 
			 int count = 0;
			 
			 if(player != null) {
				 count = player.getItemInUseCount();
			 }
		 
			 if(count > 0)
			 {
				 //Two more layers of translations/scales/rotations to get rid of in this case.
				 GL11.glPopMatrix();
				 GL11.glPopMatrix();
				 
				 GL11.glPushMatrix();
				 
				 float f13 = 0.8F;
				 float f5 = baseEntity.getSwingProgress(0.0f);
				 float f6 = MathHelper.sin(f5 * (float)Math.PI);
				 float f7 = MathHelper.sin(MathHelper.sqrt_float(f5) * (float)Math.PI);
				 GL11.glTranslatef(-f7 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(f5) * (float)Math.PI * 2.0F) * 0.2F, -f6 * 0.2F);
				 GL11.glTranslatef(0.7F * f13, -0.65F * f13 - (0.0F) * 0.6F, -0.9F * f13);
				 GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				 GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				 GL11.glRotatef(-f6 * 20.0F, 0.0F, 1.0F, 0.0F);
				 GL11.glRotatef(-f7 * 20.0F, 0.0F, 0.0F, 1.0F);
				 GL11.glRotatef(-f7 * 80.0F, 1.0F, 0.0F, 0.0F);
				 float f8 = 0.4F;
				 GL11.glScalef(f8, f8, f8);
				 
				 GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
				 GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
				 GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
				 GL11.glTranslatef(-0.9F, 0.2F, 0.0F);
		 
				 //Get the added bow item for it's stored variables.
				 ItemBlowgun blowgun = (ItemBlowgun) stack.getItem();
				 
				 float f10 = (float)blowgun.getMaxItemUseDuration(stack) - ((float)count + 1.0F);
				 float f11 = f10 / 8;// / blowgun.getDrawTime(stack);
				 f11 = (f11 * f11 + f11 * 2.0F) / 3.0F;
				 
				 if (f11 > 1.0F)
				 {
					 f11 = 1.0F;
				 }
		 
				 if (f11 > 0.1F)
				 {
					 GL11.glTranslatef(0.0F, MathHelper.sin((f10 - 0.1F) * 1.3F) * 0.01F * (f11 - 0.1F), 0.0F);
				 }
		 
				 GL11.glTranslatef(0.0F, 0.0F, f11 * 0.1F);
				 GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
				 GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
				 GL11.glTranslatef(0.0F, 0.5F, 0.0F);
				 float f12 = 1.0F + f11 * 0.2F;
				 GL11.glScalef(1.0F, 1.0F, f12);
				 GL11.glTranslatef(0.0F, -0.5F, 0.0F);
				 GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
				 GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
		 
				 //Finally render the item.
				 this.renderItem(baseEntity, stack, 0);
				 
				 //Restore the matrices to what they were (since the rest of the code expects it, and otherwise there would be errors).
				 GL11.glPopMatrix();
				 GL11.glPushMatrix();
				 GL11.glPushMatrix();
			 } else {
				 //If the bow isn't being drawn, render normally.
				 this.renderItem(baseEntity, stack, 0);
			 }
		 } else {
			 //We're rendering the bow in 3rd person. The code is from RenderBiped.
			 GL11.glPushMatrix();
			 
			 float f2 = 3F - (1F/3F);
			 GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
			 GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			 GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
			 GL11.glScalef(f2, f2, f2);
			 GL11.glTranslatef(-0.25F, -0.1875F, 0.1875F);
	
			 float f3 = 0.625F;
			 GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
			 GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
			 GL11.glScalef(f3, -f3, f3);
			 GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
			 GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
		
			//Finally render the bow item.
			 this.renderItem(baseEntity, stack, 0);
			 
			 GL11.glPopMatrix();
		 }
		 
		 //Push the matrix back (it should be empty now, but the rest of the rendering code expects it to be there, 
		 //so otherwise there would be errors).
		 GL11.glPushMatrix();
	 }
	
	//Renders an item. This code is from the ItemRenderer.
	private void renderItem(EntityLivingBase entity, ItemStack stack, int pass)
	{
		TextureManager texturemanager = this.mc.getTextureManager();
	
		IIcon iicon = entity.getItemIcon(stack, pass);
	
		if (iicon == null || texturemanager == null)
		{
			System.err.println("Um... there was no texture. This should never happen.");
			GL11.glPopMatrix();
			return;
		}
	
		texturemanager.bindTexture(texturemanager.getResourceLocation(stack.getItemSpriteNumber()));
		TextureUtil.func_152777_a(false, false, 1.0F);
		Tessellator tessellator = Tessellator.instance;
		float f = iicon.getMinU();
		float f1 = iicon.getMaxU();
		float f2 = iicon.getMinV();
		float f3 = iicon.getMaxV();
		float f4 = 0.0F;
		float f5 = 0.3F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef(-f4, -f5, 0.0F);
		float f6 = 1.5F;
		GL11.glScalef(f6, f6, f6);
		GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
		ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);
	
		if (stack.hasEffect(pass))
		{
			 GL11.glDepthFunc(GL11.GL_EQUAL);
			 GL11.glDisable(GL11.GL_LIGHTING);
			 texturemanager.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
			 GL11.glEnable(GL11.GL_BLEND);
			 OpenGlHelper.glBlendFunc(768, 1, 1, 0);
			 float f7 = 0.76F;
			 GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
			 GL11.glMatrixMode(GL11.GL_TEXTURE);
			 GL11.glPushMatrix();
			 float f8 = 0.125F;
			 GL11.glScalef(f8, f8, f8);
			 float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
			 GL11.glTranslatef(f9, 0.0F, 0.0F);
			 GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
			 ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			 GL11.glPopMatrix();
			 GL11.glPushMatrix();
			 GL11.glScalef(f8, f8, f8);
			 f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
			 GL11.glTranslatef(-f9, 0.0F, 0.0F);
			 GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
			 ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			 GL11.glPopMatrix();
			 GL11.glMatrixMode(GL11.GL_MODELVIEW);
			 GL11.glDisable(GL11.GL_BLEND);
			 GL11.glEnable(GL11.GL_LIGHTING);
			 GL11.glDepthFunc(GL11.GL_LEQUAL);
		}
	
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		texturemanager.bindTexture(texturemanager.getResourceLocation(stack.getItemSpriteNumber()));
		TextureUtil.func_147945_b();
	}
}

