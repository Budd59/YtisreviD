package diversity.cavegen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import diversity.utils.EnumCubeType;
import diversity.utils.Point;
import diversity.utils.Table3d;

public class JungleValleyGenerator implements ICaveGenerator {
	private final int minRadius;
	private final int maxRadius;
	private final int radiusRandomer;

	public JungleValleyGenerator(int minRadius, int maxRadius, int radiusRandomer) {
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
		this.radiusRandomer = radiusRandomer;
	}
	
	@Override
	public List<Point> getControlPoints(Random random, int initX, int initY, int initZ) {
		List<Point> list = new ArrayList<Point>();

		float randX = random.nextFloat() + 0.5f;
		float randZ = 2f - randX;
		
		int coloneY = initY - 9;
		int coloneRadius = maxRadius / 2;
		//double teta = 2*Math.asin((double)(1/4));
		double teta = Math.toRadians(28.95502438);
		
		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius * 1.3 *randX) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius * 1.3 *randZ) + random.nextInt(3)-1;
			list.add(new Point(coloneX, coloneY + random.nextInt(3)-1, coloneZ, coloneRadius));	
		}

		coloneY = coloneY - 5;
		coloneRadius = maxRadius;
		//teta = 2*Math.asin(1/5);
		teta = Math.toRadians(25);

		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius*2*randX) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius*2*randZ) + random.nextInt(3)-1;
			list.add(new Point(coloneX, coloneY + random.nextInt(3)-1, coloneZ, coloneRadius));	
		}

		coloneY = coloneY - 3;
		coloneRadius = maxRadius / 2-1;
		//teta = 2*Math.asin(1/5);
		teta = Math.toRadians(20);

		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius*3*randX) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius*3*randZ) + random.nextInt(3)-1;
			list.add(new Point(coloneX, coloneY + random.nextInt(3)-1, coloneZ, coloneRadius));	
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
			
			if (center.radius == maxRadius/2-1) {
				for (int tempY = maxY; tempY >= minY; tempY--)
				for (int tempX = minX*2; tempX <= maxX*2; tempX++)
				for (int tempZ = minZ*2; tempZ <= maxZ*2; tempZ++)
				{
					if (Math.pow(tempX/2, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ/2, 2.0D) < Math.pow(radius, 2.0D))
					{
						blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
					}
				}
			} else {
				for (int tempY = maxY; tempY >= minY; tempY--)
				for (int tempX = minX; tempX <= maxX; tempX++)
				for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
				{
					if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
					{
						blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
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
						if (y < waterLevel) {
							blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
						} else {
							blocks.put(x, y, z, EnumCubeType.GROUND);
						}
					} else if (blocks.containsKey(x, y+1, z) &&
							(blocks.get(x, y+1, z).equals(EnumCubeType.GROUND) || blocks.get(x, y+1, z).equals(EnumCubeType.UNDERGROUND))
						 && (!blocks.containsKey(x, y+3, z) || !blocks.get(x, y+2, z).equals(EnumCubeType.UNDERGROUND) || !blocks.get(x, y+2, z).equals(EnumCubeType.GROUND))) {
						blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
					} else {
						if (y <= waterLevel) {
							blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
						} else {
							blocks.put(x, y, z, EnumCubeType.WALL);
						}
					}
				} else if (!blocks.containsKey(x, y-1, z)) {
					if (y < waterLevel) {
						blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
					} else {
						blocks.put(x, y, z, EnumCubeType.GROUND);
					}
				} else if (y <= waterLevel) {
					blocks.put(x, y, z, EnumCubeType.WATER);
				}
			}
		}
	}
}
