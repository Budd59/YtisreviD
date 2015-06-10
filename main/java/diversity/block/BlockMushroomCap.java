package diversity.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.Diversity;
import diversity.suppliers.EnumBlock;

public class BlockMushroomCap extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon capOUT;
	@SideOnly(Side.CLIENT)
	private IIcon capIN;

	public BlockMushroomCap(Material material)
	{
		super(material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(getTextureName()+"_sterm_side");
		this.capIN = par1IconRegister.registerIcon(getTextureName()+"_cap_in");
		this.capOUT = par1IconRegister.registerIcon(getTextureName()+"_cap_out");
	}

	@Override
	public IIcon getIcon(int par1, int par2)
	{
		if (par2 == 11 && par1 > 0)
		{
			return this.capOUT;
		}
		if (par2 == 10 && par1 > 1)
		{
			return  this.blockIcon;
		}

		if (par2 >= 1 && par2 <= 9 && par1 == 1)
		{
			return this.capOUT;
		}

		if (par2 >= 1 && par2 <= 3 && par1 == 2)
		{
			return this.capOUT;
		}

		if (par2 >= 7 && par2 <= 9 && par1 == 3)
		{
			return this.capOUT;
		}

		if ((par2 == 1 || par2 == 4 || par2 == 7) && par1 == 4)
		{
			return this.capOUT;
		}

		if ((par2 == 3 || par2 == 6 || par2 == 9) && par1 == 5)
		{
			return this.capOUT;
		}

		if (par2 == 14)
		{
			return this.capOUT;
		}

		if (par2 == 15)
		{
			return  this.blockIcon;
		}

		else
		{
			return this.capIN;
		}
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
        int i = par1Random.nextInt(10) - 7;

        if (i < 0)
        {
            i = 0;
        }

        return i;
	}
	
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemById(Block.getIdFromBlock(EnumBlock.blue_mushroom.block));
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemById(Block.getIdFromBlock(EnumBlock.blue_mushroom.block));
    }
    
    @Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		if(par1World.getBlock(par2,par3+1,par4) == Blocks.air && par1World.getBlock(par2,par3-1,par4) == EnumBlock.fungal.block)
			return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
		else
			return false;
	}
}

