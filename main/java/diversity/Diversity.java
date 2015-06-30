package diversity;

import java.util.logging.Logger;

import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import diversity.configurations.AConfigTool;
import diversity.configurations.ConfigVillager;
import diversity.proxy.ServerProxy;
import diversity.suppliers.EnumCave;
import diversity.suppliers.EnumVillageBasicPiece;
import diversity.suppliers.EnumBlock;
import diversity.suppliers.EnumEntity;
import diversity.suppliers.EnumFluid;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillagePiece;
import diversity.suppliers.EnumStructure;
import diversity.suppliers.EnumVillage;
import diversity.suppliers.EnumVillager;
import diversity.utils.Economy;
import diversity.utils.Economy.EPrice;
import diversity.utils.Economy.GPrice;

@Mod(modid = Diversity.MODID, name = Diversity.NAME, version = Diversity.VERSION)
public class Diversity
{
    public static final String MODID = "diversity";
    public static final String NAME = "Diversity";
    public static final String VERSION = "ALPHA 1.6_build";
    
    @Mod.Instance(Diversity.MODID)
    public static Diversity instance;
    
    public static Logger Divlogger;
    
    @SidedProxy(clientSide="diversity.proxy.ClientProxy", serverSide="diversity.proxy.ServerProxy")
    public static ServerProxy proxy;
         
    @EventHandler
    public void PreLoad(FMLPreInitializationEvent event)
    {
    	AConfigTool.values();
    	AConfigTool.loadAllConfig();
    	AConfigTool.saveAllConfig();
    	Divlogger = Logger.getLogger("Diversity");
    	
		EnumFluid.register();
		EnumBlock.register();
		EnumItem.register();
		
		BucketHandler.buckets.put(EnumBlock.phos_water.block, EnumItem.phos_water_bucket.item);
		BucketHandler.buckets.put(EnumBlock.poison_water.block, EnumItem.poison_water_bucket.item);
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.mushroom_stew),
				Item.getItemFromBlock(Blocks.red_mushroom),
				Item.getItemFromBlock(EnumBlock.blue_mushroom.block),
				Items.bowl);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.mushroom_stew),
				Item.getItemFromBlock(Blocks.brown_mushroom),
				Item.getItemFromBlock(EnumBlock.blue_mushroom.block),
				Items.bowl);
		
		EnumTribe.register();
		
    	GPrice.values();
    	EPrice.values();
    	Economy.savePrice();
    	Economy.loadPrice();
    	
		Diversity.proxy.registerRenderers();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		EnumVillage.values();
		EnumVillageBasicPiece.register();
		EnumVillagePiece.register();
			
		EnumStructure.values();
		EnumCave.values();
		EnumStructure.register();
		EnumCave.register();
				
    	MapGenStructureIO.registerStructure(MapGenVillageDiversity.Start.class, Diversity.MODID + ".Village"); 
    	MapGenStructureIO.registerStructure(MapGenStructureDiversity.Start.class, Diversity.MODID + ".Structure"); 
    	MapGenStructureIO.registerStructure(MapGenCaveDiversity.Start.class, Diversity.MODID + ".CaveStructure"); 

		EnumVillager.register();
		EnumEntity.register();

    	MinecraftForge.TERRAIN_GEN_BUS.register(new DiversityHandler());    	  	
    	MinecraftForge.EVENT_BUS.register(new DiversityHandler());    	  	
    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
    	EnumEntity.load();
    	
    	if (ConfigVillager.removeVanillaSpawnEgg.equals("true")) {
    		EntityList.entityEggs.remove(Integer.valueOf(120));
    	}
    }
    
    @EventHandler
    public void StartWorld(FMLServerAboutToStartEvent event)
    {
		EnumStructure.load();
		EnumCave.load();
    	EnumVillage.load();
    }
}
