package diversity.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelAztec extends ModelGlobalVillager
{
	public ModelAztec(float p_i1163_1_)
	{
		this(p_i1163_1_, 0.0F, 64, 64);
	}
	
	public ModelAztec(float p_i1164_1_, float p_i1164_2_, int p_i1164_3_, int p_i1164_4_)
	{
		super(p_i1164_1_, p_i1164_2_, p_i1164_3_, p_i1164_4_);
        this.bipedHead.setTextureOffset(32, -2).addBox(-4.0F, -22.0F, -4.0F, 8, 14, 8, p_i1164_1_ + 0.5f);
	}
}