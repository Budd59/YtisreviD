package diversity.cavegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.vecmath.Point4i;

import diversity.utils.EnumCubeType;
import diversity.utils.Table3d;

public class SpiderDenGenerator implements ICaveGenerator  {
	private final int minRadius;
	private final int maxRadius;
	private final int radiusRandomer;
	
	private Map<Point4i, List<Point4i>> map = new HashMap<Point4i, List<Point4i>>();

	public SpiderDenGenerator(int minRadius, int maxRadius, int radiusRandomer) {
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
		this.radiusRandomer = radiusRandomer;
	}

	@Override
	public List<Point4i> getControlPoints(Random random, int initX, int initY, int initZ) {
		List<Point4i> list = new ArrayList<Point4i>();
		list.add(new Point4i(initX, initY, initZ, maxRadius));	

		float randX = random.nextFloat() + 0.125f;
		float randZ = 1.625f - randX;
		
		double teta = Math.toRadians(59);
		
		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius * 1.3 *randX) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius * 1.3 *randZ) + random.nextInt(3)-1;
			list.add(new Point4i(coloneX, initY + random.nextInt(3)-1, coloneZ, maxRadius/2));	
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
			for (int tempX = minX*2; tempX <= maxX*2; tempX++)
			for (int tempZ = minZ*2; tempZ <= maxZ*2; tempZ++)
			{
				if (Math.pow(tempX/2, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ/2, 2.0D) < Math.pow(radius, 2.0D))
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