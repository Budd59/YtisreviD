package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelZulu;

@SideOnly(Side.CLIENT)
public class RenderZulu extends RenderGlobalVillager
{	
    /** Model of the villager. */
    protected ModelZulu villagerModel;
    
	public RenderZulu() {
		super(new ModelZulu(0.0F), 0.5F);  
        this.villagerModel = (ModelZulu)this.mainModel;
	}
}
