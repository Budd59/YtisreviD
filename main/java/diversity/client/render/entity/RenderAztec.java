package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelAztec;

@SideOnly(Side.CLIENT)
public class RenderAztec extends RenderGlobalVillager
{
    /** Model of the villager. */
    protected ModelAztec villagerModel;
    
	public RenderAztec() {
		super(new ModelAztec(0.0F), 0.5F);  
        this.villagerModel = (ModelAztec)this.mainModel;
	}
}