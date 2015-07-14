package diversity.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.block.TileEntityFrozenChest;
import diversity.suppliers.EnumBlock;

@SideOnly(Side.CLIENT)
public class RenderBlockFrozenChest implements ISimpleBlockRenderingHandler
{

	private final TileEntityFrozenChest frozenChest = new TileEntityFrozenChest();
	
	public static final int modelId = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (block.equals(EnumBlock.frozen_chest.block)) {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(this.frozenChest, 0.0D, 0.0D, 0.0D, 0.0F);
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return modelId;
	}

}
