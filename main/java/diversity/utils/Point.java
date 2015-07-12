package diversity.utils;

public class Point {

	public int x;
	public int y;
	public int z;
	public int radius;
	
	public Point() {}
	
	public Point(int x, int y, int z, int radius) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.radius = radius;
	}
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public void set(int x, int z)
	{
		this.x = x;
		this.z = z;
	}
}
