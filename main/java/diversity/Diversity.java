package diversity;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
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
import diversity.configurations.ConfigEconomy;
import diversity.configurations.ConfigGlobal;
import diversity.configurations.ConfigEconomy.EnumObject;
import diversity.configurations.ConfigEconomy.EnumGroupObject;
import diversity.proxy.ServerProxy;
import diversity.suppliers.EnumCave;
import diversity.suppliers.EnumPotion;
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

@Mod(modid = Diversity.MODID, name = Diversity.NAME, version = Diversity.VERSION)
public class Diversity
{
    public static final String MODID = "diversity";
    public static final String NAME = "Diversity";
    public static final String VERSION = "ALPHA 1.6.1";
    
    @Mod.Instance(Diversity.MODID)
    public static Diversity instance;
    
    public static Logger Divlogger;
    
    @SidedProxy(clientSide="diversity.proxy.ClientProxy", serverSide="diversity.proxy.ServerProxy")
    public static ServerProxy proxy;
    
    @EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
    	Divlogger = Logger.getLogger("Diversity");
    	
    	EnumPotion.values();
    	
    	AConfigTool.values();
    	AConfigTool.loadAllConfig(false);
    	AConfigTool.saveAllConfig(false);
    	
		EnumFluid.register();
		EnumBlock.register();
		EnumItem.register();
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.mushroom_stew),
				Item.getItemFromBlock(Blocks.red_mushroom),
				Item.getItemFromBlock(EnumBlock.blue_mushroom.block),
				Items.bowl);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.mushroom_stew),
				Item.getItemFromBlock(Blocks.brown_mushroom),
				Item.getItemFromBlock(EnumBlock.blue_mushroom.block),
				Items.bowl);
		ItemStack boneStack = new ItemStack(Items.bone);
		GameRegistry.addRecipe(
			    new ItemStack(EnumBlock.bones.block,16),
			    "xxx",
			    "xyx",
			    "xxx",
			    'x', boneStack, 'y', new ItemStack(Items.skull));
		GameRegistry.addShapelessRecipe(
			    new ItemStack(EnumBlock.bones.block,4),
			    boneStack,boneStack,boneStack,
			    boneStack,boneStack,boneStack,
			    boneStack,boneStack,boneStack);
		GameRegistry.addRecipe(
			    new ItemStack(EnumItem.spider_gland_stick.item),
			    " y ",
			    " x ",
			    " x ",
			    'x', new ItemStack(Items.stick), 'y', new ItemStack(EnumItem.spider_gland.item));
		GameRegistry.addRecipe(
			    new ItemStack(EnumItem.spider_gland_stick.item),
			    "  z",
			    " y ",
			    "x  ",
			    'x', new ItemStack(Items.feather), 'y', new ItemStack(Items.stick), 'z', new ItemStack(EnumItem.spider_gland.item));
		
		EnumTribe.register();

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

		Diversity.proxy.registerHandler();
		
    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
    	EnumEntity.load();
    }
}
