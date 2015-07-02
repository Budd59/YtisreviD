package diversity.cavegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.vecmath.Point4i;

import diversity.utils.EnumCubeType;
import diversity.utils.Table3d;

public class YetiDenGenerator implements ICaveGenerator {
	private final int minRadius;
	private final int maxRadius;
	private final int radiusRandomer;
	
	private Map<Point4i, List<Point4i>> map = new HashMap<Point4i, List<Point4i>>();

	public YetiDenGenerator(int minRadius, int maxRadius, int radiusRandomer) {
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
		this.radiusRandomer = radiusRandomer;
	}

	@Override
	public List<Point4i> getControlPoints(Random random, int initX, int initY, int initZ) {
		List<Point4i> list = new ArrayList<Point4i>();
		list.add(new Point4i(initX, initY, initZ, maxRadius-1));	
		
		int tempX = 0;
		int tempY = 0;
		int tempZ = 0;
		int counter = 10;
		
		double teta = 2 * Math.PI * random.nextFloat();
		
		while (counter > 0)
		{
			teta+=Math.PI * (1/4-random.nextFloat()/2);
			tempX+=(maxRadius-2) * Math.cos(teta) - random.nextInt(2);
			tempY+=2;
			tempZ+=(maxRadius-2) * Math.sin(teta) - random.nextInt(2);

			list.add(new Point4i(initX+tempX, initY+tempY, initZ+tempZ, maxRadius-2));
			counter--;
		}
				
		return list;
	}
	
	@Override
	public Table3d getCavePoints(List<Point4i> sphereCenter, Random random)
	{
		Table3d blocks = new Table3d();
		for(Point4i center : sphereCenter)
		{
			int x = center.x;
			int y = center.y;
			int z = center.z;
			int radius = center.w;
	
			int minY = -radius;
			int maxY = radius;
			
			int minX = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxX = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			
			int minZ = -radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);
			int maxZ = radius - radiusRandomer / 2 + random.nextInt(radiusRandomer + 1);

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