package diversity.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockFrozenChest extends BlockChest {

	public BlockFrozenChest(int p_i45397_1_) {
		super(p_i45397_1_);
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
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(getTextureName());
	}
}
