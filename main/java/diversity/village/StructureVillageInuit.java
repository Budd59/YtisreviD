package diversity.village;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import diversity.entity.EntityInuit;
import diversity.suppliers.EnumVillageBasicPiece;
import diversity.suppliers.EnumVillagePiece;
import diversity.suppliers.EnumVillage;
import diversity.suppliers.EnumVillager;
import diversity.utils.Point;

public final class StructureVillageInuit extends AGlobalStructureVillage
{
	private static StructureVillageInuit instance;
	
	public StructureVillageInuit(EnumVillage village) {
		super(village);
		instance = this;
	}
	
	@Override
	protected AGlobalPath getPath(AGlobalStart p_75080_0_, int p_75080_7_, Random p_75080_2_, StructureBoundingBox structureboundingbox, int p_75080_6_) {
		return new Path(p_75080_0_, p_75080_7_, p_75080_2_, structureboundingbox, p_75080_6_);
	}
    
	@Override
	public AGlobalStart getStart(WorldChunkManager worldChunkManager, int i, Random rand, int j, int k, List list, int numberOfVillagers) {
		return new Start(worldChunkManager, i, rand, j, k, list, numberOfVillagers);
	}

	@Override
	protected AGlobalTorch getTorch(AGlobalStart villagePiece, int par2, Random rand, StructureBoundingBox boundingBox, int coordBaseMode) {
		return new Torch(villagePiece, par2, rand, boundingBox, coordBaseMode);
	}

	public static class Start extends AGlobalStart
    {
        public Start() {}

        public Start(WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_)
        {
            super(instance, p_i2104_1_, p_i2104_2_, p_i2104_3_, p_i2104_4_, p_i2104_5_, p_i2104_6_, p_i2104_7_);
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        @Override
        public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
        {
        	if (super.addComponentParts(p_74875_1_, p_74875_2_, p_74875_3_, 3))
        		return true;

            this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 5, 10, 5, Blocks.ice, Blocks.flowing_water, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_,  0, 10, 0, 5, 10, 5, Blocks.snow, Blocks.snow, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_,  2, 10, 2, 3, 10, 3, Blocks.air, Blocks.air, false);
            
            for (int i = 0; i <= 5; ++i)
            {
                for (int j = 0; j <= 5; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 11, i, p_74875_3_);
                }
            }
            
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 11, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 12, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 11, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 12, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 2, 11, 1, p_74875_3_);
            this.fillWithBlocks(p_74875_1_, p_74875_3_,  1, 13, 3, 4, 13, 3, Blocks.fence, Blocks.fence, false);
            return true;
        }
    }   
    
    public static class Path extends AGlobalPath
    {
    	public Path() {}

    	public Path(AGlobalStart p_i2105_1_, int p_i2105_2_, Random p_i2105_3_, StructureBoundingBox p_i2105_4_, int p_i2105_5_)
        {
            super(p_i2105_1_, p_i2105_2_, p_i2105_3_, p_i2105_4_, p_i2105_5_);
        }
		
		@Override
		protected BlockData getPathBlock(Random random) {
			return new BlockData(Blocks.snow, 0);
		}
    }
    
    public static abstract class GlobalInuitVillage extends AGlobalVillage
    {
		public GlobalInuitVillage() {}
		
 		public GlobalInuitVillage(AGlobalStart startPiece, int componentType, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(startPiece, componentType, structureBoundingBox, coordBaseMode);
		}
 		
		protected void generateSphere(World world, Random random, StructureBoundingBox structureBoundingBox, int baseX, int baseZ, int radius, double offset, int baseIncrement, int topIncrement)
		{			
			List<Point> basePoints = new ArrayList<Point>();
			List<Point> topPoints = new ArrayList<Point>();
			
			for (int baseDegree = 0; baseDegree <360; baseDegree=baseDegree+baseIncrement)
			{
				for (int topDegree = 0; topDegree <180; topDegree=topDegree+topIncrement)
				{
					Point point = getSpherePoint(radius, baseDegree, topDegree, offset);
					point.set(baseX + point.x, baseZ + point.z);
					Point sidePoint = getSpherePoint(radius, baseDegree, 180, offset);
					sidePoint.set(baseX + sidePoint.x, baseZ + sidePoint.z);

					func_151554_b(world, Blocks.packed_ice, 0, point.x, -1, point.z, structureBoundingBox);
					
					if ((Math.abs(point.x - baseX) == radius-1 || sidePoint.x == point.x) && (Math.abs(point.z - baseZ) == radius-1 || sidePoint.z == point.z))
						this.placeBlockAtCurrentPosition(world, Blocks.packed_ice, 0, point.x, 0, point.z, structureBoundingBox);
					else {
						if (random.nextInt(4)==0 && point.x!=0 && point.x!=2*baseX && point.z!=0 && point.z!=2*baseZ)
							this.placeBlockAtCurrentPosition(world, Blocks.log, 3, point.x, 0, point.z, structureBoundingBox);
						else
							this.placeBlockAtCurrentPosition(world, Blocks.snow, 0, point.x, 0, point.z, structureBoundingBox);
					}
					
					clearCurrentPositionBlocksUpwards(world, point.x, 1, point.z, structureBoundingBox);
					this.placeBlockAtCurrentPosition(world, Blocks.packed_ice, 0, point.x, point.y, point.z, structureBoundingBox);
				}
			}
		}
		
 		/*
 		Returns point of a sphere, evenly distributed over the sphere.
 		The sphere is centered at (x0,y0,z0) with the passed in radius.
 		The returned point is returned as a three element array [x,y,z]. 
 		*/
 		private Point getSpherePoint(int radius, int u, int v, double offset)
 		{
 		   double theta = 2 * Math.PI * u/360;
 		   double phi = Math.PI * v/360;
 		   int x = (int)(radius * Math.sin(phi) * Math.cos(theta));
 		   int z = (int)(radius * Math.sin(phi) * Math.sin(theta));
 		   int y = (int)(radius * Math.cos(phi) + offset);
 		   return new Point(x, y, z);
 		}
    }
    
	public static class Igloo1 extends GlobalInuitVillage
	{		
		public Igloo1() {}

 		public Igloo1(AGlobalStart startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(startPiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(3);
		}

		public static Igloo1 buildComponent(AGlobalStart villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 9, 5, 10, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new Igloo1(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		private StructureBoundingBox structureBoundingBox;
		private World world;

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
			int radius = 5;
			int baseX = 4;
			int baseZ = 5;
			double offset = 0.5;
			generateSphere(world, random, structureBoundingBox, baseX, baseZ, radius, offset, 15, 20);
			
			// Details
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 1, 1, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 1, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 3, 3, structureBoundingBox);			
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 6, 3, 7, structureBoundingBox);			
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 6, 3, 3, structureBoundingBox);			
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 3, 7, structureBoundingBox);
				
			func_151554_b(world, Blocks.packed_ice, 0, 3, -1, 0, structureBoundingBox);
			func_151554_b(world, Blocks.packed_ice, 0, 4, -1, 0, structureBoundingBox);
			func_151554_b(world, Blocks.packed_ice, 0, 5, -1, 0, structureBoundingBox);

			this.fillWithBlocks(world, structureBoundingBox, 3, 0, 0, 5, 3, 0, Blocks.packed_ice, Blocks.packed_ice, false);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 1, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.snow, 0, 4, 0, 1, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.snow, 0, 4, 0, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 3, 3, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 3, 0, structureBoundingBox);
			
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 4, 1, 1, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeBlockAtCurrentPosition(world, Blocks.log, 3, 4, 1, 8, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1, 2, 5, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 7, 2, 5, structureBoundingBox);
			
			this.spawnVillager(world, structureBoundingBox, 4, 2, 5, 0);
			this.spawnVillager(world, structureBoundingBox, 4, 2, 5, 0);
			
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityInuit(world, EnumVillager.INUIT_FISHERMAN);
		}
	}
	
	public static class Igloo2  extends GlobalInuitVillage
	{	
		public Igloo2() {}

 		public Igloo2(AGlobalStart startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(startPiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(2);
		}

		public static Igloo2 buildComponent(AGlobalStart villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 8, 4, 8, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new Igloo2(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
			int radius = 4;
			int baseX = 3;
			int baseZ = 3;
			double offset = 0.7;
			generateSphere(world, random, structureBoundingBox, baseX, baseZ, radius, offset, 18, 30);
			
			// Details
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 2, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 4, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 2, 2, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 2, 4, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 2, 1, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 1, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 2, 5, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 5, structureBoundingBox);
			
			this.placeBlockAtCurrentPosition(world, Blocks.snow, 0, 3, 0, 0, structureBoundingBox);
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 3, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeBlockAtCurrentPosition(world, Blocks.packed_ice, 0, 3, 3, 0, structureBoundingBox);

			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 3, 2, 5, structureBoundingBox);
			
			this.spawnVillager(world, structureBoundingBox, 3, 2, 3, 0);
			this.spawnVillager(world, structureBoundingBox, 3, 2, 3, 0);

			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityInuit(world, EnumVillager.INUIT_HUNTER);
		}
	}
	
	public static class ChiefIgloo  extends GlobalInuitVillage
	{	
		public ChiefIgloo() {}

 		public ChiefIgloo(AGlobalStart startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(startPiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(2);
		}

		public static ChiefIgloo buildComponent(AGlobalStart villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 8, 4, 9, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new ChiefIgloo(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}

		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
			int radius = 5;
			int baseX = 4;
			int baseZ = 5;
			double offset = 0.7;
			generateSphere(world, random, structureBoundingBox, baseX, baseZ, radius, offset, 10, 20);
			
			// Details
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 3, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 5, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 2, 3, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 2, 5, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 2, 2, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 2, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 2, 6, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 6, structureBoundingBox);
			
			func_151554_b(world, Blocks.packed_ice, 0, 3, -1, 0, structureBoundingBox);
			func_151554_b(world, Blocks.packed_ice, 0, 4, -1, 0, structureBoundingBox);
			func_151554_b(world, Blocks.packed_ice, 0, 5, -1, 0, structureBoundingBox);

			this.fillWithBlocks(world, structureBoundingBox, 3, 0, 0, 5, 3, 0, Blocks.packed_ice, Blocks.packed_ice, false);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 1, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.snow, 0, 4, 0, 1, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.snow, 0, 4, 0, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 3, 3, 0, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 3, 0, structureBoundingBox);
			
			this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 4, 1, 1, this.getMetadataWithOffset(Blocks.wooden_door, 1));
			this.placeBlockAtCurrentPosition(world, Blocks.packed_ice, 0, 3, 3, 1, structureBoundingBox);

			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1, 2, 5, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 7, 2, 5, structureBoundingBox);
			
			this.spawnVillager(world, structureBoundingBox, 4, 2, 5, 0);
			
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityInuit(world, EnumVillager.INUIT_CHIEF);
		}
	}
	
	public static class Kennel  extends AGlobalVillage
	{
		private int averageGroundLevel = -1;
		
		public Kennel() {}

 		public Kennel(AGlobalStart startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode) {
 			super(startPiece, componentType, structureBoundingBox, coordBaseMode);
 			setOffset(3);
		}

		public static Kennel buildComponent(AGlobalStart villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 7, 4, 6, coordBaseMode);
			return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new Kennel(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
		}


		@Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
            for (int x = 0; x < 7; ++x)
            {
                for (int z = 0; z < 6; ++z)
                {
                	this.func_151554_b(world, Blocks.dirt, 0, x, -1, z, structureBoundingBox);
                    this.clearCurrentPositionBlocksUpwards(world, x, 1, z, structureBoundingBox);
                }
            }
            
			this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 6, 0, 5, Blocks.fence, Blocks.air, false);
			this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 5, 0, 4, Blocks.air, Blocks.air, false);
			this.placeBlockAtCurrentPosition(world, Blocks.fence_gate, this.getMetadataWithOffset(Blocks.fence_gate, 0), 2, 0, 0, structureBoundingBox);
			
			this.spawnVillager(world, structureBoundingBox, 3, 2, 3, 0);
			this.spawnVillager(world, structureBoundingBox, 3, 2, 3, 1);
			this.spawnVillager(world, structureBoundingBox, 3, 2, 3, 1);
			this.spawnVillager(world, structureBoundingBox, 3, 2, 3, 1);
			
			return true;
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			switch (choice) {
			case 0 :
				return new EntityInuit(world, EnumVillager.INUIT_KENNELMASTER);
			case 1 :
				return new EntityWolf(world);
			}
			return null;
		}
	}
	
	public static class Torch  extends AGlobalTorch
	{		
		public Torch() {}

		public Torch(AGlobalStart startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int coordBaseMode)
		{
			super(startPiece, componentType, random, structureBoundingBox, coordBaseMode);
			setOffset(3);
		}

	       /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
		@Override
        public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
        {            
            this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 2, 3, 1, Blocks.air, Blocks.air, false);
            this.placeBlockAtCurrentPosition(world, Blocks.packed_ice, 0, 1, 0, 0, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 1, 1, 0, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1, 2, 0, structureBoundingBox);
            return true;
        }
    }
}
