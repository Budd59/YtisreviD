package diversity.utils;


public class DirectionTools
{
	public static final int[][] torch = new int[4][4];	
	public static final int[][] stair = new int[4][4];	
	public static final int[][] reversed_stairs = new int[4][4];	
	public static final int[][] log = new int[4][2];	
	
	
	static {
		torch[0] = new int[] {2,1,3,4};
		torch[1] = new int[] {4,3,2,1};
		torch[2] = new int[] {2,1,4,3};
		torch[3] = new int[] {4,3,1,2};
		
		stair[0] = new int[] {0,1,3,2};
		stair[1] = new int[] {2,3,0,1};
		stair[2] = new int[] {0,1,2,3};
		stair[3] = new int[] {2,3,1,0};
		
		reversed_stairs[0] = new int[] {4,5,7,6};
		reversed_stairs[1] = new int[] {6,7,4,5};
		reversed_stairs[2] = new int[] {4,5,6,7};
		reversed_stairs[3] = new int[] {6,7,5,4};
		
		log[0] = new int[] {4,8};
		log[1] = new int[] {8,4};
		log[2] = new int[] {4,8};
		log[3] = new int[] {8,4};
	}
}
