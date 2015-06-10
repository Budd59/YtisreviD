package diversity.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRoot extends WorldGenerator
{
	private Block block;
	private int metadata;
	
	private final double switcher = Math.cos(45);
	
	public WorldGenRoot(Block block, int metadata) {
		super();
		this.block = block;
		this.metadata = metadata;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) 
	{
		int tempY = 0;
		
		double angleY = 270;
		double angleXZ = random.nextInt(360);
		do {
			world.setBlock(x, y - tempY, z, block, metadata, 3);
			angleY += 10 * (random.nextInt(2) - random.nextInt(2));
			double ecart = Math.cos(angleY);
			double tempX = Math.cos(angleXZ);
			double tempZ = Math.sin(angleXZ);
			
			if (Math.abs(ecart) < switcher) {
				tempX = 0;
			} else {
				tempX = Math.abs(tempX)/tempX;
			}
			if (Math.abs(ecart) < switcher) {
				tempZ = 0;
			} else {
				tempZ = Math.abs(tempZ)/tempZ;
			}
			x += tempX;
			z += tempZ;
			tempY++;
		}
		while (world.getBlock(x, y-tempY, z).getMaterial().equals(Material.wood)
				|| world.getBlock(x, y-tempY, z).getMaterial().isLiquid()
				|| world.getBlock(x, y-tempY, z).getMaterial().equals(Material.air)
				|| world.getBlock(x, y-tempY, z).equals(Blocks.web)
				|| world.getBlock(x, y-tempY, z).getMaterial().equals(Material.grass)
				|| world.getBlock(x, y-tempY, z).getMaterial().equals(Material.ground));
		
		return true;
	}
}