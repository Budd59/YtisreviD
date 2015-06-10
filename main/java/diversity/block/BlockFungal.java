package diversity.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.suppliers.EnumBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFungal extends Block
{	
    @SideOnly(Side.CLIENT)
    private IIcon top;
    @SideOnly(Side.CLIENT)
    private IIcon snow;

    public BlockFungal()
    {
        super(Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.top : (p_149691_1_ == 0 ? Blocks.dirt.getBlockTextureFromSide(p_149691_1_) : this.blockIcon);
    }
	
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (y>55 || world.canBlockSeeTheSky(x, y + 1, z) || world.getBlockLightOpacity(x, y + 1, z) > 2)
		{
			world.setBlock(x, y, z, Blocks.dirt, 0, 3);
		}
		else
		{
			for (int i = 0; i < 4; i++)
			{
				int tempX = (x + random.nextInt(3)) - 1;
				int tempY = (y + random.nextInt(5)) - 3;
				int tempZ = (z + random.nextInt(3)) - 1;
				Block block = world.getBlock(tempX, tempY + 1, tempZ);
				if (world.getBlock(tempX, tempY, tempZ).equals(Blocks.dirt) && !world.canBlockSeeTheSky(x, y + 1, z) && world.getBlockLightOpacity(tempX, tempY + 1, tempZ) <= 2)
				{
					world.setBlock(tempX, tempY, tempZ, EnumBlock.fungal.block,0,3);
				}
			}
		}
	}
	
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Blocks.dirt.getItemDropped(0, p_149650_2_, p_149650_3_);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int type)
    {
        if (type == 1)
        {
            return this.top;
        }
        else if (type == 0)
        {
            return Blocks.dirt.getBlockTextureFromSide(type);
        }
        else
        {
            Material material = world.getBlock(x, y + 1, z).getMaterial();
            return !material.equals(Material.snow) && !material.equals(Material.craftedSnow) ? this.blockIcon : this.snow;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.top = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.snow = iconRegister.registerIcon("grass_side_snowed");
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        super.randomDisplayTick(world, x, y, z, random);

        if (random.nextInt(10) == 0)
        {
            world.spawnParticle("townaura", (double)((float)x + random.nextFloat()), (double)((float)y + 1.1F), (double)((float)z + random.nextFloat()), 0.0D, 0.0D, 0.0D);
        }
    }
}
