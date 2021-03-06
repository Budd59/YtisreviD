package diversity.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import diversity.Diversity;
import diversity.cavegen.ICaveGenerator;
import diversity.cavegen.SpiderDenGenerator;
import diversity.entity.EntityDarkSpider;
import diversity.entity.EntityYeti;
import diversity.utils.EnumCubeType;
import diversity.utils.Point;
import diversity.utils.Table3d;

public class SpiderDen extends GlobalFeature
{
	public Table3d blocks = new Table3d();
	public Point startPoint;
	
	private Integer caveH = -100;
	
	public ICaveGenerator caveGen;
	public List<Point> sphereCenter = new ArrayList<Point>();	
	public int minY;

    public SpiderDen() {}
    
    public SpiderDen(Random random, int coordX, int coordZ)
    {
        super(random, coordX, coordZ, 7, 5, 9);
        Diversity.Divlogger.log(Level.INFO, coordX + " " + coordZ);
        caveGen = new SpiderDenGenerator(3, 8, 3);
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
        this.minY = minY;
    }
    
    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.setInteger("caveH", caveH);
        p_143012_1_.setInteger("startX", startPoint.x);
        p_143012_1_.setInteger("startY", startPoint.y);
        p_143012_1_.setInteger("startZ", startPoint.z);
        p_143012_1_.setInteger("minY", minY);

    }

    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        super.func_143011_b(p_143011_1_);
        caveH = p_143011_1_.getInteger("caveH");
        startPoint = new Point();
        startPoint.x = p_143011_1_.getInteger("startX");
        startPoint.y = p_143011_1_.getInteger("startY");
        startPoint.z = p_143011_1_.getInteger("startZ");
        minY = p_143011_1_.getInteger("minY");
        
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
	        		Point center = sphereCenter.get(index);
	        		if (structureBoundingBox.intersectsWith(center.x, center.z, center.x, center.z)) {
	        			if (index == 0 || index == 1) {
	        				caveH = world.getTopSolidOrLiquidBlock(center.x, center.z) - center.y - center.radius * 2;
	        			} else if (index <= 6) {
	        				caveH = world.getTopSolidOrLiquidBlock(center.x, center.z) - center.y - center.radius;
	        			} else {
	        				caveH = world.getTopSolidOrLiquidBlock(center.x, center.z) - center.y - center.radius/2;
	        			}
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
								if (blocks.containsKey(x, y-1, z) && blocks.get(x, y-1, z).equals(EnumCubeType.GROUND))
								{
									if (y - minY < 3 && random.nextInt(2)==0)
									{
										world.setBlock(x, y+caveH, z, Blocks.skull, 1, 0);
										TileEntitySkull tileEntity = (TileEntitySkull)Blocks.skull.createTileEntity(world, 0);
										tileEntity.func_145903_a(random.nextInt(8));
					        			world.setTileEntity(x, y+caveH, z, tileEntity);
									} else if (y - minY < 14 && random.nextInt((y - minY +2)/2)==0)
									{
										world.setBlock(x, y+caveH, z, Blocks.web, 1, 0);
									} else
									{
										world.setBlock(x, y+caveH, z, Blocks.air, 0, 1);
									}	
								} else if (blocks.containsKey(x, y+1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.ROOF))
								{
									if (world.getTopSolidOrLiquidBlock(x, z)-1>(y+caveH) && y - minY < 18 && random.nextInt((y - minY +4)/3)==0)
									{
										world.setBlock(x, y+caveH, z, Blocks.web, 1, 0);
									} else if (world.getTopSolidOrLiquidBlock(x, z)-1>(y+caveH) && y - minY >= 8  && random.nextInt(5)!=0)
									{
										world.setBlock(x, y+caveH, z, Blocks.leaves2, 1, 0);
									} else
									{
										world.setBlock(x, y+caveH, z, Blocks.air, 0, 1);
									}
								} else {
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
								if (world.getTopSolidOrLiquidBlock(x, z)-1!=(y+caveH)) {
									if (y - minY > 9 && random.nextInt(16)==0)
									{
										world.setBlock(x, y+caveH, z, Blocks.log2, 1, 0);
									} else {
										world.setBlock(x, y+caveH, z, Blocks.stone);
									}
								} else {
									world.setBlock(x, y+caveH, z, Blocks.grass);
								}
							}
							else if (blocks.get(x, y, z).equals(EnumCubeType.WALL))
							{
								if (world.getTopSolidOrLiquidBlock(x, z)-3>(y+caveH)) {
									world.setBlock(x, y+caveH, z, Blocks.stone);
								}
							}
							else if (blocks.get(x, y, z).equals(EnumCubeType.GROUND))
							{
								if (y - minY > 19) {
									if (world.getTopSolidOrLiquidBlock(x, z)-1==(y+caveH) && random.nextInt(4)!=0) {
										world.setBlock(x, y+caveH, z, Blocks.grass, 0, 3);
									} else {
										world.setBlock(x, y+caveH, z, Blocks.dirt, 0, 3);
									}
								} else if (y - minY > 8) {
									if (random.nextInt(3)==0) {
										world.setBlock(x, y+caveH, z, Blocks.gravel, 0, 3);
									} else {
										world.setBlock(x, y+caveH, z, Blocks.dirt, 1, 3);
									}
								} else if (y - minY > 6) {
									if (random.nextInt(3)!=0) {
										world.setBlock(x, y+caveH, z, Blocks.gravel, 0, 3);
									} else {
										world.setBlock(x, y+caveH, z, Blocks.dirt, 1, 3);
									}
								} else  if (y - minY > 5) {
									if (random.nextInt(3)==0) {
										world.setBlock(x, y+caveH, z, Blocks.gravel, 0, 3);
									} else {
										world.setBlock(x, y+caveH, z, Blocks.stone, 0, 3);
									}
								} else {
									world.setBlock(x, y+caveH, z, Blocks.stone, 0, 3);
								}
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