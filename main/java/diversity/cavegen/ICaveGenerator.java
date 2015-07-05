package diversity.cavegen;

import java.util.List;
import java.util.Random;

import diversity.utils.Point;
import diversity.utils.Table3d;

public interface ICaveGenerator
{
	public List<Point> getControlPoints(Random random, int initX, int initY, int initZ);
	
	public Table3d getCavePoints(List<Point> sphereCenter, Random random);
	
	public void generateBlockType(Random random, Table3d blocks, int waterLevel);
}
