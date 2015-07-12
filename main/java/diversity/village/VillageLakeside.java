package diversity.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import diversity.entity.EntityLakeside;
import diversity.suppliers.EnumVillageBasicPiece;
import diversity.suppliers.EnumVillagePiece;
import diversity.suppliers.EnumVillage;
import diversity.suppliers.EnumVillager;
import diversity.utils.DirectionTools;

public class VillageLakeside extends VillageTools
{
	private static VillageLakeside instance;

	public VillageLakeside(EnumVillage ENUM) {
		super(ENUM);
		instance = this;
	}
    
	@Override
	public GlobalStart getStart(WorldChunkManager worldChunkManager, int i, Random rand, int j, int k, List list, int numberOfVillagers)
	{
		return new Start(worldChunkManager, i, rand, j, k, list, numberOfVillagers);
	}

	@Override
	protected GlobalTorch getTorch(GlobalStart villagePiece, int par2, Random rand, StructureBoundingBox boundingBox, int coordBaseMode)
	{
		return new Torch(villagePiece, par2, rand, boundingBox, coordBaseMode);
	}
	

	@Override
	protected GlobalPath getPath(GlobalStart p_75080_0_, int p_75080_7_, Random p_75080_2_, StructureBoundingBox structureboundingbox, int p_75080_6_)
	{
		return new Path(p_75080_0_, p_75080_7_, p_75080_2_, structureboundingbox, p_75080_6_);
	}
	
	public static class Start extends GlobalStart
    {
        public Start() {}

        public Start(WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_)
        {
            super(instance, p_i2104_1_, p_i2104_2_, p_i2104_3_, p_i2104_4_, p_i2104_5_, p_i2104_6_, p_i2104_7_);
            coordBaseMode = 2;
        }
        
        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
        	if (super.addComponentParts(world, rand, structureBoundingBox, 2))
        		return true;

            for (int x = 0; x <= 5; ++x)
            {
            	for (int z = 0; z <= 5; ++z)
            	{
                     this.clearCurrentPositionBlocksUpwards(world, x, 11, z, structureBoundingBox);
                }
            }
            
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 12, 2, 4, 12, 3, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 12, 1, 3, 12, 4, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
            
        	this.func_151554_b(world, Blocks.log, 0, 0, 13, 0, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 0, 13, 5, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 5, 13, 0, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 5, 13, 5, structureBoundingBox);

        	this.func_151554_b(world, Blocks.log, 0, 1, 15, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 1, 15, 4, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 4, 15, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 4, 15, 4, structureBoundingBox);
        	
            this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 0, 14, 0, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 0, 14, 5, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 14, 0, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 14, 5, structureBoundingBox);
            
            for (int k = 1; k < 5; k++) {
                this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 0, 12, k, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 5, 12, k, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], k, 12, 0, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], k, 12, 5, structureBoundingBox);
                
                if (k == 2 || k == 3) {
                    this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], k, 15, 1, structureBoundingBox);
                    this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], k, 15, 4, structureBoundingBox);
                    this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 15, k, structureBoundingBox);
                    this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 4, 15, k, structureBoundingBox);
                }
            }

			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 15, 0, 0, 15, 4, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 15, 5, 4, 15, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 15, 1, 5, 15, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 15, 0, 5, 15, 0, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 16, 1, 4, 16, 4, Blocks.wooden_slab, 0, Blocks.wooden_slab, 0, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 16, 2, 3, 16, 3, Blocks.wooden_slab, 8, Blocks.wooden_slab, 8, false);
			
        	
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
		
		@Override
		protected BlockData getPathBlock(Random random) {
			return new BlockData(Blocks.gravel, 0);
		}
		
		@Override
		public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
		{			
            for (int x = this.boundingBox.minX; x <= this.boundingBox.maxX; x++)
            {
                for (int z = this.boundingBox.minZ; z <= this.boundingBox.maxZ; z++)
                {
                    if (structureBoundingBox.isVecInside(x, 64, z))
                    {
                        int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
                        if (y < 64) {
                        	boolean isSupport = false;
                        	switch(coordBaseMode){
                        	case 1:
                        	case 3:
                        		isSupport = x%4 == 0 && (z == this.boundingBox.minZ || z == this.boundingBox.maxZ) && random.nextBoolean();
                        		break;
                         	case 0:
                        	case 2:
                        		isSupport = z%4 == 0 && (x == this.boundingBox.minX || x == this.boundingBox.maxX) && random.nextBoolean();
                        	}
                        	if (isSupport) {
                        		y = 64;
                        		while (isSupport) {
                        			if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isLiquid()) {
                        				world.setBlock(x, y, z, Blocks.log, 0, 2);
                        			} else {
                        				isSupport = false;
                        			}
                        			y = y-1;
                        			if (y == 50) {
                        				isSupport = false;
                        			}
                        		}
                        	} else {
                        		world.setBlock(x, 64, z, Blocks.wooden_slab, 9, 2);
                        	}
                        } else {
	        				BlockData blockData = getPathBlock(random);
	        				world.setBlock(x, y, z, blockData.block, blockData.metaData, 2);
                        }
                    }
                }
            }
			return true;
		}
    }
    
	public static class Torch  extends GlobalTorch
	{		
		public Torch() {}

		public Torch(StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
		{
			super(EnumVillageBasicPiece.LAKE_TORCH, startPiece, componentType, random, structureBoundingBox, coordBaseMode);
			setOffset(3);
		}

	       /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
		@Override
        public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
        {
			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, -2, 0, 2, -2, 1, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 1, 1, -2, 1, structureBoundingBox);
			this.func_151554_b(world, Blocks.log, 0, 0, 1, 0, structureBoundingBox);
    		for (int k = -1; k<2; k++) {
        		this.placeBlockAtCurrentPosition(world, Blocks.ladder, this.getMetadataWithOffset(Blocks.ladder, 4), 0, k, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.ladder, this.getMetadataWithOffset(Blocks.ladder, 2), 2, k, 1, structureBoundingBox);
    		}
    		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1, 2, 1, structureBoundingBox);
            return true;
        }
    }
	
	public static class House1 extends GlobalVillage
	{
		public House1() {}
		
 		public House1(StructureVillagePieces.Start villagePiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(EnumVillagePiece.LAKESIDE_HOUSE1, villagePiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(3);
 		}

		public static House1 buildComponent( StructureVillagePieces.Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 7, 4, 6, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new House1(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
            for (int x = 0; x < 7; ++x)
            {
                for (int z = 0; z < 7; ++z)
                {
                    this.clearCurrentPositionBlocksUpwards(world, x, -1, z, structureBoundingBox);
                }
            }
            
        	this.func_151554_b(world, Blocks.log, 0, 1, 3, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 5, 3, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 1, 3, 5, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 5, 3, 5, structureBoundingBox);
        	
        	for (int k = 0; k < 3; k++) {
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 0, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 0, 5, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 3, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 3, 5, structureBoundingBox);
        		
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 0, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 5, 0, 2+k, structureBoundingBox);
           		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 3, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 5, 3, 2+k, structureBoundingBox);
        	}
        	
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 1, 1, 4, 2, 1, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 1, 5, 4, 2, 5, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 1, 2, 1, 2, 4, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 1, 2, 5, 2, 4, Blocks.planks, 1, Blocks.planks, 1, false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 0, 2, 4, 0, 4, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 0, 0, 5, 0, 0, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 3, 0, 0, 3, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 3, 6, 5, 3, 6, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 6, 3, 1, 6, 3, 6, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 3, 0, 6, 3, 0, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 4, 1, 5, 4, 5, Blocks.wooden_slab, 0, Blocks.wooden_slab, 0, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 4, 2, 4, 4, 4, Blocks.wooden_slab, 8, Blocks.wooden_slab, 8, false);
			
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 2, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 3, 2, 5, structureBoundingBox);
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 3, 1, 1, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 3, 3, 2, structureBoundingBox);
			
			this.spawnEntity(world, structureBoundingBox, 3, 1, 3, 0);
			this.spawnEntity(world, structureBoundingBox, 3, 1, 3, 0);
			
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityLakeside(world, EnumVillager.LAKESIDE_FISHERMAN);
		}
	}
	
	public static class House2 extends GlobalVillage
	{
		public House2() {}
		
 		public House2(StructureVillagePieces.Start villagePiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(EnumVillagePiece.LAKESIDE_HOUSE2, villagePiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(4);
 		}

		public static House2 buildComponent( StructureVillagePieces.Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 8, 4, 6, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new House2(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
            for (int x = 0; x < 8; ++x)
            {
                for (int z = 0; z < 7; ++z)
                {
                    this.clearCurrentPositionBlocksUpwards(world, x, -1, z, structureBoundingBox);
                }
            }
            
        	this.func_151554_b(world, Blocks.log, 0, 1, 3, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 6, 3, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 1, 3, 5, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 6, 3, 5, structureBoundingBox);

        	for (int k = 0; k < 4; k++) {
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 0, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 0, 5, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 3, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 3, 5, structureBoundingBox);
        		
        		if (k != 3) {
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 0, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 6, 0, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 3, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 6, 3, 2+k, structureBoundingBox);
        		}
        	}
        	
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 1, 1, 5, 2, 1, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 1, 5, 5, 2, 5, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 1, 2, 1, 2, 4, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 6, 1, 2, 6, 2, 4, Blocks.planks, 1, Blocks.planks, 1, false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 0, 2, 5, 0, 4, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, -1, 0, 6, -1, 0, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
			
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][0], 3, 0, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][3], 4, 0, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][1], 5, 0, 0, structureBoundingBox);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 3, 0, 0, 3, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 3, 6, 6, 3, 6, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 7, 3, 1, 7, 3, 6, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 3, 0, 7, 3, 0, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 4, 1, 6, 4, 5, Blocks.wooden_slab, 0, Blocks.wooden_slab, 0, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 4, 2, 5, 4, 4, Blocks.wooden_slab, 8, Blocks.wooden_slab, 8, false);
			
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 6, 2, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 3, 2, 5, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 5, structureBoundingBox);
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 4, 1, 1, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4, 3, 2, structureBoundingBox);
			
			this.spawnEntity(world, structureBoundingBox, 3, 1, 3, 0);
			
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityLakeside(world, EnumVillager.LAKESIDE_CHIEF);
		}
	}
	
	public static class Tower extends GlobalVillage
	{
		public Tower() {}
		
 		public Tower(StructureVillagePieces.Start villagePiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(EnumVillagePiece.LAKESIDE_TOWER, villagePiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(7);
 		}

		public static Tower buildComponent( StructureVillagePieces.Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 6, 7, 5, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new Tower(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
            for (int x = 0; x < 6; ++x)
            {
                for (int z = 0; z < 6; ++z)
                {
                    this.clearCurrentPositionBlocksUpwards(world, x, -2, z, structureBoundingBox);
                }
            }
            
        	this.func_151554_b(world, Blocks.log, 0, 1, 6, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 4, 6, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 1, 6, 4, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 4, 6, 4, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 5, 0, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 5, -2, 5, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 3, -3, 5, structureBoundingBox);

        	for (int k = 0; k < 2; k++) {
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 0, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 0, 4, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 3, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 3, 4, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 6, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 2+k, 6, 4, structureBoundingBox);
        		
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 0, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 4, 0, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 3, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 4, 3, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 1, 6, 2+k, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 4, 6, 2+k, structureBoundingBox);
        	}
        	
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 1, 1, 3, 2, 1, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 1, 4, 3, 2, 4, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 1, 2, 1, 2, 3, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 1, 2, 4, 2, 3, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 4, 1, 3, 5, 1, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 4, 4, 3, 5, 4, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 4, 2, 1, 5, 3, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 4, 2, 4, 5, 3, Blocks.planks, 1, Blocks.planks, 1, false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 0, 2, 3, 0, 3, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, -1, 0, 4, -1, 0, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		
			
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][0], 1, 0, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][3], 2, 0, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][1], 3, 0, 0, structureBoundingBox);

    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 1, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 9, 5, 0, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][2], 5, 0, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][2], 5, -1, 4, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][0], 4, -2, 5, structureBoundingBox);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 3, 2, 3, 3, 3, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 3, 0, 4, 3, 0, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		
			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 3, 1, 0, 3, 4, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 3, 5, 4, 3, 5, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		
			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 3, 1, 5, 3, 4, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		

			this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 0, 4, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 0, 5, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 0, 4, 4, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 0, 5, 4, structureBoundingBox); 
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 4, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 5, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 4, 4, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 5, 4, structureBoundingBox);
    		
       		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 1, 4, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 1, 5, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 4, 4, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 4, 5, 0, structureBoundingBox);
       		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 1, 4, 5, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 1, 5, 5, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 4, 4, 5, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 4, 5, 5, structureBoundingBox);
    		
			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 6, 0, 0, 6, 4, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 6, 5, 4, 6, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 6, 1, 5, 6, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 6, 0, 5, 6, 0, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 7, 1, 4, 7, 4, Blocks.wooden_slab, 0, Blocks.wooden_slab, 0, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 7, 2, 3, 7, 3, Blocks.wooden_slab, 8, Blocks.wooden_slab, 8, false);
			
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 2, 4, structureBoundingBox);
    		
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 3, 3, 3, structureBoundingBox);
    		
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 4, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 5, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 3, 4, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 3, 5, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 4, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 5, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 4, 4, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 5, 4, structureBoundingBox);
    		
    		for (int k = 1; k < 4; k++) {
        		this.placeBlockAtCurrentPosition(world, Blocks.ladder, this.getMetadataWithOffset(Blocks.ladder, 4), 3, k, 3, structureBoundingBox);
    		}
    		
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 2, 1, 1, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 4, 1, 2, this.getMetadataWithOffset(Blocks.wooden_door, 2));
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 2, 2, 4, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 2, 5, 2, structureBoundingBox);
			
			this.spawnEntity(world, structureBoundingBox, 3, 1, 3, 0);
			this.spawnEntity(world, structureBoundingBox, 3, 1, 3, 0);
			this.spawnEntity(world, structureBoundingBox, 3, 4, 3, 0);
			
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityLakeside(world, EnumVillager.LAKESIDE_GUARD);
		}
	}
	
	public static class Breeding extends GlobalVillage
	{
        private static Planks randomSwampPlanks = new Planks(null);
		
		public Breeding() {}
		
 		public Breeding(StructureVillagePieces.Start villagePiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(EnumVillagePiece.LAKESIDE_BREEDING, villagePiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(4);
 		}

		public static Breeding buildComponent( StructureVillagePieces.Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 10, 4, 8, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new Breeding(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
            for (int x = 0; x < 10; ++x)
            {
                for (int z = 0; z < 9; ++z)
                {
                    this.clearCurrentPositionBlocksUpwards(world, x, -2, z, structureBoundingBox);
                }
            }
            
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, -6, 2, 0, -3, 7, false, random, randomSwampPlanks);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 8, -6, 2, 8, -3, 7, false, random, randomSwampPlanks);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, -6, 1, 7, -3, 1, false, random, randomSwampPlanks);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, -6, 8, 7, -3, 8, false, random, randomSwampPlanks);
            
            for (int x =1; x < 8; x++) {
            	for (int z = 2; z < 8; z++) {
                	this.func_151554_b(world, Blocks.dirt, 0, x, -4, z, structureBoundingBox);
            		this.placeBlockAtCurrentPosition(world, Blocks.grass, 0, x, -3, z, structureBoundingBox);
            	}
            }
            for (int x =0; x < 9; x++) {
            	for (int z = 1; z < 9; z++) {
                	this.func_151554_b(world, Blocks.dirt, 0, x, -7, z, structureBoundingBox);
            	}
            }
        	this.fillWithAir(world, structureBoundingBox, 4, -6, 1, 4, -3, 1);
        	this.fillWithAir(world, structureBoundingBox, 8, -6, 4, 8, -3, 4);

        	this.func_151554_b(world, Blocks.log, 0, 0, -2, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 0, -2, 8, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 8, -2, 8, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 8, 3, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 4, 3, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 8, 3, 4, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 4, 3, 4, structureBoundingBox);

        	this.fillWithBlocks(world, structureBoundingBox, 1, -2, 1, 3, -2, 1, Blocks.fence, Blocks.fence, false);
        	this.fillWithBlocks(world, structureBoundingBox, 0, -2, 2, 0, -2, 7, Blocks.fence, Blocks.fence, false);
        	this.fillWithBlocks(world, structureBoundingBox, 1, -2, 8, 7, -2, 8, Blocks.fence, Blocks.fence, false);
        	this.fillWithBlocks(world, structureBoundingBox, 8, -2, 5, 8, -2, 7, Blocks.fence, Blocks.fence, false);
        	this.fillWithBlocks(world, structureBoundingBox, 8, -2, 2, 8, -1, 3, Blocks.fence, Blocks.fence, false);
        	this.fillWithBlocks(world, structureBoundingBox, 5, -2, 1, 7, -1, 1, Blocks.fence, Blocks.fence, false);


        	for (int k = 0; k < 3; k++) {
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 5+k, 0, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 5+k, 0, 4, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 5+k, 3, 1, structureBoundingBox);
        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][0], 5+k, 3, 4, structureBoundingBox);
        		
        		if (k != 2) {
	        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 4, 0, 2+k, structureBoundingBox);
	        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 8, 0, 2+k, structureBoundingBox);
	        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 4, 3, 2+k, structureBoundingBox);
	        		this.placeBlockAtCurrentPosition(world, Blocks.log, DirectionTools.log[coordBaseMode][1], 8, 3, 2+k, structureBoundingBox);
        		}
        	}
        	
			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 1, 1, 7, 2, 1, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 1, 4, 7, 2, 4, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 1, 2, 4, 2, 3, Blocks.planks, 1, Blocks.planks, 1, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 8, 1, 2, 8, 2, 3, Blocks.planks, 1, Blocks.planks, 1, false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 0, 2, 7, 0, 3, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 4, -1, 0, 8, -1, 0, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		
			
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][0], 5, 0, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][3], 6, 0, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][1], 7, 0, 0, structureBoundingBox);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 0, 1, 3, 0, 4, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		
			this.fillWithMetadataBlocks(world, structureBoundingBox, 9, 0, 1, 9, 0, 4, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);		
    		
			this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 9, 6, 0, 5, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 9, 7, 0, 5, structureBoundingBox);

    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][0], 5, 0, 5, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][0], 4, -1, 5, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][0], 3, -2, 5, structureBoundingBox);

			this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 3, 1, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 3, 2, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 3, 1, 4, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 3, 2, 4, structureBoundingBox); 

			this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 9, 1, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 9, 2, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 9, 1, 4, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 9, 2, 4, structureBoundingBox); 
    		
       		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 7, 1, 5, structureBoundingBox);
       		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 7, 2, 5, structureBoundingBox);
    		
			this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 3, 0, 3, 3, 4, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 3, 5, 8, 3, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 9, 3, 1, 9, 3, 5, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 3, 0, 9, 3, 0, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], false);

			this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 4, 1, 8, 4, 4, Blocks.wooden_slab, 0, Blocks.wooden_slab, 0, false);
			this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 4, 2, 7, 4, 3, Blocks.wooden_slab, 8, Blocks.wooden_slab, 8, false);
			
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 1, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 3, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 8, 1, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 8, 2, 2, structureBoundingBox);

			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 6, 1, 1, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 6, 1, 4, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 6, 3, 2, structureBoundingBox);
			
			this.spawnEntity(world, structureBoundingBox, 2, -2, 1, 0);
			this.spawnEntity(world, structureBoundingBox, 2, -2, 1, 1);
			this.spawnEntity(world, structureBoundingBox, 2, -2, 1, 1);
			this.spawnEntity(world, structureBoundingBox, 2, -2, 1, 1);
			this.spawnEntity(world, structureBoundingBox, 2, -2, 1, 1);
			
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			switch (choice) {
			case 0 :
				return new EntityLakeside(world, EnumVillager.LAKESIDE_BREEDER);
			case 1 :
				return new EntityPig(world);
			}
			return null;
		}
	}
	
	public static class Field extends GlobalField
	{
        private static Planks randomSwampPlanks = new Planks(null);
		
		public Field() {}
		
 		public Field(StructureVillagePieces.Start villagePiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(EnumVillagePiece.LAKESIDE_FIELD, villagePiece, componentType, random, structureBoundingBox, coordBaseMode);
 			setOffset(1);
 		}

		public static Field buildComponent( StructureVillagePieces.Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 8, 4, 7, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new Field(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		@Override
		protected Block getCropType(Random random) {
			return random.nextBoolean() ? Blocks.carrots:Blocks.potatoes;
		}

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
            for (int x = 0; x < 8; ++x)
            {
                for (int z = 0; z < 8; ++z)
                {
                    this.clearCurrentPositionBlocksUpwards(world, x, 1, z, structureBoundingBox);
                }
            }
            
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, -3, 2, 0, 0, 6, false, random, randomSwampPlanks);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 7, -3, 2, 7, 0, 6, false, random, randomSwampPlanks);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, -3, 1, 6, 0, 1, false, random, randomSwampPlanks);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, -3, 7, 6, 0, 7, false, random, randomSwampPlanks);
            
            for (int x = 1; x < 7; x++) {
            	for (int z = 2; z < 7; z++) {
                	this.func_151554_b(world, Blocks.dirt, 0, x, -1, z, structureBoundingBox);
            		this.placeBlockAtCurrentPosition(world, Blocks.farmland, 0, x, 0, z, structureBoundingBox);
            	}
            }

        	this.func_151554_b(world, Blocks.log, 0, 0, 2, 0, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 0, 1, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 0, 1, 7, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 7, 2, 0, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 7, 1, 1, structureBoundingBox);
        	this.func_151554_b(world, Blocks.log, 0, 7, 1, 7, structureBoundingBox);
        	
            for (int x =0; x < 8; x++) {
            	for (int z = 1; z < 8; z++) {
                	this.func_151554_b(world, Blocks.dirt, 0, x, -4, z, structureBoundingBox);
            	}
            }
            
            for (int x = 1; x < 7; x++) {
                this.clearCurrentPositionBlocksUpwards(world, x, 1, 0, structureBoundingBox);
            }
            
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, -3, 0, 6, 0, 0, false, random, randomSwampPlanks);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][1], 2, 1, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, DirectionTools.stair[coordBaseMode][1], 1, 2, 0, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.planks, 1, 1, 1, 0, structureBoundingBox);

         
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], 6, 1, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][0], 6, 1, 7, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], 1, 1, 1, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][1], 1, 1, 7, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], 0, 1, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][2], 7, 1, 2, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], 0, 1, 6, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, DirectionTools.stair[coordBaseMode][3], 7, 1, 6, structureBoundingBox);
    
			this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 0, 0, 1, 4, structureBoundingBox);
    		this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 0, 7, 1, 4, structureBoundingBox);

    		this.fillWithBlocks(world, structureBoundingBox, 0, 0, 4, 7, 0 , 4, Blocks.water, Blocks.water, false);
			
            for (int x = 1; x < 7; ++x)
            {
                this.placeBlockAtCurrentPosition(world, cropTypeA, MathHelper.getRandomIntegerInRange(random, 2, 7), x, 1, 2, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, cropTypeA, MathHelper.getRandomIntegerInRange(random, 2, 7), x, 1, 3, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, cropTypeB, MathHelper.getRandomIntegerInRange(random, 2, 7), x, 1, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, cropTypeB, MathHelper.getRandomIntegerInRange(random, 2, 7), x, 1, 6, structureBoundingBox);
            }
            
            this.spawnEntity(world, structureBoundingBox, 3, 1, 1, 0);
            this.spawnEntity(world, structureBoundingBox, 3, 1, 1, 0);
    		
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityLakeside(world, EnumVillager.LAKESIDE_FARMER);
		}
	}
	
	
    static class Planks extends StructureComponent.BlockSelector
    {
        private Planks() {}

        /**
         * picks Block Ids and Metadata (Silverfish)
         */
        public void selectBlocks(Random p_75062_1_, int p_75062_2_, int p_75062_3_, int p_75062_4_, boolean p_75062_5_)
        {
        	this.field_151562_a = Blocks.planks;

            if (p_75062_1_.nextFloat() < 0.4F)
            {
                this.selectedBlockMetaData = 0;
            }
            else
            {
                this.selectedBlockMetaData = 1;
            }
        }

        Planks(Object p_i2063_1_)
        {
            this();
        }
    }
}
