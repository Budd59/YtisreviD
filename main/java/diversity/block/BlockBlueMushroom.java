package diversity.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.suppliers.EnumBlock;
import diversity.world.WorldGenBlueMushroom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBlueMushroom extends BlockBush implements IGrowable
{
	private int metadata = 0;

	public BlockBlueMushroom()
	{
		super(Material.plants);
		float f = 0.3F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		setTickRandomly(true);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
	{
		return null;
	}

	public int tickRate(World par1World)
	{
		return 10;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return 1;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		super.onNeighborBlockChange(world, x, y, z, block);
		checkFlowerChange(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(getTextureName());
	}

	protected final void checkFlowerChange(World world, int x, int y, int z)
	{
		if (!canBlockStay(world, x, y, z))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}


	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if(world.canBlockSeeTheSky(x, y, z)) 
			return;

		if (random.nextInt(25) == 0)
		{
			byte b0 = 4;
			int l = 5;
			int tempX;
			int tempZ;
			int tempY;

			for (tempX = x - b0; tempX <= x + b0; ++tempX)
			{
				for (tempZ = z - b0; tempZ <= z + b0; ++tempZ)
				{
					for (tempY = y - 1; tempY <= y + 1; ++tempY)
					{
						if (world.getBlock(tempX, tempY, tempZ) == this)
						{
							--l;

							if (l <= 0)
							{
								return;
							}
						}
					}
				}
			}

			tempX = x + random.nextInt(3) - 1;
			tempZ = y + random.nextInt(2) - random.nextInt(2);
			tempY = z + random.nextInt(3) - 1;

			for (int l1 = 0; l1 < 4; ++l1)
			{
				if (world.isAirBlock(tempX, tempZ, tempY) && this.canBlockStay(world, tempX, tempZ, tempY))
				{
					x = tempX;
					y = tempZ;
					z = tempY;
				}

				tempX = x + random.nextInt(3) - 1;
				tempZ = y + random.nextInt(2) - random.nextInt(2);
				tempY = z + random.nextInt(3) - 1;
			}

			if (world.isAirBlock(tempX, tempZ, tempY) && this.canBlockStay(world, tempX, tempZ, tempY))
			{
				world.setBlock(tempX, tempZ, tempY, this);
			}
		}
	}

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        if (y >= 0 && y < 256)
        {
            Block block = world.getBlock(x, y - 1, z);
            return !world.canBlockSeeTheSky(x, y, z) && block.equals(EnumBlock.fungal.block) && block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
        }
        else
        {
            return false;
        }
    }
     
	public boolean fertilizeMushroom(World world, int x, int y, int z, Random random)
	{
		int i = world.getBlockMetadata(x, y, z);

		WorldGenerator worldgenbigmushroom = new WorldGenBlueMushroom();

		if(!worldgenbigmushroom.generate(world, random, x, y, z)){
			return false;
		}
		else
		{
			return true;
		}

	}

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return (double)p_149852_2_.nextFloat() < 0.4D;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.fertilizeMushroom(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
    	return EnumPlantType.Cave;
    }
}
