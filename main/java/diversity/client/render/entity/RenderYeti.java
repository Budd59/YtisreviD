package diversity.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelYeti;
import diversity.entity.EntityYeti;
import diversity.utils.ResourceTools;

@SideOnly(Side.CLIENT)
public class RenderYeti extends RenderLiving
{	
	private ModelYeti warriorSkeletonModel;

	public RenderYeti() {
		super(new ModelYeti(), 0.5F);  
        this.warriorSkeletonModel = (ModelYeti)this.mainModel;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return ResourceTools.getResource(EntityYeti.class);
	}
}

