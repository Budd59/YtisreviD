package diversity.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.model.ModelEgyptian;

@SideOnly(Side.CLIENT)
public class RenderEgyptian extends RenderGlobalVillager
{
    /** Model of the villager. */
    protected ModelEgyptian villagerModel;
    
	public RenderEgyptian() {
		super(new ModelEgyptian(0.0F), 0.5F);  
        this.villagerModel = (ModelEgyptian)this.mainModel;
	}
}