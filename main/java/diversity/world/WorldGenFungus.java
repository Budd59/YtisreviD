package diversity.world;

import java.util.Random;

import diversity.suppliers.EnumBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFungus extends WorldGenerator {

	private int iwidth;
	private int kwidth;
	
	public WorldGenFungus (int iwidth, int kwidth) {
		this.iwidth = iwidth;
		this.kwidth = kwidth;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		iwidth = (iwidth*2) -1;
		kwidth = (kwidth*2) -1;

		boolean flag = false;
		
		for( int tempX = iwidth; tempX !=-iwidth; tempX-=iwidth){
			for( int tempZ = kwidth; tempZ !=-kwidth; tempZ-=kwidth){
				if(world.getBlock(x + tempX, y, z + tempZ).equals(Blocks.air))
				{
					world.setBlock(x + tempX, y, z + tempZ, EnumBlock.fungus.block);
					flag = true;
				}
			}
		}
		return flag;
	}
}
