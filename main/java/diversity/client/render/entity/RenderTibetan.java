package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelTibetan;

@SideOnly(Side.CLIENT)
public class RenderTibetan extends RenderGlobalVillager
{
    /** Model of the villager. */
    protected ModelTibetan villagerModel;
    
	public RenderTibetan() {
		super(new ModelTibetan(0.0F), 0.5F);  
        this.villagerModel = (ModelTibetan)this.mainModel;
	}
}
