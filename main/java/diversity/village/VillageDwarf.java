package diversity.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import diversity.entity.EntityAztec;
import diversity.suppliers.EnumVillage;
import diversity.suppliers.EnumVillageBasicPiece;
import diversity.suppliers.EnumVillagePiece;
import diversity.suppliers.EnumVillager;
import diversity.village.VillageAztec.Field;
import diversity.village.VillageAztec.House1;
import diversity.village.VillageAztec.House2;
import diversity.village.VillageAztec.Livestock;
import diversity.village.VillageAztec.Palace;
import diversity.village.VillageAztec.Path;
import diversity.village.VillageAztec.Start;
import diversity.village.VillageAztec.Temple;
import diversity.village.VillageAztec.Torch;
import diversity.village.VillageTools.BlockData;
import diversity.village.VillageTools.GlobalField;
import diversity.village.VillageTools.GlobalPath;
import diversity.village.VillageTools.GlobalStart;
import diversity.village.VillageTools.GlobalTorch;
import diversity.village.VillageTools.GlobalVillage;

public class VillageDwarf extends VillageTools
{	
 	private static VillageDwarf instance;
 	
 	public VillageDwarf(EnumVillage ENUM) {
 		super(ENUM);
 		instance = this;
 	}
 	
 	@Override
 	protected GlobalPath getPath(GlobalStart p_75080_0_, int p_75080_7_, Random p_75080_2_, StructureBoundingBox structureboundingbox, int p_75080_6_) {
 		return new Path(p_75080_0_, p_75080_7_, p_75080_2_, structureboundingbox, p_75080_6_);
 	}
     
 	@Override
 	public GlobalStart getStart(WorldChunkManager worldChunkManager, int i, Random rand, int j, int k, List list, int numberOfVillagers) {
 		return new Start(worldChunkManager, i, rand, j, k, list, numberOfVillagers);
 	}

 	@Override
 	protected GlobalTorch getTorch(GlobalStart villagePiece, int par2, Random rand, StructureBoundingBox boundingBox, int coordBaseMode) {
 		return new VillageAztec.Torch(villagePiece, par2, rand, boundingBox, coordBaseMode);
 	}
     
 	public static class Start extends GlobalStart
 	{
		public Start() {}

		public Start(WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_)
		{
			super(instance, p_i2104_1_, p_i2104_2_, p_i2104_3_, p_i2104_4_, p_i2104_5_, p_i2104_6_, p_i2104_7_);
			switch (this.coordBaseMode)
			{
				case 0:
				case 2:
					this.boundingBox = new StructureBoundingBox(p_i2104_4_-3, 30, p_i2104_5_-3, p_i2104_4_ + 3 - 1, 35, p_i2104_5_ + 3 - 1);
					break;
				default:
					this.boundingBox = new StructureBoundingBox(p_i2104_4_-3, 30, p_i2104_5_-3, p_i2104_4_ + 3 - 1, 35, p_i2104_5_ + 3 - 1);
			}
		}
		         
     /**
      * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
      * Mineshafts at the end, it adds Fences...
      */
     @Override
     public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
     {
	     for (int x = 0; x < 6; ++x)
	        {
	            for (int z = 0; z < 6; ++z)
	            {
	                this.clearCurrentPositionBlocksUpwards(world, x, 1, z, structureBoundingBox);
	            }
	        }
	         
	        this.fillWithBlocks(world, structureBoundingBox,  0, 0, 0, 5, 0, 5, Blocks.stonebrick, Blocks.stonebrick, false);            
	        this.fillWithBlocks(world, structureBoundingBox,  2, 0, 2, 3, 0, 3, Blocks.water, Blocks.water, false);
	         
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 1, 1, 1, structureBoundingBox);
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 1, 1, 4, structureBoundingBox);
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 4, 1, 1, structureBoundingBox);
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 4, 1, 4, structureBoundingBox);
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 1, 2, 1, structureBoundingBox);
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 1, 2, 4, structureBoundingBox);
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 4, 2, 1, structureBoundingBox);
	        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 4, 2, 4, structureBoundingBox);
	         
	        for (int k = 1; k < 4; k ++) {
	         	this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, this.getMetadataWithOffset(Blocks.stone_brick_stairs, 3), k, 3, 1, structureBoundingBox);
	         	this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, this.getMetadataWithOffset(Blocks.stone_brick_stairs, 0), 1, 3, k+1, structureBoundingBox);
	         	this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, this.getMetadataWithOffset(Blocks.stone_brick_stairs, 2), k+1, 3, 4, structureBoundingBox);
	         	this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, this.getMetadataWithOffset(Blocks.stone_brick_stairs, 1), 4, 3, k, structureBoundingBox);
	        }
	
	        return true;
	    }
	}
 	
    public static class Path extends GlobalPath
    {
    	public Path() {}

    	public Path(GlobalStart p_i2105_1_, int p_i2105_2_, Random p_i2105_3_, StructureBoundingBox p_i2105_4_, int p_i2105_5_)
        {
            super(instance, p_i2105_1_, p_i2105_2_, p_i2105_3_, p_i2105_4_, p_i2105_5_);
        }
    	
		/**
		 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
		 * Mineshafts at the end, it adds Fences...
		 */
		@Override
		public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
		{			
            for (int x = this.boundingBox.minX; x <= this.boundingBox.maxX; x++)
            {
                for (int z = this.boundingBox.minZ; z <= this.boundingBox.maxZ; z++)
                {
                    if (structureBoundingBox.isVecInside(x, 64, z))
                    {
                        int y = 30;
                        points.put(x, z, y);
        				BlockData blockData = getPathBlock(random);
        				world.setBlock(x, y, z, blockData.block, blockData.metaData, 2);
                    }
                }
            }
            return true;
		}
    	
		@Override
		protected BlockData getPathBlock(Random random) {
			return new BlockData(Blocks.stonebrick, 0);
		}

		@Override
		protected BlockData getPathBridge(Random random) {
			return new BlockData(Blocks.stonebrick, 0);
		}

		@Override
		protected BlockData getUnderPathBlock(Random random) {
			return new BlockData(Blocks.stonebrick, 0);
		}
    }
}
