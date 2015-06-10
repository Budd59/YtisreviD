package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelLakeside;

@SideOnly(Side.CLIENT)
public class RenderLakeside extends RenderGlobalVillager
{
    /** Model of the villager. */
    protected ModelLakeside villagerModel;
    
	public RenderLakeside() {
		super(new ModelLakeside(0.0F), 0.5F);  
        this.villagerModel = (ModelLakeside)this.mainModel;
	}
}
