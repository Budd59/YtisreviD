package diversity.suppliers;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import diversity.Diversity;
import diversity.block.BlockBlueMushroom;
import diversity.block.BlockBlueVine;
import diversity.block.BlockBones;
import diversity.block.BlockFrozenChest;
import diversity.block.BlockFungus;
import diversity.block.BlockMushroomCap;
import diversity.block.BlockPhosMushroom;
import diversity.block.BlockFungal;
import diversity.block.BlockPhosWater;
import diversity.block.BlockPoisonWater;
import diversity.block.TileEntityFrozenChest;
import diversity.utils.PathTool;

public enum EnumBlock
{
	phos_water (new BlockPhosWater().setBlockName("phos_water").setBlockTextureName(Diversity.MODID+":phos_water").setLightOpacity(3).setLightLevel(0.8f)),
	poison_water (new BlockPoisonWater().setBlockName("poison_water").setBlockTextureName(Diversity.MODID+":poison_water").setLightOpacity(3)),
	fungal (new BlockFungal().setHardness(0.6F).setStepSound(Block.soundTypeGrass).setBlockName("fungal").setBlockTextureName(Diversity.MODID+":fungal")),
	blue_mushroom (new BlockBlueMushroom().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("blue_mushroom").setBlockTextureName(Diversity.MODID+":blue_mushroom")),
	blue_mushroom_cap (new BlockMushroomCap(Material.wood).setStepSound(Block.soundTypeWood).setBlockName("blue_mushroom").setBlockTextureName(Diversity.MODID+":blue_mushroom")),
	phos_mushroom (new BlockPhosMushroom().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setLightLevel(0.6F).setBlockName("phos_mushroom").setBlockTextureName(Diversity.MODID+":phos_mushroom")),
	phos_mushroom_cap (new BlockMushroomCap(Material.wood).setStepSound(Block.soundTypeWood).setBlockName("phos_mushroom").setBlockTextureName(Diversity.MODID+":phos_mushroom")),
	blue_vine (new BlockBlueVine().setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockName("blue_vine").setBlockTextureName(Diversity.MODID+":blue_vine")),
	fungus (new BlockFungus().setStepSound(Block.soundTypeWood).setLightLevel(1.0F).setBlockName("fungus").setBlockTextureName(Diversity.MODID+":fungus")),
	bones (new BlockBones().setHardness(2.0F).setStepSound(Block.soundTypeGravel).setBlockName("bones").setBlockTextureName(Diversity.MODID+":bones")),
	frozen_chest (new BlockFrozenChest(0).setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("frozen_chest").setBlockTextureName(Diversity.MODID+":frozen_chest"), new TileEntityFrozenChest());

	public final Block block;
	public final Class blockClass;
	public final String resourcePath;
	public final Class tileEntityClass;
	public final TileEntity tileEntity;
	public final int renderId;
	
	private EnumBlock (Block block) {
		this(block, null);
	}
	
	private EnumBlock (Block block, TileEntity tileEntity) {
		this.block = block;
		this.blockClass = block.getClass();
		this.resourcePath = PathTool.blockTexturePath + name().toLowerCase() + PathTool.ext;
		if (tileEntity != null) {
			this.tileEntity = tileEntity;
			this.tileEntityClass = tileEntity.getClass();
		}
		else
		{
			this.tileEntity = null;
			this.tileEntityClass = null;
		}
		this.renderId = RenderingRegistry.getNextAvailableRenderId();
	}
	
	public static void register() {
		for (EnumBlock enumBlock : EnumBlock.values()) {
			Diversity.proxy.registerBlockRenderer(enumBlock);
			Diversity.proxy.registerBlockRessource(enumBlock);
			GameRegistry.registerBlock(enumBlock.block, enumBlock.name());
			if (enumBlock.tileEntityClass != null) {
				Diversity.proxy.registerTileEntityRenderer(enumBlock);
				GameRegistry.registerTileEntity(enumBlock.tileEntityClass, enumBlock.name());
			}
		}
	}
}