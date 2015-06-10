package diversity.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.suppliers.EnumBlock;
import diversity.world.WorldGenPhosMushroom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPhosMushroom extends BlockBush implements IGrowable
{
	private int metadata = 0;

	public BlockPhosMushroom()
	{
		super(Material.plants);
		float f = 0.5F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(getTextureName());
	}

//	@Override
//	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
//		super.onBlockPlacedBy(world,i,j,k,par5EntityLiving,par6ItemStack);
//		if(world.getBlock(i,j-1,k) == EnumBlock.phos_mycelium.block){
//			world.setBlock(i,j+1,k, EnumBlock.phos_mushroom.block);
//		}
//	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
	{

		return null;
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

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		checkFlowerChange(par1World, par2, par3, par4);
	}

	protected final void checkFlowerChange(World par1World, int par2, int par3, int par4)
	{
		if (!canBlockStay(par1World, par2, par3, par4) || par1World.getBlock(par2, par3 - 1, par4) != EnumBlock.fungal.block)
		{
			dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if(par1World.canBlockSeeTheSky(par2, par3, par4)) 
			return;

		if (par5Random.nextInt(100) == 0)
		{
			byte byte0 = 4;
			int i = 5;
	
			for (int j = par2 - byte0; j <= par2 + byte0; j++)
			{
				for (int l = par4 - byte0; l <= par4 + byte0; l++)
				{
					for (int j1 = par3 - 1; j1 <= par3 + 1; j1++)
					{
						if (par1World.getBlock(j, j1, l) == this && --i <= 0)
						{
							return;
						}
					}
				}
				int k = (par2 + par5Random.nextInt(3)) - 1;
				int i1 = (par3 + par5Random.nextInt(2)) - par5Random.nextInt(2);
				int k1 = (par4 + par5Random.nextInt(3)) - 1;
	
				for (int l1 = 0; l1 < 4; l1++)
				{
					if (par1World.isAirBlock(k, i1, k1) && canBlockStay(par1World, k, i1, k1))
					{
						par2 = k;
						par3 = i1;
						par4 = k1;
					}
	
					k = (par2 + par5Random.nextInt(3)) - 1;
					i1 = (par3 + par5Random.nextInt(2)) - par5Random.nextInt(2);
					k1 = (par4 + par5Random.nextInt(3)) - 1;
				}
	
				if (par1World.isAirBlock(k, i1, k1) && canBlockStay(par1World, k, i1, k1) && par5Random.nextInt(5) == 0)
				{
					par1World.setBlock(k, i1, k1, this);
				}
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if(world.getBlock(x,y+1,z) == Blocks.air && world.getBlock(x,y-1,z) == EnumBlock.fungal.block)
			return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
		else
			return false;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (y < 0 || y >= 256)
		{
			return false;
		}
		else
		{
            Block block = world.getBlock(x, y-1, z);
			return !world.canBlockSeeTheSky(x, y, z) && world.getBlock(x, y - 1, z) == EnumBlock.fungal.block && block.canSustainPlant(world, x, y, z, ForgeDirection.UP, this);
		}
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Block.getIdFromBlock(EnumBlock.phos_mushroom.block);
	}

	public boolean fertilizeMushroom(World world, int x, int y, int z, Random random)
	{
		world.setBlockToAir(x, y, z);

		WorldGenPhosMushroom worldgenbigmushroom = new WorldGenPhosMushroom();

		if (worldgenbigmushroom == null || !worldgenbigmushroom.generate(world, random, x, y-1, z))
		{
			world.setBlock(x, y, z, EnumBlock.phos_mushroom.block);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return true;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return (double)p_149852_2_.nextFloat() < 0.4D;
	}

	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
        this.fertilizeMushroom(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
	}
	
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
    	return EnumPlantType.Cave;
    }
}