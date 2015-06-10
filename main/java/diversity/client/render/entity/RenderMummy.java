package diversity.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelMummy;
import diversity.entity.EntityMummy;
import diversity.utils.ResourceTools;

@SideOnly(Side.CLIENT)
public class RenderMummy extends RenderLiving
{	
	private ModelMummy mummyModel;

	public RenderMummy() {
		super(new ModelMummy(0.0F), 0.5F);  
        this.mummyModel = (ModelMummy)this.mainModel;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return ResourceTools.getResource(EntityMummy.class);
	}
}
