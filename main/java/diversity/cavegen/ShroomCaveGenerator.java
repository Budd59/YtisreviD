package diversity.cavegen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.vecmath.Point4i;

import diversity.utils.EnumCubeType;
import diversity.utils.Table3d;

public class ShroomCaveGenerator implements ICaveGenerator {
	private final int minRadius;
	private final int maxRadius;
	private final int radiusRandomer;

	public ShroomCaveGenerator(int minRadius, int maxRadius, int radiusRandomer) {
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
		this.radiusRandomer = radiusRandomer;
	}
	
	@Override
	public List<Point4i> getControlPoints(Random random, int initX, int initY, int initZ) {
		List<Point4i> list = new ArrayList<Point4i>();
		list.add(new Point4i(initX, initY, initZ, maxRadius));	
		
		int coloneY = initY - 3;
		int coloneRadius = maxRadius;
		double teta = Math.toRadians(90);
		
		for (double tempTeta = 0.5*Math.PI; tempTeta <= 2.5*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius*2.5) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius*2.5) + random.nextInt(3)-1;
			list.add(new Point4i(coloneX, coloneY + random.nextInt(3)-1, coloneZ, coloneRadius));	
		}
		
		
		
		
		coloneY = initY + maxRadius/4 - 3;
		coloneRadius = maxRadius / 2 + 1;
		teta = Math.toRadians(35);
		
		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius/2.2) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius/2.2) + random.nextInt(3)-1;
			list.add(new Point4i(coloneX, coloneY + random.nextInt(3)-1, coloneZ, coloneRadius));	
		}
		
		coloneY = initY;
		
		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius*1.7) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius*1.7) + random.nextInt(3)-1;
			list.add(new Point4i(coloneX, coloneY + random.nextInt(7) - 1, coloneZ, coloneRadius));	
		}
		
		coloneY = initY- maxRadius/4+2-1;
		teta = Math.toRadians(45);
		double tetaColone = Math.toRadians(35.27575900);

		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			if (random.nextInt(10)!=0) {
				int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius*2.7) + random.nextInt(3)-1;
				int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius*2.7) + random.nextInt(3)-1;
				
				
				for (double tempTetaColone = 0; tempTetaColone < 2*Math.PI; tempTetaColone = tempTetaColone + tetaColone) {
					int tempColoneX = coloneX + (int) (Math.cos(tempTetaColone)* coloneRadius*1.7) + random.nextInt(3)-1;
					int tempColoneZ = coloneZ + (int) (Math.sin(tempTetaColone)* coloneRadius*1.7) + random.nextInt(3)-1;
					
					if (random.nextInt(10)!=0) {
						list.add(new Point4i(tempColoneX, coloneY + random.nextInt(7)-4, tempColoneZ, coloneRadius));
					}
				}
			}
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
			
			if (sphereCenter.get(0).equals(center)) {
				for (int tempY = maxY; tempY >= minY/2; tempY--)
					for (int tempX = minX; tempX <= maxX; tempX++)
					for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
					{
						if (Math.pow(tempX, 2.0D) + Math.pow(tempY*2, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
						{
							blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
						}
					}
			} else if (center.w == maxRadius/2+1) {
				for (double tempY = maxY*1.5; tempY >= minY; tempY--)
				for (int tempX = minX; tempX <= maxX; tempX++)
				for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
				{
					if ((tempY<0 && Math.pow(tempX/2, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D)) ||  Math.pow(tempX/2, 2.0D) + Math.pow(tempY/1.5, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
					{
						blocks.put(x + tempX, y + (int)(tempY), z + tempZ, EnumCubeType.AIR);
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
