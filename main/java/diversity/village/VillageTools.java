package diversity.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import cpw.mods.fml.common.registry.VillagerRegistry;
import diversity.entity.EntityAztec;
import diversity.entity.EntityGlobalVillager;
import diversity.entity.EntityInuit;
import diversity.suppliers.EnumVillageBasicPiece;
import diversity.suppliers.EnumVillagePiece;
import diversity.suppliers.EnumVillage;
import diversity.suppliers.IEnumPiece;
import diversity.utils.Point;

public abstract class VillageTools
{
	public final EnumVillage village;
	
	VillageTools(EnumVillage village) {
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
	
	public abstract GlobalStart getStart(WorldChunkManager worldChunkManager, int i, Random rand, int j,int k, List list, int numberOfVillagers);

    protected abstract GlobalTorch getTorch(GlobalStart villagePiece, int par2, Random rand, StructureBoundingBox boundingBox, int coordBaseMode);

	protected abstract GlobalPath getPath(GlobalStart p_75080_0_, int p_75080_7_, Random p_75080_2_, StructureBoundingBox structureboundingbox, int p_75080_6_);
	
    
	public static abstract class GlobalStart extends StructureVillagePieces.Start
    {	
		private VillageTools village;
		
        public GlobalStart() {
        }

        public GlobalStart(VillageTools village, WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_)
        {
            super(p_i2104_1_, p_i2104_2_, p_i2104_3_, p_i2104_4_, p_i2104_5_, p_i2104_6_, p_i2104_7_);
            this.village = village;
        }
        
        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        @Override
        public void buildComponent(StructureComponent startPiece, List componentList, Random random)
        {
        	getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, this.getComponentType());
        	getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, this.getComponentType());
        	getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, this.getComponentType());
        	getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
        }
        
		public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox, int offset)
		{
			if (this.field_143015_k < 0) {
				this.field_143015_k = this.getAverageGroundLevel(world, structureBoundingBox);

				if (this.field_143015_k < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + offset, 0);
			}
			return false;
		}
    }
	
    public static abstract class GlobalPath extends StructureVillagePieces.Path
    {
        private int averageGroundLevel;
        private StructureVillagePieces.Start startPiece;
        private VillageTools village;
        
		Table<Integer, Integer, Integer> points = HashBasedTable.create();
        
        private int recursiveCounter;
        
    	public GlobalPath() {}

    	public GlobalPath(VillageTools village, GlobalStart startPiece, int p_i2105_2_, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
            super(startPiece, p_i2105_2_, random, structureBoundingBox, coordBaseMode);
            this.startPiece = startPiece;
            this.averageGroundLevel = Math.max(structureBoundingBox.getXSize(), structureBoundingBox.getZSize());
            this.village = village;
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
                piece = this.getNextComponentNN((GlobalStart)startPiece, componentList, random, 0, i);

                if (piece != null)
                {
                    i += Math.max(piece.getBoundingBox().getXSize(), piece.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            for (i = random.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + random.nextInt(5))
            {
                piece = this.getNextComponentPP((GlobalStart)startPiece, componentList, random, 0, i);

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
                        VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 1, this.getComponentType());
                        break;
                    case 1:
                    	VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
                        break;
                    case 2:
                    	VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, 1, this.getComponentType());
                        break;
                    case 3:
                    	VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
                }
            }

            if (flag && random.nextInt(3) > 0)
            {
                switch (this.coordBaseMode)
                {
                    case 0:
                    	VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 3, this.getComponentType());
                        break;
                    case 1:
                    	VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                        break;
                    case 2:
                    	VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, 3, this.getComponentType());
                        break;
                    case 3:
                    	VillageTools.getNextComponentVillagePath(village, (GlobalStart)startPiece, componentList, random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                }
            }
        }
        
        /**
         * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentNN(GlobalStart startPiece, List componentList, Random random, int p_74891_4_, int p_74891_5_)
        {
            switch (this.coordBaseMode)
            {
                case 0:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
                case 1:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
                case 2:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
                case 3:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
                default:
                    return null;
            }
        }

        /**
         * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentPP(GlobalStart startPiece, List componentList, Random random, int p_74894_4_, int p_74894_5_)
        {
            switch (this.coordBaseMode)
            {
                case 0:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
                case 1:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                case 2:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
                case 3:
                    return VillageTools.getNextVillageStructureComponent(village, startPiece, componentList, random, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                default:
                    return null;
            }
        }
    }
    
    public static abstract class GlobalTorch extends GlobalVillage
    {
		public GlobalTorch() {}

    	public GlobalTorch(EnumVillageBasicPiece enumPiece, StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
    		super((IEnumPiece)enumPiece, startPiece, componentType, structureBoundingBox, coordBaseMode);
        }
    	
        public static StructureBoundingBox getStructureBoundingBox(StructureVillagePieces.Start startPiece, List list, Random random, int coordX, int coordY, int coordZ, int p_74904_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(coordX, coordY, coordZ, 0, 0, 0, 3, 4, 2, p_74904_6_);
            return StructureComponent.findIntersecting(list, structureboundingbox) != null ? null : structureboundingbox;
        }
        
		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return null;
		}
    }
    
    public static abstract class GlobalVillage extends StructureVillagePieces.Village
    {
        private int villagersSpawned;
        protected IEnumPiece piece;
		
		private int offset;
		
    	public GlobalVillage() {}
    	
        protected GlobalVillage(IEnumPiece piece, StructureVillagePieces.Start startPiece, int componentType, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
            super(startPiece, componentType);
 			this.coordBaseMode = coordBaseMode;
 			this.boundingBox = structureBoundingBox;
            this.piece = piece;
        }
        
        @Override
        protected void func_143012_a(NBTTagCompound tagCompound)
        {
        	super.func_143012_a(tagCompound);
            tagCompound.setInteger("VCount", this.villagersSpawned);
        }

        @Override
        protected void func_143011_b(NBTTagCompound tagCompound)
        {
        	super.func_143011_b(tagCompound);
            this.villagersSpawned = tagCompound.getInteger("VCount");
        }	
 		
 		protected void setOffset(int offset)
 		{
 			this.offset = offset;
 		}
        
        protected void spawnEntity(World world, StructureBoundingBox structureBoundingBox, int spawnX, int spawnY, int spawnZ, int choice)
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
			if (this.field_143015_k < 0) {
				this.field_143015_k = this.getAverageGroundLevel(world, structureBoundingBox);

				if (this.field_143015_k < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + offset, 0);
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
	
    public static StructureComponent getNextComponentVillagePath(VillageTools villageInstance, GlobalStart startPiece, List componentList, Random random, int x, int y, int z, int coordBaseMode, int componentType)
    {
        if (componentType > 3 + startPiece.terrainType)
        {
            return null;
        }
        else if (Math.abs(x - startPiece.getBoundingBox().minX) <= 112 && Math.abs(z - startPiece.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = GlobalPath.func_74933_a(startPiece, componentList, random, x, y, z, coordBaseMode);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
            	GlobalPath path = villageInstance.getPath(startPiece, componentType, random, structureboundingbox, coordBaseMode);
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
    
    /**
     * attempts to find a next Structure Component to be spawned, private Village function
     */
    private static StructureComponent getNextVillageStructureComponent(VillageTools village, GlobalStart startPiece, List componentList, Random random, int p_75077_3_, int p_75077_4_, int p_75077_5_, int p_75077_6_, int p_75077_7_)
    {
        if (p_75077_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_75077_3_ - startPiece.getBoundingBox().minX) <= 112 && Math.abs(p_75077_5_ - startPiece.getBoundingBox().minZ) <= 112)
        {
        	GlobalVillage piece = getNextVillageComponent(village, startPiece, componentList, random, p_75077_3_, p_75077_4_, p_75077_5_, p_75077_6_, p_75077_7_ + 1);

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
            if (((StructureVillagePieces.PieceWeight)iterator.next()).villagePiecesLimit == 0)
            {
                iterator.remove();
            }
        }

        return arraylist;
	}
	
    /**
     * attempts to find a next Village Component to be spawned
     */
    private static GlobalVillage getNextVillageComponent(VillageTools village, GlobalStart startPiece, List p_75081_1_, Random random, int p_75081_3_, int p_75081_4_, int p_75081_5_, int coordBaseMode, int p_75081_7_)
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
                    StructureVillagePieces.PieceWeight pieceweight = (StructureVillagePieces.PieceWeight)iterator.next();
                    l1 -= pieceweight.villagePieceWeight;

                    if (l1 < 0)
                    {
                        if (!pieceweight.canSpawnMoreVillagePiecesOfType(p_75081_7_) || pieceweight == startPiece.structVillagePieceWeight && startPiece.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        GlobalVillage piece = getVillageComponent(startPiece, pieceweight, p_75081_1_, random, p_75081_3_, p_75081_4_, p_75081_5_, coordBaseMode, p_75081_7_);

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

            StructureBoundingBox structureboundingbox = VillageTools.GlobalTorch.getStructureBoundingBox(startPiece, p_75081_1_, random, p_75081_3_, p_75081_4_, p_75081_5_, coordBaseMode);

            if (structureboundingbox != null)
            {
            	return village.getTorch(startPiece, p_75081_7_, random, structureboundingbox, coordBaseMode);
            }
            else
            {
                return null;
            }
        }
    }
    
    private static int getVillagePieceWeight(List pieceList)
    {
        boolean flag = false;
        int i = 0;
        StructureVillagePieces.PieceWeight pieceweight;

        for (Iterator iterator = pieceList.iterator(); iterator.hasNext(); i += pieceweight.villagePieceWeight)
        {
            pieceweight = (StructureVillagePieces.PieceWeight)iterator.next();

            if (pieceweight.villagePiecesLimit > 0 && pieceweight.villagePiecesSpawned < pieceweight.villagePiecesLimit)
            {
                flag = true;
            }
        }

        return flag ? i : -1;
    }
    
    private static GlobalVillage getVillageComponent(StructureVillagePieces.Start startPiece, StructureVillagePieces.PieceWeight pieceWeight, List p_75083_2_, Random p_75083_3_, int p_75083_4_, int p_75083_5_, int p_75083_6_, int p_75083_7_, int p_75083_8_)
    {
        return EnumVillagePiece.getVillageComponent(pieceWeight, startPiece , p_75083_2_, p_75083_3_, p_75083_4_, p_75083_5_, p_75083_6_, p_75083_7_, p_75083_8_);
    }
    
    public static abstract class GlobalField extends GlobalVillage
    {
        /** First crop type for this field. */
        protected Block cropTypeA;
        /** Second crop type for this field. */
        protected Block cropTypeB;
        /** Third crop type for this field. */
        protected Block cropTypeC;
        /** Fourth crop type for this field. */
        protected Block cropTypeD;
    	
		public GlobalField() {}

    	public GlobalField(EnumVillagePiece enumPiece, StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
        {
    		super((IEnumPiece)enumPiece, startPiece, componentType, structureBoundingBox, coordBaseMode);
    		
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
