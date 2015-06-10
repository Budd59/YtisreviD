package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelSettled;

@SideOnly(Side.CLIENT)
public class RenderSettled extends RenderGlobalVillager
{
    /** Model of the villager. */
    protected ModelSettled villagerModel;
    
	public RenderSettled() {
		super(new ModelSettled(0.0F), 0.5F);  
        this.villagerModel = (ModelSettled)this.mainModel;
	}
}