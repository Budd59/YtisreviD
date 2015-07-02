package diversity.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import diversity.MapGenCaveDiversity;
import diversity.MapGenStructureDiversity;
import diversity.MapGenVillageDiversity;
import diversity.entity.EntityWorshipper;
import diversity.suppliers.EnumBlock;
import diversity.world.WorldGenBlueMushroom;
import diversity.world.WorldGenBlueVine;
import diversity.world.WorldGenFungus;
import diversity.world.WorldGenPhosMushroom;

public class ServerHandler
{
	public final static MapGenVillageDiversity mapGenVillageDiversity = new MapGenVillageDiversity();
	public final static MapGenStructureDiversity mapGenStructureDiversity = new MapGenStructureDiversity();
	public final static MapGenCaveDiversity mapGenCaveStructureDiversity = new MapGenCaveDiversity();
	
	@SubscribeEvent
	public void checkUpdate(InitMapGenEvent event)
	{
		if (EventType.VILLAGE == event.type) {
			event.newGen = mapGenVillageDiversity;
		} else if (EventType.SCATTERED_FEATURE == event.type) {
			event.newGen = mapGenStructureDiversity;
		}
	}
    
	@SubscribeEvent
    public void OnSpawnEntity(CheckSpawn event) {
    	int x = (int) event.x;
    	int y = (int) event.y;
    	int z = (int) event.z;

    	boolean flag = false;
    	for (int tempX = -1; tempX <= 1; tempX++)
        for (int tempZ = -1; tempZ <= 1; tempZ++)
        {
        	int tempY = y;
        	while (event.world.getBlock(x, tempY, z).equals(Blocks.air) || event.world.getBlock(x, tempY, z) instanceof BlockFalling) {
        		tempY--;
        	}
	    	if (event.world.getBlock(x, tempY, z).equals(EnumBlock.fungal.block)
	    			|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_mushroom_cap.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_mushroom_cap.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_water.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_mushroom.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_mushroom.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_vine.block)) {
	    		flag = true;
	    	}
        }
    	if (flag) {
			if (event.entity instanceof EntityWitch || event.entity instanceof EntityWorshipper) {
				event.setResult(Result.ALLOW);
			} else {
				event.setResult(Result.DENY);
			}
    	} else if (event.entity instanceof EntityWorshipper) {
			event.setResult(Result.DENY);
    	}
    	
    	if (event.entity instanceof EntitySpider)
    	{
	    	for (int tempX = -1; tempX <= 1; tempX++)
	        for (int tempZ = -1; tempZ <= 1; tempZ++)
            {
    	    	if (event.world.getBlock(x, y, z).equals(Blocks.web)) {
    	    		event.setResult(Result.ALLOW);
    	    	}
            }
		}
    }

	private WorldGenerator blueMushroom = new WorldGenBlueMushroom();
	private WorldGenerator phosMushroom = new WorldGenPhosMushroom();
	private WorldGenerator vineMushroom = new WorldGenBlueVine();
	private WorldGenerator fungusGen = new WorldGenFungus(1,1);
	
	public static List<Integer[]> listMushroomChunk = new ArrayList<Integer[]>();
	
	@SubscribeEvent
	public void OnDecorate(Decorate event)
    {
		if (event.type == Decorate.EventType.BIG_SHROOM || event.type == Decorate.EventType.SHROOM || event.type == Decorate.EventType.GRASS)

		for (Integer[] chunkP : listMushroomChunk) {
			if (event.chunkX == chunkP[0] && event.chunkZ == chunkP[1])
				if (event.type == Decorate.EventType.BIG_SHROOM) {
			        for (int j = 0; j < 3; ++j)
			        {
			            int x = event.chunkX + event.rand.nextInt(16) + 8;
			            int z = event.chunkZ + event.rand.nextInt(16) + 8;
			            for (int y = 20; y<50; y++) {
			            	if (event.world.getBlock(x, y, z).equals(EnumBlock.fungal.block)) {
			            		if (event.rand.nextInt(8)==0) {
			            			phosMushroom.generate(event.world, event.rand, x, y+1, z);
			            		}
			            		else {
			            			blueMushroom.generate(event.world, event.rand, x, y+1, z);
			            		}
			            		break;
			            	}
			            }
			        }
				} else if (event.type == Decorate.EventType.SHROOM) {
			        for (int j = 0; j < 10; ++j)
			        {
			        	int x = event.chunkX + event.rand.nextInt(16) + 8;
			            int z = event.chunkZ + event.rand.nextInt(16) + 8;
			            for (int y = 20; y<50; y++) {
			            	if (event.world.getBlock(x, y, z).equals(EnumBlock.fungal.block) && event.world.getBlock(x, y+1, z).equals(Blocks.air)) {
			            		if (event.rand.nextInt(8)==0) {
				            		event.world.setBlock(x, y+1, z, EnumBlock.phos_mushroom.block);
			            		}
			            		else {
				            		event.world.setBlock(x, y+1, z, EnumBlock.blue_mushroom.block);
			            		}
			            	}
			            }
			        }
				} else if (event.type == Decorate.EventType.GRASS) {
			        for (int tempX = 8; tempX < 24; ++tempX)
				    for (int tempZ = 8; tempZ < 24; ++tempZ)
			        {
			        	int x = event.chunkX + tempX;
			            int z = event.chunkZ + tempZ;
			            
			            for (int tempY = 30; tempY < 60; tempY++) {
			            	if (	event.world.getBlock(x, tempY, z).getMaterial().equals(Material.air)
			            			&& event.world.getBlock(x, tempY-1, z).getMaterial().equals(Material.air)
			            			&& event.world.getBlock(x, tempY+1, z).getMaterial().equals(Material.air)
			            			&& (	event.world.getBlock(x+1, tempY, z).equals(Blocks.stone)
			            					|| event.world.getBlock(x-1, tempY, z).equals(Blocks.stone)
			            					|| event.world.getBlock(x, tempY, z+1).equals(Blocks.stone)
			            					|| event.world.getBlock(x, tempY, z-1).equals(Blocks.stone)
			            				)
			            			&& event.rand.nextInt(40)==0
			            			)
			            	{
			            		List<Integer[]> solution = new ArrayList<Integer[]>();
			            		if (tempX-1>=8)
			            			solution.add(new Integer[]{x-1,tempY,z});
			            		if (tempX+1<24)
			            			solution.add(new Integer[]{x+1,tempY,z});
			            		if (tempZ-1>=8)
			            			solution.add(new Integer[]{x,tempY,z-1});
			            		if (tempZ+1<24)
			            			solution.add(new Integer[]{x,tempY,z+1});
			            		Collections.shuffle(solution);
			            		while (!solution.isEmpty() && !event.world.getBlock(solution.get(0)[0],solution.get(0)[1],solution.get(0)[2]).getMaterial().equals(Material.rock)) {
			            			solution.remove(0);
			            		}
			            		if (!solution.isEmpty())
			            			fungusGen.generate(event.world, event.rand, solution.get(0)[0], solution.get(0)[1], solution.get(0)[2]);
			            		solution.clear();
			            	}
			            }
			            
			            if (event.rand.nextInt(5)==0) {
				            for (int tempY = 30; tempY < 60; tempY++) {
				            	if (event.world.getBlock(x, tempY, z).equals(Blocks.air) && event.world.getBlock(x, tempY+1, z).getMaterial().equals(Material.rock))
				            	{
				            		vineMushroom.generate(event.world, event.rand, x, tempY, z);
				            	}
				            }
			            }
					}
				}
		}
    }
	
	@SubscribeEvent
    public void OnPOstDecorate(DecorateBiomeEvent.Post event) {
		for (Integer[] chunkP : listMushroomChunk) {
			if (event.chunkX == chunkP[0] && event.chunkZ == chunkP[1]) {
				listMushroomChunk.remove(chunkP);
				return;
			}
		}
	}
}
