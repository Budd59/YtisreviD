package diversity.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import diversity.entity.EntityAztec;
import diversity.entity.AGlobalEntityVillager;
import diversity.entity.EntityInuit;
import diversity.suppliers.EnumVillageBasicPiece;
import diversity.suppliers.EnumVillagePiece;
import diversity.suppliers.EnumVillage;
import diversity.suppliers.IEnumPiece;
import diversity.utils.Point;

public abstract class AGlobalStructureVillage
{
	public final EnumVillage village;
	
	AGlobalStructureVillage(EnumVillage village) {
		this.village = village;
	}
	
	public static class BlockData {
		protected Block block;
		protected int metaData;
		
		public BlockData(Block block, int metaData) {
			this.block = block;
			this.metaData = metaData;
		}
	}
		
    private void getStructureList(ArrayList arraylist, Random rand)
    {
    	for (EnumVillagePiece piece : village.pieces)
    		arraylist.add(piece.getVillagePieceWeight(rand, 1));
	}
	
	public abstract AGlobalStart getStart(WorldChunkManager worldChunkManager, int i, Random rand, int j,int k, List list, int numberOfVillagers);

    protected abstract AGlobalTorch getTorch(AGlobalStart villagePiece, int par2, Random rand, StructureBoundingBox boundingBox, int coordBaseMode);

	protected abstract AGlobalPath getPath(AGlobalStart p_75080_0_, int p_75080_7_, Random p_75080_2_, StructureBoundingBox structureboundingbox, int p_75080_6_);
	
    
    /**
     * attempts to find a next Structure Component to be spawned, private Village function
     */
    private StructureComponent getNextVillageStructureComponent(AGlobalStart startPiece, List componentList, Random random, int p_75077_3_, int p_75077_4_, int p_75077_5_, int p_75077_6_, int p_75077_7_)
    {
        if (p_75077_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_75077_3_ - startPiece.getBoundingBox().minX) <= 112 && Math.abs(p_75077_5_ - startPiece.getBoundingBox().minZ) <= 112)
        {
        	AGlobalVillage piece = getNextVillageComponent(startPiece, componentList, random, p_75077_3_, p_75077_4_, p_75077_5_, p_75077_6_, p_75077_7_ + 1);

            if (piece != null)
            {
                componentList.add(piece);
                startPiece.field_74932_i.add(piece);
                return piece;
            }

            return null;
        }
        else
        {
            return null;
        }
    }

	public List getStructureVillageWeightedPieceList(Random rand)
	{
        ArrayList arraylist = new ArrayList();
        
        getStructureList(arraylist, rand);
        
        Iterator iterator = arraylist.iterator();
        while (iterator.hasNext())
        {
            if (((PieceWeight)iterator.next()).villagePiecesLimit == 0)
            {
                iterator.remove();
            }
        }

        return arraylist;
	}
	
    /**
     * attempts to find a next Village Component to be spawned
     */
    private AGlobalVillage getNextVillageComponent(AGlobalStart startPiece, List p_75081_1_, Random random, int p_75081_3_, int p_75081_4_, int p_75081_5_, int coordBaseMode, int p_75081_7_)
    {
        int villagePieceWeight = getVillagePieceWeight(startPiece.structureVillageWeightedPieceList);

        if (villagePieceWeight <= 0)
        {
            return null;
        }
        else
        {
            int k1 = 0;

            while (k1 < 5)
            {
                ++k1;
                int l1 = random.nextInt(villagePieceWeight);
                Iterator iterator = startPiece.structureVillageWeightedPieceList.iterator();

                while (iterator.hasNext())
                {
                    PieceWeight pieceweight = (PieceWeight)iterator.next();
                    l1 -= pieceweight.villagePieceWeight;

                    if (l1 < 0)
                    {
                        if (!pieceweight.canSpawnMoreVillagePiecesOfType(p_75081_7_) || pieceweight == startPiece.structVillagePieceWeight && startPiece.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        AGlobalVillage piece = getVillageComponent(startPiece, pieceweight, p_75081_1_, random, p_75081_3_, p_75081_4_, p_75081_5_, coordBaseMode, p_75081_7_);

                        if (piece != null)
                        {
                            ++pieceweight.villagePiecesSpawned;
                            startPiece.structVillagePieceWeight = pieceweight;

                            if (!pieceweight.canSpawnMoreVillagePieces())
                            {
                                startPiece.structureVillageWeightedPieceList.remove(pieceweight);
                            }

                            return piece;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = AGlobalStructureVillage.AGlobalTorch.getStructureBoundingBox(startPiece, p_75081_1_, random, p_75081_3_, p_75081_4_, p_75081_5_, coordBaseMode);

            if (structureboundingbox != null)
            {
            	return getTorch(startPiece, p_75081_7_, random, structureboundingbox, coordBaseMode);
            }
            else
            {
                return null;
            }
        }
    }
    
    private int getVillagePieceWeight(List pieceList)
    {
        boolean flag = false;
        int i = 0;
        PieceWeight pieceweight;

        for (Iterator iterator = pieceList.iterator(); iterator.hasNext(); i += pieceweight.villagePieceWeight)
        {
            pieceweight = (PieceWeight)iterator.next();

            if (pieceweight.villagePiecesLimit > 0 && pieceweight.villagePiecesSpawned < pieceweight.villagePiecesLimit)
            {
                flag = true;
            }
        }

        return flag ? i : -1;
    }
    
    private AGlobalVillage getVillageComponent(AGlobalStart startPiece, PieceWeight pieceWeight, List p_75083_2_, Random p_75083_3_, int p_75083_4_, int p_75083_5_, int p_75083_6_, int p_75083_7_, int p_75083_8_)
    {
        return EnumVillagePiece.getVillageComponent(pieceWeight, startPiece , p_75083_2_, p_75083_3_, p_75083_4_, p_75083_5_, p_75083_6_, p_75083_7_, p_75083_8_);
    }
    
    public StructureComponent getNextComponentVillagePath(AGlobalStart startPiece, List componentList, Random random, int x, int y, int z, int coordBaseMode, int componentType)
    {
        if (componentType > 3 + startPiece.terrainType)
        {
            return null;
        }
        else if (Math.abs(x - startPiece.getBoundingBox().minX) <= 112 && Math.abs(z - startPiece.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = AGlobalPath.func_74933_a(startPiece, componentList, random, x, y, z, coordBaseMode);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
            	AGlobalPath path = getPath(startPiece, componentType, random, structureboundingbox, coordBaseMode);
                int j1 = (path.getBoundingBox().minX + path.getBoundingBox().maxX) / 2;
                int k1 = (path.getBoundingBox().minZ + path.getBoundingBox().maxZ) / 2;
                int l1 = path.getBoundingBox().maxX - path.getBoundingBox().minX;
                int i2 = path.getBoundingBox().maxZ - path.getBoundingBox().minZ;
                int j2 = l1 > i2 ? l1 : i2;

                if (startPiece.getWorldChunkManager().areBiomesViable(j1, k1, j2 / 2 + 4, MapGenVillage.villageSpawnBiomes))
                {
                    componentList.add(path);
                    startPiece.field_74930_j.add(path);
                    return path;
                }
            }
            return null;
        }
        else
        {
            return null;
        }
    }
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public static class PieceWeight
    {
        /** The Class object for the represantation of this village piece. */
        public Class villagePieceClass;
        public final int villagePieceWeight;
        public int villagePiecesSpawned;
        public int villagePiecesLimit;
        private static final String __OBFID = "CL_00000521";

        public PieceWeight(Class p_i2098_1_, int p_i2098_2_, int p_i2098_3_)
        {
            this.villagePieceClass = p_i2098_1_;
            this.villagePieceWeight = p_i2098_2_;
            this.villagePiecesLimit = p_i2098_3_;
        }

        public boolean canSpawnMoreVillagePiecesOfType(int p_75085_1_)
        {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }

        public boolean canSpawnMoreVillagePieces()
        {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }
    }
 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	public static abstract class AGlobalStart extends AGlobalWell
    {	
        public WorldChunkManager worldChunkMngr;
        /** Boolean that determines if the village is in a desert or not. */
        public boolean inDesert;
        /** World terrain type, 0 for normal, 1 for flap map */
        public int terrainType;
        public PieceWeight structVillagePieceWeight;
        /**
         * Contains List of all spawnable Structure Piece Weights. If no more Pieces of a type can be spawned, they
         * are removed from this list
         */
        public List structureVillageWeightedPieceList;
        public List field_74932_i = new ArrayList();
        public List field_74930_j = new ArrayList();
        public BiomeGenBase biome;
		
        public AGlobalStart() {
        }

        public AGlobalStart(AGlobalStructureVillage village, WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_)
        {
            super(village, 0, p_i2104_3_, p_i2104_4_, p_i2104_5_);

            this.worldChunkMngr = p_i2104_1_;
            this.structureVillageWeightedPieceList = p_i2104_6_;
            this.terrainType = p_i2104_7_;
            BiomeGenBase biomegenbase = p_i2104_1_.getBiomeGenAt(p_i2104_4_, p_i2104_5_);
            this.inDesert = biomegenbase == BiomeGenBase.desert || biomegenbase == BiomeGenBase.desertHills;
            this.biome = biomegenbase;
            
        }
        
        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        @Override
        public void buildComponent(StructureComponent startPiece, List componentList, Random random)
        {
        	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, this.getComponentType());
        	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, this.getComponentType());
        	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, this.getComponentType());
        	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
        }
        
		public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox, int offset)
		{
			if (this.averageGroundLevel < 0) {
				this.averageGroundLevel = this.getAverageGroundLevel(world, structureBoundingBox);

				if (this.averageGroundLevel < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + offset, 0);
			}
			return false;
		}

        public WorldChunkManager getWorldChunkManager()
        {
            return this.worldChunkMngr;
        }
    }
	
    public static abstract class AGlobalWell extends AGlobalVillage
    {
		protected AGlobalStructureVillage village;

        public AGlobalWell() {}

        public AGlobalWell(AGlobalStructureVillage village, int componentType, Random rand, int p_i2109_4_, int p_i2109_5_)
        {
            super((AGlobalStart)null, componentType, null, 0);
            this.village = village;
            this.coordBaseMode = rand.nextInt(4);

            switch (this.coordBaseMode)
            {
                case 0:
                case 2:
                    this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
                    break;
                default:
                    this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
            }
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
        {
            village.getNextComponentVillagePath((AGlobalStart)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, this.getComponentType());
            village.getNextComponentVillagePath((AGlobalStart)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, this.getComponentType());
            village.getNextComponentVillagePath((AGlobalStart)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, this.getComponentType());
            village.getNextComponentVillagePath((AGlobalStart)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
        {
            if (this.averageGroundLevel < 0)
            {
                this.averageGroundLevel = this.getAverageGroundLevel(p_74875_1_, p_74875_3_);

                if (this.averageGroundLevel < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 3, 0);
            }

            this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 4, 12, 4, Blocks.cobblestone, Blocks.flowing_water, false);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 12, 2, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 3, 12, 2, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 12, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 3, 12, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 13, 1, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 14, 1, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 13, 1, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 14, 1, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 13, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 14, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 13, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 14, 4, p_74875_3_);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 15, 1, 4, 15, 4, Blocks.cobblestone, Blocks.cobblestone, false);

            for (int i = 0; i <= 5; ++i)
            {
                for (int j = 0; j <= 5; ++j)
                {
                    if (j == 0 || j == 5 || i == 0 || i == 5)
                    {
                        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.gravel, 0, j, 11, i, p_74875_3_);
                        this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 12, i, p_74875_3_);
                    }
                }
            }

            return true;
        }

		@Override
		protected boolean build(World world, Random random, StructureBoundingBox structureBoundingBox) {
			return false;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return null;
		}
    }
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public static abstract class AGlobalPath extends AGlobalRoad
    {
        private int averageGroundLevel;
        private AGlobalStart startPiece;
        private AGlobalStructureVillage village;
        
		Table<Integer, Integer, Integer> points = HashBasedTable.create();
        
        private int recursiveCounter;
        
    	public AGlobalPath() {}

    	public AGlobalPath(AGlobalStart startPiece, int p_i2105_2_, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
            super(startPiece, p_i2105_2_, structureBoundingBox, coordBaseMode);
            this.startPiece = startPiece;
            this.averageGroundLevel = Math.max(structureBoundingBox.getXSize(), structureBoundingBox.getZSize());
            this.village = startPiece.village;
            
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
                        int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
                        points.put(x, z, y);
        				BlockData blockData = getPathBlock(random);
        				world.setBlock(x, y, z, blockData.block, blockData.metaData, 2);
                    }
                }
            }
            return true;
		}
    	    	
        protected abstract BlockData getPathBlock(Random random);
                
        protected void func_143012_a(NBTTagCompound p_143012_1_)
        {
            super.func_143012_a(p_143012_1_);
            p_143012_1_.setInteger("Length", this.averageGroundLevel);
        }

        protected void func_143011_b(NBTTagCompound p_143011_1_)
        {
            super.func_143011_b(p_143011_1_);
            this.averageGroundLevel = p_143011_1_.getInteger("Length");
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent startPiece, List componentList, Random random)
        {
            boolean flag = false;
            int i;
            StructureComponent piece;

            for (i = random.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + random.nextInt(5))
            {
                piece = this.getNextComponentNN((AGlobalStart)startPiece, componentList, random, 0, i);

                if (piece != null)
                {
                    i += Math.max(piece.getBoundingBox().getXSize(), piece.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            for (i = random.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + random.nextInt(5))
            {
                piece = this.getNextComponentPP((AGlobalStart)startPiece, componentList, random, 0, i);

                if (piece != null)
                {
                    i += Math.max(piece.getBoundingBox().getXSize(), piece.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            if (flag && random.nextInt(3) > 0)
            {
                switch (this.coordBaseMode)
                {
                    case 0:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 1, this.getComponentType());
                        break;
                    case 1:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
                        break;
                    case 2:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, 1, this.getComponentType());
                        break;
                    case 3:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
                }
            }

            if (flag && random.nextInt(3) > 0)
            {
                switch (this.coordBaseMode)
                {
                    case 0:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 3, this.getComponentType());
                        break;
                    case 1:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                        break;
                    case 2:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, 3, this.getComponentType());
                        break;
                    case 3:
                    	village.getNextComponentVillagePath((AGlobalStart)startPiece, componentList, random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                }
            }
        }
        
        /**
         * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentNN(AGlobalStart startPiece, List componentList, Random random, int p_74891_4_, int p_74891_5_)
        {
            switch (this.coordBaseMode)
            {
                case 0:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
                case 1:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
                case 2:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
                case 3:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
                default:
                    return null;
            }
        }

        /**
         * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentPP(AGlobalStart startPiece, List componentList, Random random, int p_74894_4_, int p_74894_5_)
        {
            switch (this.coordBaseMode)
            {
                case 0:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
                case 1:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                case 2:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
                case 3:
                    return village.getNextVillageStructureComponent(startPiece, componentList, random, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                default:
                    return null;
            }
        }
        
        public static StructureBoundingBox func_74933_a(AGlobalStart p_74933_0_, List p_74933_1_, Random p_74933_2_, int p_74933_3_, int p_74933_4_, int p_74933_5_, int p_74933_6_)
        {
            for (int i1 = 7 * MathHelper.getRandomIntegerInRange(p_74933_2_, 3, 5); i1 >= 7; i1 -= 7)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74933_3_, p_74933_4_, p_74933_5_, 0, 0, 0, 3, 3, i1, p_74933_6_);

                if (StructureComponent.findIntersecting(p_74933_1_, structureboundingbox) == null)
                {
                    return structureboundingbox;
                }
            }

            return null;
        }
    }
    
    public static abstract class AGlobalRoad extends AGlobalVillage
    {
        public AGlobalRoad() {}

        protected AGlobalRoad(AGlobalStart startPiece, int componentType, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
            super(startPiece, componentType, structureBoundingBox, coordBaseMode);
        }

		@Override
		protected boolean build(World world, Random random, StructureBoundingBox structureBoundingBox) {
			return false;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return null;
		}
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static abstract class AGlobalVillage extends StructureVillagePieces.Village
//	{
//		private int villagersSpawned;
//		protected IEnumPiece piece;
//		
//		private int offset;
//		
//		public AGlobalVillage() {}
//		
//		protected AGlobalVillage(IEnumPiece piece, AGlobalStart startPiece, int componentType, StructureBoundingBox structureBoundingBox, int coordBaseMode)
//		{
//			super(startPiece, componentType);
//			this.coordBaseMode = coordBaseMode;
//			this.boundingBox = structureBoundingBox;
//			this.piece = piece;
//		}
//		
//		@Override
//		protected void func_143012_a(NBTTagCompound tagCompound)
//		{
//		super.func_143012_a(tagCompound);
//			tagCompound.setInteger("VCount", this.villagersSpawned);
//		}
//		
//		@Override
//		protected void func_143011_b(NBTTagCompound tagCompound)
//		{
//			super.func_143011_b(tagCompound);
//			this.villagersSpawned = tagCompound.getInteger("VCount");
//		}	
//		
//		protected void setOffset(int offset)
//		{
//			this.offset = offset;
//		}
//		
//		protected void spawnEntity(World world, StructureBoundingBox structureBoundingBox, int spawnX, int spawnY, int spawnZ, int choice)
//		{
//			int x = this.getXWithOffset(spawnX, spawnZ);
//			int y = this.getYWithOffset(spawnY);
//			int z = this.getZWithOffset(spawnX, spawnZ);
//			
//			if (!structureBoundingBox.isVecInside(x, y, z))
//			{
//				return;
//			}
//		
//			EntityLiving entity = getNewEntity(world, choice);
//			entity.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
//			world.spawnEntityInWorld(entity);
//		}
//		
//		@Override
//		public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
//		{
//			if (this.field_143015_k < 0) {
//				this.field_143015_k = this.getAverageGroundLevel(world, structureBoundingBox);
//			
//				if (this.field_143015_k < 0) {
//					return true;
//				}
//			
//				this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + offset, 0);
//			}
//		
//			build(world, random, structureBoundingBox);			
//			return true;
//		}
//		
//		protected void placeBlockAtCurrentPosition(World p_151550_1_, BlockData blockData, int p_151550_4_, int p_151550_5_, int p_151550_6_, StructureBoundingBox p_151550_7_)
//		{
//			placeBlockAtCurrentPosition(p_151550_1_, blockData.block, blockData.metaData, p_151550_4_, p_151550_5_, p_151550_6_, p_151550_7_);
//		}
//		
//		protected abstract boolean build(World world, Random random, StructureBoundingBox structureBoundingBox);
//		
//		protected abstract EntityLiving getNewEntity(World world, int choice);
//	}
    
    
    
    public abstract static class AGlobalVillage extends StructureComponent
    {
        protected int averageGroundLevel = -1;
        /** The number of villagers that have been spawned in this component. */
        private int villagersSpawned;
		private int offset;
        private AGlobalStart startPiece;
        private AGlobalStructureVillage village;

        public AGlobalVillage() {}

        protected AGlobalVillage(AGlobalStart startPiece, int componentType, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
            super(componentType);
            this.coordBaseMode = coordBaseMode;
			this.boundingBox = structureBoundingBox;
			
            if (startPiece != null)
            {
                this.startPiece = startPiece;
            	this.village = startPiece.village;
            }
        }

        protected void func_143012_a(NBTTagCompound p_143012_1_)
        {
            p_143012_1_.setInteger("HPos", this.averageGroundLevel);
            p_143012_1_.setInteger("VCount", this.villagersSpawned);
        }

        protected void func_143011_b(NBTTagCompound p_143011_1_)
        {
            this.averageGroundLevel = p_143011_1_.getInteger("HPos");
            this.villagersSpawned = p_143011_1_.getInteger("VCount");
        }

        /**
         * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentNN(AGlobalStart p_74891_1_, List p_74891_2_, Random p_74891_3_, int p_74891_4_, int p_74891_5_)
        {
            switch (this.coordBaseMode)
            {
                case 0:
                    return village.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
                case 1:
                    return village.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
                case 2:
                    return village.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
                case 3:
                    return village.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
                default:
                    return null;
            }
        }

        /**
         * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentPP(AGlobalStart p_74894_1_, List p_74894_2_, Random p_74894_3_, int p_74894_4_, int p_74894_5_)
        {
            switch (this.coordBaseMode)
            {
                case 0:
                    return village.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
                case 1:
                    return village.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                case 2:
                    return village.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
                case 3:
                    return village.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                default:
                    return null;
            }
        }

        /**
         * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of
         * all the levels in the BB's horizontal rectangle).
         */
        protected int getAverageGroundLevel(World p_74889_1_, StructureBoundingBox p_74889_2_)
        {
            int i = 0;
            int j = 0;

            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
            {
                for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                {
                    if (p_74889_2_.isVecInside(l, 64, k))
                    {
                        i += Math.max(p_74889_1_.getTopSolidOrLiquidBlock(l, k), p_74889_1_.provider.getAverageGroundLevel());
                        ++j;
                    }
                }
            }

            if (j == 0)
            {
                return -1;
            }
            else
            {
                return i / j;
            }
        }

        protected static boolean canVillageGoDeeper(StructureBoundingBox p_74895_0_)
        {
            return p_74895_0_ != null && p_74895_0_.minY > 10;
        }

        protected Block func_151558_b(Block p_151558_1_, int p_151558_2_)
        {
            return p_151558_1_;
        }

        protected int func_151557_c(Block p_151557_1_, int p_151557_2_)
        {
            return p_151557_2_;
        }

        /**
         * current Position depends on currently set Coordinates mode, is computed here
         */
        protected void placeBlockAtCurrentPosition(World p_151550_1_, Block p_151550_2_, int p_151550_3_, int p_151550_4_, int p_151550_5_, int p_151550_6_, StructureBoundingBox p_151550_7_)
        {
            Block block1 = this.func_151558_b(p_151550_2_, p_151550_3_);
            int i1 = this.func_151557_c(p_151550_2_, p_151550_3_);
            super.placeBlockAtCurrentPosition(p_151550_1_, block1, i1, p_151550_4_, p_151550_5_, p_151550_6_, p_151550_7_);
        }

        /**
         * arguments: (World worldObj, StructureBoundingBox structBB, int minX, int minY, int minZ, int maxX, int
         * maxY, int maxZ, int placeBlock, int replaceBlock, boolean alwaysreplace)
         */
        protected void fillWithBlocks(World p_151549_1_, StructureBoundingBox p_151549_2_, int p_151549_3_, int p_151549_4_, int p_151549_5_, int p_151549_6_, int p_151549_7_, int p_151549_8_, Block p_151549_9_, Block p_151549_10_, boolean p_151549_11_)
        {
            Block block2 = this.func_151558_b(p_151549_9_, 0);
            int k1 = this.func_151557_c(p_151549_9_, 0);
            Block block3 = this.func_151558_b(p_151549_10_, 0);
            int l1 = this.func_151557_c(p_151549_10_, 0);
            super.fillWithMetadataBlocks(p_151549_1_, p_151549_2_, p_151549_3_, p_151549_4_, p_151549_5_, p_151549_6_, p_151549_7_, p_151549_8_, block2, k1, block3, l1, p_151549_11_);
        }

        protected void func_151554_b(World p_151554_1_, Block p_151554_2_, int p_151554_3_, int p_151554_4_, int p_151554_5_, int p_151554_6_, StructureBoundingBox p_151554_7_)
        {
            Block block1 = this.func_151558_b(p_151554_2_, p_151554_3_);
            int i1 = this.func_151557_c(p_151554_2_, p_151554_3_);
            super.func_151554_b(p_151554_1_, block1, i1, p_151554_4_, p_151554_5_, p_151554_6_, p_151554_7_);
        }

		protected void setOffset(int offset)
		{
			this.offset = offset;
		}
		
		protected void spawnVillager(World world, StructureBoundingBox structureBoundingBox, int spawnX, int spawnY, int spawnZ, int choice)
		{
			int x = this.getXWithOffset(spawnX, spawnZ);
			int y = this.getYWithOffset(spawnY);
			int z = this.getZWithOffset(spawnX, spawnZ);
			
			if (!structureBoundingBox.isVecInside(x, y, z))
			{
				return;
			}
		
			EntityLiving entity = getNewEntity(world, choice);
			entity.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(entity);
		}
        
		@Override
		public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
			if (this.averageGroundLevel < 0) {
				this.averageGroundLevel = this.getAverageGroundLevel(world, structureBoundingBox);
			
				if (this.averageGroundLevel < 0) {
					return true;
				}
			
				this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + offset, 0);
			}
		
			build(world, random, structureBoundingBox);			
			return true;
		}
		
		protected void placeBlockAtCurrentPosition(World p_151550_1_, BlockData blockData, int p_151550_4_, int p_151550_5_, int p_151550_6_, StructureBoundingBox p_151550_7_)
		{
			placeBlockAtCurrentPosition(p_151550_1_, blockData.block, blockData.metaData, p_151550_4_, p_151550_5_, p_151550_6_, p_151550_7_);
		}
        
		protected abstract boolean build(World world, Random random, StructureBoundingBox structureBoundingBox);
		
		protected abstract EntityLiving getNewEntity(World world, int choice);
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static abstract class AGlobalTorch extends AGlobalVillage
    {
		public AGlobalTorch() {}

    	public AGlobalTorch(AGlobalStart startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
    		super(startPiece, componentType, structureBoundingBox, coordBaseMode);
        }
    	
        public static StructureBoundingBox getStructureBoundingBox(AGlobalStart startPiece, List list, Random random, int coordX, int coordY, int coordZ, int p_74904_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(coordX, coordY, coordZ, 0, 0, 0, 3, 4, 2, p_74904_6_);
            return StructureComponent.findIntersecting(list, structureboundingbox) != null ? null : structureboundingbox;
        }
        
		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return null;
		}
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static abstract class AGlobalField extends AGlobalVillage
    {
        /** First crop type for this field. */
        protected Block cropTypeA;
        /** Second crop type for this field. */
        protected Block cropTypeB;
        /** Third crop type for this field. */
        protected Block cropTypeC;
        /** Fourth crop type for this field. */
        protected Block cropTypeD;
    	
		public AGlobalField() {}

    	public AGlobalField(AGlobalStart startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
    		super(startPiece, componentType, structureBoundingBox, coordBaseMode);
    		
            this.cropTypeA = this.getCropType(random);
            this.cropTypeB = this.getCropType(random);
            this.cropTypeC = this.getCropType(random);
            this.cropTypeD = this.getCropType(random);
        }
    	
        protected void func_143012_a(NBTTagCompound p_143012_1_)
        {
            super.func_143012_a(p_143012_1_);
            p_143012_1_.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
            p_143012_1_.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
            p_143012_1_.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
            p_143012_1_.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
        }

        protected void func_143011_b(NBTTagCompound p_143011_1_)
        {
            super.func_143011_b(p_143011_1_);
            this.cropTypeA = Block.getBlockById(p_143011_1_.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(p_143011_1_.getInteger("CB"));
            this.cropTypeC = Block.getBlockById(p_143011_1_.getInteger("CC"));
            this.cropTypeD = Block.getBlockById(p_143011_1_.getInteger("CD"));
        }
    	
        protected abstract Block getCropType(Random random);
    	
    }
}
