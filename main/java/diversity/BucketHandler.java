package diversity;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class BucketHandler
{
	public final static Map<Block, Item> buckets = new HashMap<Block, Item>();
	
	@SubscribeEvent	
	public void onBucketFill(FillBucketEvent event) {
		ItemStack result = fillCustomBucket(event.world, event.target);
		
		if (result == null)
			return;

		event.result = result;
		event.setResult(Result.ALLOW);
	}

	public ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
		Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

		if (world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) 
		{
			for (Block bukketBlock : buckets.keySet()) {
				if (block.equals(bukketBlock)) {
					world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
					return new ItemStack(buckets.get(bukketBlock));
				}
			}
		}
		return null;
	}

}
