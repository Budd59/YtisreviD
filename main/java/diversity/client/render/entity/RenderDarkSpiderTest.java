package diversity.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.Diversity;
import diversity.client.model.ModelDarkSpiderTest;
import diversity.entity.EntityDarkSpiderTest;
import diversity.entity.EntityWarriorSkeleton;
import diversity.suppliers.EnumEntity;
import diversity.utils.PathTool;
import diversity.utils.ResourceTools;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderDarkSpiderTest extends RenderLiving
{
    private static final ResourceLocation spiderEyesTextures = new ResourceLocation(Diversity.MODID+  ":" + "textures/entities/monsters/spider_eyes.png");
    private static final ResourceLocation spiderTransparentBackTextures = new ResourceLocation(Diversity.MODID+  ":" + "textures/entities/monsters/darkspidertest_transparentback.png");
    private static final ResourceLocation spiderBackTextures = new ResourceLocation(Diversity.MODID+  ":" + "textures/entities/monsters/darkspidertest_back.png");
    private ModelBase eggTransparentSack;
    private ModelBase eggStack;
    
    public RenderDarkSpiderTest()
    {
        super(new ModelDarkSpiderTest(0), 1.0F);
        this.setRenderPassModel(mainModel);
        this.eggTransparentSack = new ModelDarkSpiderTest(1);
        this.eggStack = new ModelDarkSpiderTest(2);
    }

    protected float getDeathMaxRotation(EntityDarkSpiderTest p_77037_1_)
    {
        return 180.0F;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityDarkSpiderTest p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
    	if (p_77032_2_ == 0)
        {
            this.setRenderPassModel(this.eggStack);
            this.bindTexture(spiderBackTextures);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        } if (p_77032_2_ == 1)
        {
            this.setRenderPassModel(this.eggTransparentSack);
            this.bindTexture(spiderTransparentBackTextures);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        } else 
        {
            if (p_77032_2_ == 2)
            {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            this.bindTexture(spiderEyesTextures);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

            if (p_77032_1_.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            return -1;
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDarkSpiderTest p_110775_1_)
    {
        return ResourceTools.getResource(EntityDarkSpiderTest.class);
    }

    protected float getDeathMaxRotation(EntityLivingBase p_77037_1_)
    {
        return this.getDeathMaxRotation((EntityDarkSpiderTest)p_77037_1_);
    }
    
    protected float handleRotationFloat(EntityLivingBase entityliving, float f)
    {
    	EntityDarkSpiderTest entityDarkSpider = (EntityDarkSpiderTest)entityliving;
        float f1 = entityDarkSpider.squishe + (entityDarkSpider.squishb - entityDarkSpider.squishe) * f;
        float f2 = entityDarkSpider.squishd + (entityDarkSpider.squishc - entityDarkSpider.squishd) * f;
        return (MathHelper.sin(f1) + 0.1F) * f2;
    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
    {
    	GL11.glScalef(2F, 2F, 2F);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityDarkSpiderTest)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityDarkSpiderTest)p_110775_1_);
    }
}