package diversity.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


public class RenderSpear implements IItemRenderer
{
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
    private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.EQUIPPED;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		EntityLivingBase entityLivingBase;
		if (data.length == 2 && data[1] instanceof EntityLivingBase) {
			entityLivingBase = (EntityLivingBase)data[1];
		} else {
			return;
		}
		
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        IIcon iicon = entityLivingBase.getItemIcon(item, 0);

        if (iicon == null)
        {
            GL11.glPopMatrix();
            return;
        }
        
        TextureUtil.func_152777_a(false, false, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        float f = iicon.getMinU();
        float f1 = iicon.getMaxU();
        float f2 = iicon.getMinV();
        float f3 = iicon.getMaxV();
        
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        
        if (type == type.EQUIPPED_FIRST_PERSON)
        {	
        	GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
	        GL11.glScalef(1.0f, 2.0f, 1.0f);
		    GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
		    GL11.glTranslatef(0.15f,  0.0f, 0.0f);
        }
        else
        if (type == type.EQUIPPED)
        {
        	GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
	        GL11.glScalef(1.0f, 2.0f, 1.0f);
		    GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        }
        
        ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        texturemanager.bindTexture(texturemanager.getResourceLocation(item.getItemSpriteNumber()));
        TextureUtil.func_147945_b();
    }
}
