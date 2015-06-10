package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelApache;

@SideOnly(Side.CLIENT)
public class RenderApache extends RenderGlobalVillager
{
    /** Model of the villager. */
    protected ModelApache villagerModel;
    
	public RenderApache() {
		super(new ModelApache(0.0F), 0.5F);  
        this.villagerModel = (ModelApache)this.mainModel;
	}
}
