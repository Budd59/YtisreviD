package diversity.cavegen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import diversity.Diversity;
import diversity.utils.EnumCubeType;
import diversity.utils.Point;
import diversity.utils.Table3d;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class DwarvenCaveGenerator implements ICaveGenerator
{
	private final int minRadius;
	private final int maxRadius;
	private final int radiusRandomer;

	public DwarvenCaveGenerator(int minRadius, int maxRadius, int radiusRandomer) {
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
		this.radiusRandomer = radiusRandomer;
	}
	
	@Override
	public List<Point> getControlPoints(Random random, int initX, int initY, int initZ) {
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(initX, initY, initZ, maxRadius));	
		
		int coloneY = initY - maxRadius + 5;
		int coloneRadius = maxRadius / 2;
		//double teta = 2*Math.asin((double)(1/4));
		double teta = Math.toRadians(28.95502438);

		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius) + random.nextInt(3)-1;
			list.add(new Point(coloneX, coloneY + random.nextInt(3)-1, coloneZ, coloneRadius));	
		}

		coloneY = coloneY - coloneRadius;
		coloneRadius = maxRadius / 2 - 1;
		//teta = 2*Math.asin(1/5);
		teta = Math.toRadians(23.07391806);

		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius) + random.nextInt(3)-1;
			int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius) + random.nextInt(3)-1;
			list.add(new Point(coloneX, coloneY + random.nextInt(3)-1, coloneZ, coloneRadius));	
		}
		
		coloneY = initY -2;
		coloneRadius = maxRadius / 2;
		//teta = 2*Math.asin(3/8));
		//teta = 30.43691032;
		teta = Math.toRadians(44.04862568);

		for (double tempTeta = 0; tempTeta < 2*Math.PI; tempTeta = tempTeta + teta) {
			if (random.nextInt(10)!=0) {
				int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius*2.3) + random.nextInt(3)-1;
				int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius*2.3) + random.nextInt(3)-1;
				
				int tempColoneRadius = maxRadius / 3;
				//double tetaColone = 2*Math.asin(maxRadius/(3*maxRadius+6));
				double tetaColone = Math.toRadians(35.27575900);
				
				for (double tempTetaColone = 0; tempTetaColone < 2*Math.PI; tempTetaColone = tempTetaColone + tetaColone) {
					int tempColoneX = coloneX + (int) (Math.cos(tempTetaColone)* coloneRadius) + random.nextInt(3)-1;
					int tempColoneZ = coloneZ + (int) (Math.sin(tempTetaColone)* coloneRadius) + random.nextInt(3)-1;
					
					list.add(new Point(tempColoneX, coloneY + random.nextInt(3)-1, tempColoneZ, tempColoneRadius));	
				}
			}
			if (random.nextInt(7)==0) {
				int coloneX = initX + (int) (Math.cos(tempTeta)* maxRadius*3) + random.nextInt(3)-1;
				int coloneZ = initZ + (int) (Math.sin(tempTeta)* maxRadius*3) + random.nextInt(3)-1;
				
				int tempColoneRadius = maxRadius / 3;
				//double tetaColone = 2*Math.asin(maxRadius/(3*maxRadius+6));
				double tetaColone = Math.toRadians(35.27575900);
				
				for (double tempTetaColone = Math.toRadians(35.27575900/2); tempTetaColone < 2*Math.PI+Math.toRadians(35.27575900/2); tempTetaColone = tempTetaColone + tetaColone) {
					int tempColoneX = coloneX + (int) (Math.cos(tempTetaColone)* coloneRadius) + random.nextInt(3)-1;
					int tempColoneZ = coloneZ + (int) (Math.sin(tempTetaColone)* coloneRadius) + random.nextInt(3)-1;
					
					list.add(new Point(tempColoneX, coloneY + random.nextInt(3)-1, tempColoneZ, tempColoneRadius));	
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
			
			if (radius==maxRadius) {
				for (int tempY = maxY; tempY >= minY; tempY--)
				for (int tempX = minX*2; tempX <= maxX*2; tempX++)
				for (int tempZ = minZ*2; tempZ <= maxZ*2; tempZ++)
				{
					if (Math.pow(tempX/2, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ/2, 2.0D) < Math.pow(radius, 2.0D)
							&& ((y+tempY) >= 30 || ((y+tempY) >= 12
									&& Math.pow(x+tempX-sphereCenter.get(0).x, 2.0D) + Math.pow(z+tempZ-sphereCenter.get(0).z, 2.0D) + Math.pow(y+tempY-sphereCenter.get(0).z, 2.0D) > Math.pow(sphereCenter.get(0).radius*2/3, 2.0D)
									&& Math.pow(x+tempX-sphereCenter.get(0).x, 2.0D) + Math.pow(z+tempZ-sphereCenter.get(0).z, 2.0D) > Math.pow(sphereCenter.get(0).radius*2/3, 2.0D))))
					{
						blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
					}
				}
			} else if (radius==maxRadius/2 || radius== maxRadius/2-1) {
				for (int tempY = maxY; tempY >= minY; tempY--)
				for (int tempX = minX; tempX <= maxX; tempX++)
				for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
				{
					if (Math.pow(tempX, 2.0D) + Math.pow(tempY, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D)
							&& ((y+tempY) >= 30 || ((y+tempY) >= 12
									&& Math.pow(x+tempX-sphereCenter.get(0).x, 2.0D) + Math.pow(z+tempZ-sphereCenter.get(0).z, 2.0D) + Math.pow(y+tempY-sphereCenter.get(0).z, 2.0D) > Math.pow(sphereCenter.get(0).radius/2, 2.0D)
									//&& Math.pow(x+tempX-sphereCenter.get(0).x, 2.0D) + Math.pow(z+tempZ-sphereCenter.get(0).z, 2.0D) > Math.pow(sphereCenter.get(0).w*2/3, 2.0D))))
							)))
					{
						blocks.put(x + tempX, y + tempY, z + tempZ, EnumCubeType.AIR);
					}
				}
			} else if (radius==maxRadius/3) {
				for (int tempY = maxY*2; tempY >= minY*2; tempY--)
				for (int tempX = minX; tempX <= maxX; tempX++)
				for (int tempZ = minZ; tempZ <= maxZ; tempZ++)
				{
					if (Math.pow(tempX, 2.0D) + Math.pow(tempY/2, 2.0D) + Math.pow(tempZ, 2.0D) < Math.pow(radius, 2.0D))
							//&& ((y+tempY) >= 30 || ((y+tempY) >= 12
									//&& Math.pow(x+tempX-sphereCenter.get(0).x, 2.0D) + Math.pow(z+tempZ-sphereCenter.get(0).z, 2.0D) + Math.pow(y+tempY-sphereCenter.get(0).z, 2.0D) > Math.pow(sphereCenter.get(0).w, 2.0D)
									//&& Math.pow(x+tempX-sphereCenter.get(0).x, 2.0D) + Math.pow(z+tempZ-sphereCenter.get(0).z, 2.0D) > Math.pow(sphereCenter.get(0).w*2/3, 2.0D))))
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
							if (random.nextInt(15) == 0 && !blocks.containsKey(x, y-1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.WATER)) {
								blocks.put(x, y, z, EnumCubeType.ORE);
							} else {
								blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
							}
						} else {
							blocks.put(x, y, z, EnumCubeType.GROUND);
						}
					} else if (blocks.containsKey(x, y+1, z) && (blocks.get(x, y+1, z).equals(EnumCubeType.GROUND) || blocks.get(x, y+1, z).equals(EnumCubeType.UNDERGROUND)) && (!blocks.containsKey(x, y+3, z) || !blocks.get(x, y+2, z).equals(EnumCubeType.UNDERGROUND) || !blocks.get(x, y+2, z).equals(EnumCubeType.GROUND))) {
						if (random.nextInt(15) == 0 && !blocks.containsKey(x, y-1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.WATER)) {
							blocks.put(x, y, z, EnumCubeType.ORE);
						} else {
							blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
						}
					} else {
						if (y>=15 && y<=45 && random.nextInt((y-15)/5+4)==0) {
							blocks.put(x, y, z, EnumCubeType.ORE);
						} else {
							blocks.put(x, y, z, EnumCubeType.WALL);
						}
					}
				} else if (!blocks.containsKey(x, y-1, z))
				{
					if (y < waterLevel) {
						if (random.nextInt(15) == 0 && !blocks.containsKey(x, y-1, z) && blocks.get(x, y+1, z).equals(EnumCubeType.WATER)) {
							blocks.put(x, y, z, EnumCubeType.ORE);
						} else {
							blocks.put(x, y, z, EnumCubeType.UNDERGROUND);
						}
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
