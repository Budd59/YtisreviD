package diversity.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelWarriorSkeleton;
import diversity.entity.EntityWarriorSkeleton;
import diversity.utils.ResourceTools;

@SideOnly(Side.CLIENT)
public class RenderWarriorSkeleton extends RenderLiving
{	
	private ModelWarriorSkeleton warriorSkeletonModel;

	public RenderWarriorSkeleton() {
		super(new ModelWarriorSkeleton(0.0F), 0.5F);  
        this.warriorSkeletonModel = (ModelWarriorSkeleton)this.mainModel;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return ResourceTools.getResource(EntityWarriorSkeleton.class);
	}
}