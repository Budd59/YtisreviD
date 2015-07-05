package diversity.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import diversity.Diversity;
import diversity.cavegen.ICaveGenerator;
import diversity.cavegen.ShroomCaveGenerator;
import diversity.suppliers.EnumBlock;
import diversity.utils.EnumCubeType;
import diversity.utils.Point;
import diversity.utils.Table3d;

public class ShroomCave extends GlobalFeature
{
	public Table3d blocks = new Table3d();
	public Point startPoint;

    public ShroomCave() {}
    
    public ShroomCave(Random random, int coordX, int coordZ)
    {
        super(random, coordX, coordZ, 7, 5, 9);
        
        ICaveGenerator caveGen = new ShroomCaveGenerator(7, 14, 4);
        List<Point> sphereCenter = caveGen.getControlPoints(random, coordX, 42, coordZ);
        blocks = caveGen.getCavePoints(sphereCenter, random);
        caveGen.generateBlockType(random, blocks, 29);
        blocks.mutateTable();
        
        startPoint = sphereCenter.get(0);
        int maxX = startPoint.x, minX = startPoint.x;
        int maxY = startPoint.y, minY = startPoint.y;
        int maxZ = startPoint.z, minZ = startPoint.z;
        
		for (Integer y : blocks.descendingKeySet())
		for (Integer x : blocks.rowKeySet(y))
		for (Integer z : blocks.columnKeySet(y))
		{
			if (x < minX)
				minX = x;
			if (x > maxX)
				maxX = x;
			if (y < minY)
				minY = y;
			if (y > maxY)
				maxY = y;
			if (z < minZ)
				minZ = z;
			if (z > maxZ)
				maxZ = z;
		}
        
        this.boundingBox = new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }
    
	@Override
	protected boolean build(World world, Random random,	StructureBoundingBox structureBoundingBox)
	{
        if (!this.func_74935_a(world, structureBoundingBox, 0))
        {
            return false;
        }
        else
        {
        	this.generateCaveBlocks(world, random, structureBoundingBox);
            return true;
        }
	}
	
	

	@Override
	protected EntityLiving getNewEntity(World world, int choice) {
		return new EntityWitch(world);
	}
	
	private void generateCaveBlocks(World world, Random random, StructureBoundingBox structureBoundingBox)
	{
		for (Integer x : blocks.mutation.rowKeySet())
		for (Integer z : blocks.mutation.row(x).keySet())
		{
			if (structureBoundingBox.isVecInside(x, 20, z))
	        { 
				for (Integer y : blocks.mutation.get(x, z).keySet())
				{
					if (blocks.containsKey(x, y, z))
					{
						if (blocks.get(x, y, z).equals(EnumCubeType.AIR))
						{
							world.setBlock(x, y, z, Blocks.air);
						}
						else if (blocks.get(x, y, z).equals(EnumCubeType.WATER))
						{
							world.setBlock(x, y, z, EnumBlock.phos_water.block);
						}
					}
				}
	        }
		}
		for (Integer x : blocks.mutation.rowKeySet())
		for (Integer z : blocks.mutation.row(x).keySet())
		{
			if (structureBoundingBox.isVecInside(x, 20, z))
	        { 
				for (Integer y : blocks.mutation.get(x, z).keySet())
				{
					if (blocks.containsKey(x, y, z))
					{
						if (blocks.get(x, y, z).equals(EnumCubeType.ROOF))
						{
							world.setBlock(x, y, z, Blocks.stone);
						}
						else if (blocks.get(x, y, z).equals(EnumCubeType.WALL))
						{
							world.setBlock(x, y, z, Blocks.stone);
						}
						else if (blocks.get(x, y, z).equals(EnumCubeType.GROUND))
						{
							world.setBlock(x, y, z, EnumBlock.fungal.block);
						}
						else if (blocks.get(x, y, z).equals(EnumCubeType.UNDERGROUND))
						{
							world.setBlock(x, y, z, Blocks.dirt);
						}
						blocks.remove(x, y, z);
					}
				}
	        }
		}
		Diversity.proxy.handler.listMushroomChunk.add(new Integer[]{structureBoundingBox.minX-8, structureBoundingBox.minZ-8});
	}
}