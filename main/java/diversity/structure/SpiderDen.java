package diversity.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.vecmath.Point4i;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import diversity.cavegen.ICaveGenerator;
import diversity.cavegen.SpiderDenGenerator;
import diversity.entity.EntityDarkSpider;
import diversity.entity.EntityYeti;
import diversity.utils.EnumCubeType;
import diversity.utils.Table3d;

public class SpiderDen extends GlobalFeature
{
	public Table3d blocks = new Table3d();
	public Point4i startPoint;
	
	private Integer caveH = -100;
	
	public ICaveGenerator caveGen;
	public List<Point4i> sphereCenter = new ArrayList<Point4i>();	

    public SpiderDen() {}
    
    public SpiderDen(Random random, int coordX, int coordZ)
    {
        super(random, coordX, coordZ, 7, 5, 9);
        
        caveGen = new SpiderDenGenerator(3, 5, 3);
        sphereCenter = caveGen.getControlPoints(random, coordX, 55, coordZ);
        blocks = caveGen.getCavePoints(sphereCenter, random);
        caveGen.generateBlockType(random, blocks, 0);
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
    
    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.setInteger("caveH", caveH);
        p_143012_1_.setInteger("startX", startPoint.x);
        p_143012_1_.setInteger("startY", startPoint.y);
        p_143012_1_.setInteger("startZ", startPoint.z);

    }

    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        super.func_143011_b(p_143011_1_);
        caveH = p_143011_1_.getInteger("caveH");
        startPoint = new Point4i();
        startPoint.x = p_143011_1_.getInteger("startX");
        startPoint.y = p_143011_1_.getInteger("startY");
        startPoint.z = p_143011_1_.getInteger("startZ");
        
        Random rand = new Random();
        caveGen = new SpiderDenGenerator(3, 5, 3);
        sphereCenter = caveGen.getControlPoints(rand, boundingBox.minX, 55, boundingBox.minZ);
		blocks = caveGen.getCavePoints(sphereCenter, rand);
        caveGen.generateBlockType(rand, blocks, 0);
        blocks.mutateTable();
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
        	if(caveH == -100) {
	        	for (int index = 0; index < sphereCenter.size(); index++) {
	        		Point4i center = sphereCenter.get(index);
	        		if (structureBoundingBox.intersectsWith(center.x, center.z, center.x, center.z)) {
	        			caveH = world.getTopSolidOrLiquidBlock(center.x, center.z) - center.y - 2;
	        			break;
	        		}
	        	}
        	}
        	if (caveH != -100)
        	{
        		this.generateCaveBlocks(world, random, structureBoundingBox);
        		if (structureBoundingBox.intersectsWith(startPoint.x, startPoint.z, startPoint.x, startPoint.z)) {
	                EntityLiving entity = getNewEntity(world, 0);
	                entity.setLocationAndAngles((double)startPoint.x + 0.5D, (double)startPoint.y+caveH, (double)startPoint.z + 0.5D, 0.0F, 0.0F);
	                world.spawnEntityInWorld(entity);
        		}
        		for (int index = 1; index < sphereCenter.size(); index++) {
        			if (structureBoundingBox.intersectsWith(sphereCenter.get(index).x, sphereCenter.get(index).z, sphereCenter.get(index).x, sphereCenter.get(index).z))
        			{
	                	EntityLiving entitySpider = new EntitySpider(world);
	                	entitySpider.setLocationAndAngles((double)sphereCenter.get(index).x + 0.5D, (double)sphereCenter.get(index).y+caveH, (double)sphereCenter.get(index).z + 0.5D, 0.0F, 0.0F);
		                world.spawnEntityInWorld(entitySpider);
        			}
                }
        	}
            return true;
        }
	}

	@Override
	protected EntityLiving getNewEntity(World world, int choice) {
		return new EntityDarkSpider(world);
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
						if (world.getTopSolidOrLiquidBlock(x, z)>=y+caveH)
						{
							if (blocks.get(x, y, z).equals(EnumCubeType.AIR))
							{
								if (blocks.containsKey(x, y-1, z) && blocks.get(x, y-1, z).equals(EnumCubeType.GROUND) && (world.getTopSolidOrLiquidBlock(x, z) - (y+caveH)) > 3 && random.nextInt(6)==0)
								{
									world.setBlock(x, y+caveH, z, Blocks.skull, 1, 0);
									TileEntitySkull tileEntity = (TileEntitySkull)Blocks.skull.createTileEntity(world, 0);
									tileEntity.func_145903_a(random.nextInt(8));
				        			world.setTileEntity(x, y+caveH, z, tileEntity);
								} else if (random.nextInt(5)==0)
								{
									world.setBlock(x, y+caveH, z, Blocks.web, 1, 0);
								}
								else {
									world.setBlock(x, y+caveH, z, Blocks.air, 0, 1);
								}
							}
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
						if (world.getTopSolidOrLiquidBlock(x, z)>(y+caveH))
						{
							if (blocks.get(x, y, z).equals(EnumCubeType.ROOF))
							{
								world.setBlock(x, y+caveH, z, Blocks.air);
							}
							else if (blocks.get(x, y, z).equals(EnumCubeType.WALL))
							{
								
							}
							else if (blocks.get(x, y, z).equals(EnumCubeType.GROUND))
							{
								world.setBlock(x, y+caveH, z, Blocks.dirt, 1, 3);
							}
							else if (blocks.get(x, y, z).equals(EnumCubeType.UNDERGROUND))
							{
								world.setBlock(x, y+caveH, z, Blocks.dirt);
							}
						}
						blocks.remove(x, y, z);
					}
				}
	        }
		}
	}
}