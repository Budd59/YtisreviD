package diversity.client.render.block;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.block.BlockFrozenChest;
import diversity.block.TileEntityFrozenChest;
import diversity.suppliers.EnumBlock;

@SideOnly(Side.CLIENT)
public class RenderBlockFrozenChest implements ISimpleBlockRenderingHandler
{	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if (block.equals(EnumBlock.frozen_chest.block))
		{
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
	        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	        TileEntityRendererDispatcher.instance.renderTileEntityAt(EnumBlock.frozen_chest.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	    }
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (block.equals(EnumBlock.frozen_chest.block))
		{
            TileEntityRendererDispatcher.instance.renderTileEntityAt(EnumBlock.frozen_chest.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
            return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
        return EnumBlock.frozen_chest.renderId;
	}
}
