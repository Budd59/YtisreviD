package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelInuit;

@SideOnly(Side.CLIENT)
public class RenderInuit extends RenderGlobalVillager
{
    /** Model of the villager. */
    protected ModelInuit villagerModel;
    
	public RenderInuit() {
		super(new ModelInuit(0.0F), 0.5F);  
        this.villagerModel = (ModelInuit)this.mainModel;
	}
}
