package diversity.suppliers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import diversity.Diversity;
import diversity.block.BlockBlueMushroom;
import diversity.block.BlockBlueVine;
import diversity.block.BlockFrozenChest;
import diversity.block.BlockFungus;
import diversity.block.BlockMushroomCap;
import diversity.block.BlockPhosMushroom;
import diversity.block.BlockFungal;
import diversity.block.BlockPhosWater;
import diversity.block.BlockPoisonWater;
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
	frozen_chest (new BlockFrozenChest(0).setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("frozen_chest").setBlockTextureName(Diversity.MODID+":frozen_chest"));

	public final Block block;
	public final Class blockClass;
	public final String resourcePath;
	
	private EnumBlock (Block block) {
		this.block = block;
		this.blockClass = block.getClass();
		this.resourcePath = PathTool.blockTexturePath + name().toLowerCase() + PathTool.ext;
	}
	
	public static void register() {
		for (EnumBlock enumBlock : EnumBlock.values()) {
			Diversity.proxy.registerBlockRenderer(enumBlock);
			Diversity.proxy.registerBlockRessource(enumBlock);
			GameRegistry.registerBlock(enumBlock.block, enumBlock.name());
		}
	}
}