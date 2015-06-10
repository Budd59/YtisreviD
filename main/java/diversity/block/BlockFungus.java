package diversity.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockFungus extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon side;
	
	public BlockFungus()
    {
        super(Material.grass);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
	    this.side = par1IconRegister.registerIcon(getTextureName()+"_side");
	    this.blockIcon = par1IconRegister.registerIcon(getTextureName());     
	}

	public IIcon getIcon(int i, int k) 
	{
		if(i == 1 || i == 0) 
		{
			return this.blockIcon; //return top texture
		}
		else{
			return this.side; // return side texture
		}
	}
	
	public int quantityDropped(Random par1Random)
	{
		return 5 + par1Random.nextInt(5);
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return 0;//mod_Shroom.sporesItem.itemID;
	}
}