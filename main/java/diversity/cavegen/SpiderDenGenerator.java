package diversity.cavegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import diversity.utils.EnumCubeType;
import diversity.utils.Point;
import diversity.utils.Table3d;

public class SpiderDenGenerator implements ICaveGenerator  {
	private final int minRadius;
	private final int maxRadius;
	private final int radiusRandomer;
	
	private Map<Point, List<Point>> map = new HashMap<Point, List<Point>>();

	public SpiderDenGenerator(int minRadius, int maxRadius, int radiusRandomer) {
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
		this.radiusRandomer = radiusRandomer;
	}

	@Override
	public List<Point> getControlPoints(Random random, int initX, int initY, int initZ) {
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(initX, initY, initZ, maxRadius-2));	
		list.add(new Point(initX+random.nextInt(5)-2, initY-maxRadius/3, initZ+random.nextInt(5)-2, maxRadius-3));	

		//float randX = random.nextFloat() + 0.125f;
		//float randZ = 1.625f - randX;
		
		double teta = Math.toRadians(72);
		
		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius * 2.2) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius * 2.2) + random.nextInt(3)-1;
			list.add(new Point(coloneX, initY +1 + random.nextInt(3), coloneZ, maxRadius-1));	
		}
		
		teta = Math.toRadians(30);

		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius * 3) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius * 3) + random.nextInt(3)-1;
			list.add(new Point(coloneX, initY + 2 + random.nextInt(3), coloneZ, maxRadius-3));
			
			if (random.nextInt(2)==0) {
				int counter = 6 + random.nextInt(8);
				for (int counetIndex = 1; counetIndex <= counter; counetIndex++) {
					double tetaC = tempTeta + Math.toRadians(random.nextInt(10))*(random.nextBoolean()?-1:1);
					int coloneXC = coloneX + (int) (Math.cos(tetaC)* maxRadius/2 * counetIndex);
					int coloneZC = coloneZ + (int) (Math.sin(tetaC)* maxRadius/2 * counetIndex);
					list.add(new Point(coloneXC, initY + 3 + counetIndex + random.nextInt(3), coloneZC, maxRadius-4));
				}
			}
		}
		return list;
	}
	
	@Override
	public Table3d getCavePoints(List<Point> sphereCenter, Random random)
	{
		Table3d blocks = new Table3d();
		for(Point center : sphereCenter)
		{
			int x = center.x;
			int y = center.y;
			int z = center.z;
			int radius = center.radius;
	
			int minY = -radius;
			int maxY = radius;
			
			int minX = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxX = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			
			int minZ = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxZ = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);

			if (center.radius == maxRadius || center.radius == maxRadius-2) {
				for (int tempY = maxY/2; tempY >= minY/2; tempY--)
				for (int tempX = minX*2; tempX <= maxX*2; tempX++)
				for (int tempZ = minZ*2; tempZ <= maxZ*2; tempZ++)
				{
					if (Math.pow(tempX/2, 2.0D) + Math.pow(tempY*2, 2.0D) + Math.pow(tempZ/2, 2.0D) < Math.pow(radius, 2.0D))
					{
						blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
					}
				}
			} else if (center.radius == maxRadius-1) {
				for (double tempY = maxY/2; tempY >= minY; tempY--)
				for (int tempX = minX; tempX <= maxX; tempX++)
				for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
				{
					if (tempY > 0) {
						if (Math.pow(tempX, 2.0D) + Math.pow(tempY*2, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
						{
							blocks.put(x + tempX, y + (int)tempY, z + tempZ, EnumCubeType.AIR);
						}
					} else {
						if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
						{
							blocks.put(x + tempX, y + (int)tempY, z + tempZ, EnumCubeType.AIR);
						}
					}
				}
			} else if (center.radius == maxRadius-3) {
				for (int tempY = maxY; tempY >= minY; tempY--)
				for (int tempX = minX; tempX <= maxX; tempX++)
				for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
				{
					if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
					{
						blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
					}
				}
			} else if (center.radius == maxRadius-4) {
				int deformer = random.nextInt(3)==0?2:1;
				for (int tempY = maxY*deformer; tempY >= minY; tempY--)
				for (int tempX = minX; tempX <= maxX; tempX++)
				for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
				{
					if (tempY>0) {
						if (Math.pow(tempX, 2.0D) + Math.pow(tempY/deformer, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
						{
							blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
						}
					} else
					{
						if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
						{
							blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
						}
					}
				}
			}
		}
		return blocks;
	}
	
	@Override
	public void generateBlockType(Random random, Table3d blocks, int waterLevel)
	{
		for (Integer y : blocks.descendingKeySet())
		for (Integer x : blocks.rowKeySet(y))
		for (Integer z : blocks.columnKeySet(y))
		{
			if (blocks.containsKey(x, y, z))
			{
				if (!blocks.containsKey(x, y+1, z) && blocks.containsKey(x+1, y-1, z) && blocks.containsKey(x-1, y-1, z) && blocks.containsKey(x, y-1, z+1) && blocks.containsKey(x, y-1, z-1)) {
					blocks.put(x, y, z, EnumCubeType.ROOF);
				} else if (!blocks.containsKey(x+1, y, z) || !blocks.containsKey(x-1, y, z) || !blocks.containsKey(x, y, z+1) || !blocks.containsKey(x, y, z-1)) {
					if (blocks.containsKey(x, y+1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.ROOF) && blocks.containsKey(x+1, y-1, z) && blocks.containsKey(x-1, y-1, z) && blocks.containsKey(x, y-1, z+1) && blocks.containsKey(x, y-1, z-1)) {
						blocks.put(x, y, z, EnumCubeType.ROOF);
					} else if (blocks.containsKey(x, y+1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.AIR)) {
						blocks.put(x, y, z, EnumCubeType.GROUND);
					} else if (blocks.containsKey(x, y+1, z) &&
							(blocks.get(x, y+1, z).equals(EnumCubeType.GROUND) || blocks.get(x, y+1, z).equals(EnumCubeType.UNDERGROUND))
						 && (!blocks.containsKey(x, y+3, z) || !blocks.get(x, y+2, z).equals(EnumCubeType.UNDERGROUND) || !blocks.get(x, y+2, z).equals(EnumCubeType.GROUND))) {
						blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
					} else {
						blocks.put(x, y, z, EnumCubeType.WALL);
					}
				} else if (!blocks.containsKey(x, y-1, z)) {
					if (y < waterLevel) {
						blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
					} else {
						blocks.put(x, y, z, EnumCubeType.GROUND);
					}
				}
			}
		}
	}
}