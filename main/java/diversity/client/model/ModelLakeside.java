package diversity.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLakeside extends ModelGlobalVillager
{
	public ModelLakeside(float p_i1163_1_)
	{
		this(p_i1163_1_, 0.0F, 64, 64);
	}
	
	public ModelLakeside(float p_i1164_1_, float p_i1164_2_, int p_i1164_3_, int p_i1164_4_)
	{
		super(p_i1164_1_, p_i1164_2_, p_i1164_3_, p_i1164_4_);
        this.bipedHead.setTextureOffset(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 7, 8, p_i1164_1_ + 0.5f);
        this.bipedHead.setTextureOffset(32, 15).addBox(-4.0F, -2.0F, -4.0F, 8, 4, 1, p_i1164_1_ + 0.3f);
	}
}