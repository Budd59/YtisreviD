package diversity.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.client.render.block.RenderBlockFrozenChest;
import diversity.suppliers.EnumBlock;
import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockFrozenChest extends BlockChest {
	
	public BlockFrozenChest(int p_i45397_1_) {
		super(p_i45397_1_);
	}
	
    /**
     * The type of render function that is called for this block
     */
	@Override
    public int getRenderType()
    {
        return EnumBlock.frozen_chest.renderId;
    }
	
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	if (world.getSavedLightValue(EnumSkyBlock.Block, p_149727_2_, p_149727_3_, p_149727_4_) > 11 - this.getLightOpacity()) {
	            IInventory iinventory = this.func_149951_m(world, p_149727_2_, p_149727_3_, p_149727_4_);
	            if (iinventory != null)
	            {
	            	player.displayGUIChest(iinventory);
	            }	            
        	}
        	return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        TileEntityChest tileentitychest = new TileEntityFrozenChest();
        return tileentitychest;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("ice");
    }
}
