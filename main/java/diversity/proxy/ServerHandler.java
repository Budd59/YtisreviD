package diversity.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import diversity.MapGenCaveDiversity;
import diversity.MapGenStructureDiversity;
import diversity.MapGenVillageDiversity;
import diversity.configurations.AConfigTool;
import diversity.configurations.ConfigGlobal;
import diversity.entity.EntityWorshipper;
import diversity.suppliers.EnumBlock;
import diversity.suppliers.EnumCave;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumStructure;
import diversity.suppliers.EnumVillage;
import diversity.world.WorldGenBlueMushroom;
import diversity.world.WorldGenBlueVine;
import diversity.world.WorldGenFungus;
import diversity.world.WorldGenPhosMushroom;

public class ServerHandler
{
	public final static MapGenVillageDiversity mapGenVillageDiversity = new MapGenVillageDiversity();
	public final static MapGenStructureDiversity mapGenStructureDiversity = new MapGenStructureDiversity();
	public final static MapGenCaveDiversity mapGenCaveStructureDiversity = new MapGenCaveDiversity();
	public static MapGenStructure mapGenScatteredFeature;
	public static MapGenStructure mapGenVillage;
	
	@SubscribeEvent
	public void OnInitMapGen(InitMapGenEvent event)
	{
		if (EventType.VILLAGE == event.type) {
			mapGenVillage = (MapGenStructure) event.originalGen;
			event.newGen = mapGenVillageDiversity;
		} else if (EventType.SCATTERED_FEATURE == event.type) {
			mapGenScatteredFeature = (MapGenStructure) event.originalGen;
			event.newGen = mapGenStructureDiversity;
		}
	}
	
	@SubscribeEvent
	public void OnWorldUnload(WorldEvent.Unload event) {
		AConfigTool.loadAllConfig(false);
		AConfigTool.saveAllConfig(false);
    	if (ConfigGlobal.REMOVE_VANILLA_SPAWN_EGG.equals("true")) {
    		if (EntityList.entityEggs.containsKey(Integer.valueOf(120))) {
    			EntityList.entityEggs.remove(Integer.valueOf(120));
    		}
    	} else {
    		if (!EntityList.entityEggs.containsKey(Integer.valueOf(120))) {
    			EntityList.entityEggs.put(Integer.valueOf(120), new EntityList.EntityEggInfo(120, 5651507, 12422002));
    		}
    	}
	}
	
	@SubscribeEvent
	public void OnWorldLoad(WorldEvent.Load event) {
		AConfigTool.loadAllConfig(true);
		AConfigTool.saveAllConfig(true);
		EnumStructure.load();
		EnumCave.load();
    	EnumVillage.load();
    	if (ConfigGlobal.REMOVE_VANILLA_SPAWN_EGG.equals("true")) {
    		if (EntityList.entityEggs.containsKey(Integer.valueOf(120))) {
    			EntityList.entityEggs.remove(Integer.valueOf(120));
    		}
    	} else {
    		if (!EntityList.entityEggs.containsKey(Integer.valueOf(120))) {
    			EntityList.entityEggs.put(Integer.valueOf(120), new EntityList.EntityEggInfo(120, 5651507, 12422002));
    		}
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

	private final WorldGenerator blueMushroom = new WorldGenBlueMushroom();
	private final WorldGenerator phosMushroom = new WorldGenPhosMushroom();
	private final WorldGenerator vineMushroom = new WorldGenBlueVine();
	private final WorldGenerator fungusGen = new WorldGenFungus(1,1);
	
	public static List<Integer[]> listShroomCaveChunk = new ArrayList<Integer[]>();
	public static List<Integer[]> listSpiderDenChunk = new ArrayList<Integer[]>();
	
	@SubscribeEvent
	public void OnDecorate(Decorate event)
    {
		if (event.type == Decorate.EventType.BIG_SHROOM || event.type == Decorate.EventType.SHROOM || event.type == Decorate.EventType.GRASS)
		for (Integer[] chunkP : listShroomCaveChunk)
		{
			if (event.chunkX == chunkP[0] && event.chunkZ == chunkP[1])
			{
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
    }
	
	@SubscribeEvent
    public void OnPostDecorate(DecorateBiomeEvent.Post event) {
		for (Integer[] chunkP : listShroomCaveChunk) {
			if (event.chunkX == chunkP[0] && event.chunkZ == chunkP[1]) {
				listShroomCaveChunk.remove(chunkP);
				return;
			}
		}
	}
	
	private final static Map<Block, Item> buckets = new HashMap<Block, Item>();
	static {
		buckets.put(EnumBlock.phos_water.block, EnumItem.phos_water_bucket.item);
		buckets.put(EnumBlock.poison_water.block, EnumItem.poison_water_bucket.item);
	}
	
	@SubscribeEvent	
	public void onBucketFill(FillBucketEvent event) {
		Block block = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);

		if (event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ) == 0) 
		{
			for (Block bukketBlock : buckets.keySet()) {
				if (block.equals(bukketBlock)) {
					event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
					ItemStack result = new ItemStack(buckets.get(bukketBlock));
					event.result = result;
					event.setResult(Result.ALLOW);
					return;
				}
			}
		}
	}
}
