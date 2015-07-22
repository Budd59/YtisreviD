package diversity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBones extends Block {

	public BlockBones() {
		super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}
}
