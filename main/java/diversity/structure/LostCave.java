package diversity.structure;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import javax.vecmath.Point4i;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import diversity.Diversity;
import diversity.cavegen.DwarvesCaveGenerator;
import diversity.cavegen.ICaveGenerator;
import diversity.cavegen.LostCaveGenerator;
import diversity.suppliers.EnumBlock;
import diversity.utils.EnumCubeType;
import diversity.utils.Table3d;
import diversity.world.WorldGenUnderGroundVine;

public class LostCave  extends GlobalFeature
{
	public Table3d blocks = new Table3d();
	public Point4i startPoint;

    public LostCave() {}
    
    public LostCave(Random random, int coordX, int coordZ)
    {
        super(random, coordX, coordZ, 7, 5, 9);
        
        ICaveGenerator caveGen = new LostCaveGenerator(7, 18, 4);
        List<Point4i> sphereCenter = caveGen.getControlPoints(random, coordX, 68, coordZ);
        blocks = caveGen.getCavePoints(sphereCenter, random);
        caveGen.generateBlockType(random, blocks, 40);
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
							world.setBlock(x, y, z, EnumBlock.poison_water.block);
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
							generateRoof(world, random, x, y, z);
						}
						else if (blocks.get(x, y, z).equals(EnumCubeType.WALL))
						{
							generateWall(world, random, x, y, z);
						}
						else if (blocks.get(x, y, z).equals(EnumCubeType.GROUND))
						{
							world.setBlock(x, y, z, Blocks.grass);
						}
						else if (blocks.get(x, y, z).equals(EnumCubeType.UNDERGROUND))
						{
							generateUnderGround(world, random, x, y, z);
						}
						blocks.remove(x, y, z);
					}
				}
	        }
		}
	}
	
	private void generateRoof(World world, Random random, int x, int y, int z) {
		if (y > 60 && !world.getBlock(x, y + 2, z).getMaterial().equals(Material.water))
		{
			if (world.isAirBlock(x, y + 4, z))
			{
				boolean canContinue = false;
				int tempY = y;
				while (tempY < y+10)
		        {
					world.setBlockToAir(x, tempY, z);
					tempY++;
		        }
				if (random.nextInt(5) == 0)
				{
					//WorldGenUnderGroundVine gen = new WorldGenUnderGroundVine();
					//gen.generate(world, random, x, y, z, blocks.lastKey().intValue());
				}
			}
		}
	}

	private void generateWall(World world, Random random, int x, int y, int z) {
		if (random.nextInt(10) == 0 && y < 60) {
			world.setBlock(x, y , z, Blocks.vine);
			while (random.nextBoolean() || random.nextBoolean())
			{
				y--;
				if (blocks.containsKey(x, y, z) && blocks.get(x, y, z).equals(EnumCubeType.AIR))
				{
					world.setBlock(x, y , z, Blocks.vine);
				} else {
					return;
				}
			}
		} else {
			world.setBlockToAir(x, y , z);
		}
	}

	private void generateUnderGround(World world, Random random, int x, int y, int z) {
		if (blocks.get(x, y+1, z).equals(EnumCubeType.WATER)) {
			if (random.nextInt(30) == 0) {
				world.setBlock(x, y, z, Blocks.gold_ore);
			} else {
				world.setBlock(x, y, z, Blocks.stone);
			}
		} else {
			world.setBlock(x, y, z, Blocks.dirt);
		}
	}
}