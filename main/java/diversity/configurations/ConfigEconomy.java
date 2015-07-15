package diversity.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.DimensionManager;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import cpw.mods.fml.common.Loader;
import diversity.suppliers.EnumPotion;

public class ConfigEconomy
{
	private static final String configNameFile = "diversity-economy.cfg";
	private static final String configFile = Loader.instance().getConfigDir() + "/" + configNameFile;
	
	public static void saveConfig(boolean isWorld) {
		Properties properties = new Properties();
		for (Table.Cell<Item, Integer, IPrice> cell : IPrice.priceMap.cellSet()) {
			properties.setProperty(cell.getValue().name(), String.valueOf(cell.getValue().getPrice()));
		}
		
		try {
			File file;
			if (isWorld) {
				File folder = new File(DimensionManager.getCurrentSaveRootDirectory(), "config");
		    	folder.mkdir();
				file = new File(folder, configNameFile);
			} else {
				file = new File(configFile);
			}
			properties.store(new FileOutputStream(file), null);
		} catch (Exception e) {
		}
	}
	
	public static void loadConfig(boolean isWorld) {
		Properties properties = new Properties();
		try {
			FileInputStream inputStream;
			if (isWorld) {
				File folder = new File(DimensionManager.getCurrentSaveRootDirectory(), "config");
		    	folder.mkdir();
				inputStream = new FileInputStream(new File(folder, configNameFile));
			} else {
				inputStream = new FileInputStream(configFile);
			}
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			return;
		}
		
		for (Table.Cell<Item, Integer, IPrice> cell : IPrice.priceMap.cellSet()) {
			String price = properties.getProperty(cell.getValue().name());
			if (price != null && !price.isEmpty()) {
				cell.getValue().setPrice(Float.valueOf(price));
				properties.remove(cell.getValue().name());
			}
		}
	}
	
	public static float getPrice(Item item, int metaData)
	{
		return Float.valueOf(IPrice.priceMap.get(item, metaData).getPrice());
	}
	
	public static float getPrice(Item item)
	{
		return getPrice(item, 0);
	}
	
	public static Item getItem(IPrice itemPrice)
	{
		for (Table.Cell<Item, Integer, IPrice> cell : IPrice.priceMap.cellSet()) {
			if (cell.getValue().name().equals(itemPrice.name())) {
				return cell.getRowKey();
			}
		}
		return null;
	}
	
	public static int getMetadata(IPrice itemPrice)
	{
		for (Table.Cell<Item, Integer, IPrice> cell : IPrice.priceMap.cellSet()) {
			if (cell.getValue().name().equals(itemPrice.name())) {
				return cell.getColumnKey();
			}
		}
		return 0;
	}
	
	public interface IPrice
	{
		public static Table<Item, Integer, IPrice> priceMap = HashBasedTable.create();
		
		public abstract String name();
		
		public abstract float getPrice();
		
		public abstract void setPrice(float price);
	}
	
	public interface IItem {}
	
	public static enum GPrice implements IItem
	{
		wool			(Blocks.wool),
		dye				(Items.dye),
		potionitem		(Items.potionitem),
		echantmentbook 	(Items.enchanted_book)
		;
		
		Item item;
		
		private GPrice(Block block)
		{
			this(Item.getItemFromBlock(block));
		}
		
		private GPrice(Item item)
		{
			this.item = item;;
		}
		
		public IItem[] getIPrices()
		{
			Map<Integer, IPrice> map = IPrice.priceMap.row(item);
			return map.values().toArray(new IItem[] {});
		}
	}
	
	public enum EPrice implements IPrice, IItem
	{
		stone 					(Blocks.stone		, 	0, 	3),
		grass_block				(Blocks.grass		, 	0, 	3),
		dirt 					(Blocks.dirt		,	0,	1),
		coarse_dirt 			(Blocks.dirt		, 	1, 	4),
		potzol 					(Blocks.dirt		, 	2, 	4),
		cobblestone 			(Blocks.cobblestone	, 	0, 	1),
		oak_planks	 			(Blocks.planks		, 	0, 	1),
		spruce_planks 			(Blocks.planks		, 	1, 	1),
		birch_planks 			(Blocks.planks		, 	2, 	1),
		jungle_planks 			(Blocks.planks		, 	3, 	1),
		acacia_planks 			(Blocks.planks		, 	4, 	1),
		dark_oak_planks 		(Blocks.planks		, 	5, 	1),
		oak_sapling 			(Blocks.sapling		, 	0, 	3),
		spruce_sapling 			(Blocks.sapling		, 	1, 	3),
		birch_sapling 			(Blocks.sapling		, 	2, 	3),
		jungle_sapling 			(Blocks.sapling		, 	3, 	3),
		acacia_sapling 			(Blocks.sapling		, 	4, 	3),
		dark_oak_sapling 		(Blocks.sapling		, 	5, 	3),
		sand					(Blocks.sand		, 	0, 	1),
		red_sand				(Blocks.sand		, 	1, 	3),
		gravel					(Blocks.gravel		, 	0, 	3),
		oak_wood				(Blocks.log			, 	0, 	2),
		spruce_wood				(Blocks.log			, 	1, 	2),
		birch_wood				(Blocks.log			, 	2, 	2),
		jungle_wood				(Blocks.log			, 	3, 	2),
		oak_leaves				(Blocks.leaves		, 	0, 	3),
		spruce_leaves			(Blocks.leaves		, 	1, 	3),
		birch_leaves			(Blocks.leaves		, 	2, 	3),
		jungle_leaves			(Blocks.leaves		, 	3, 	3),
		sponge					(Blocks.sponge		, 	0,	60),
		glass					(Blocks.glass		, 	0,	3),
		lapis_lazuli_block		(Blocks.lapis_block	, 	0, 	24),
		dispenser				(Blocks.dispenser	, 	0, 	12),
		sandstone				(Blocks.sandstone	, 	0, 	4),
		chiseled_sandstone		(Blocks.sandstone	, 	1, 	30),
		smooth_sandstone		(Blocks.sandstone	, 	2, 	4),
		note_block				(Blocks.noteblock	, 	0, 	8),
		powered_rail			(Blocks.golden_rail	, 	0, 	50),
		detector_rail			(Blocks.detector_rail, 	0, 	28),
		sticky_piston			(Blocks.sticky_piston, 	0, 	14),
		cobweb					(Blocks.web			, 	0, 	20),
		dead_shrub				(Blocks.tallgrass	, 	0, 	4),
		grass					(Blocks.tallgrass	, 	1, 	4),
		fern					(Blocks.tallgrass	, 	2, 	4),
		piston					(Blocks.piston		, 	0, 	12),
		white_wool				(Blocks.wool		, 	0, 	6),
		orange_wool				(Blocks.wool		, 	1,	8),
		magenta_wool			(Blocks.wool		, 	2, 	8),
		light_blue_wool			(Blocks.wool		, 	3, 	8),
		yellow_wool				(Blocks.wool		, 	4,	8),
		lime_wool				(Blocks.wool		, 	5,	8),
		pink_wool				(Blocks.wool		, 	6, 	8),
		gray_wool				(Blocks.wool		, 	7, 	7),
		light_gray_wool			(Blocks.wool		, 	8, 	7),
		cyan_wool				(Blocks.wool		, 	9, 	8),
		purple_wool				(Blocks.wool		, 	10, 8),
		blue_wool				(Blocks.wool		, 	11, 8),
		brown_wool				(Blocks.wool		, 	12, 8),
		green_wool				(Blocks.wool		, 	13, 8),
		red_wool				(Blocks.wool		, 	14, 8),
		black_wool				(Blocks.wool		, 	15, 7),
		dandelion				(Blocks.yellow_flower, 	0, 	4),
		poppy					(Blocks.red_flower	, 	0, 	4),
		blue_orchid				(Blocks.red_flower	, 	1, 	4),
		allium					(Blocks.red_flower	, 	2, 	4),
		azure_bluet				(Blocks.red_flower	, 	3, 	4),
		red_tulip				(Blocks.red_flower	, 	4, 	4),
		orange_tulip			(Blocks.red_flower	,	5, 	4),
		white_tulip				(Blocks.red_flower	, 	6, 	4),
		pink_tulip				(Blocks.red_flower	, 	7, 	4),
		oxeye_daisy				(Blocks.red_flower	, 	8, 	4),
		brown_mushroom			(Blocks.brown_mushroom, 0, 	4),
		red_mushroom			(Blocks.red_mushroom, 	0, 	4),
		bricks					(Blocks.brick_block	, 	0, 	18),
		tnt						(Blocks.tnt			, 	0, 	24),
		bookshelf				(Blocks.bookshelf	, 	0, 	38),
		mossy_cobblestone		(Blocks.mossy_cobblestone, 0, 12),
		obsidian				(Blocks.obsidian	, 	0, 	20),
		torch					(Blocks.torch		, 	0 , 1),
		chest					(Blocks.chest		, 	0, 	8),
		ladder					(Blocks.ladder		, 	0, 	1),
		rail					(Blocks.rail		, 	0, 	2),
		lever					(Blocks.lever		, 	0, 	2),
		snow					(Blocks.snow		,	0,	4),
		ice						(Blocks.ice			,	0,	8),
		cactus					(Blocks.cactus		,	0,	6),
		jukebox					(Blocks.jukebox		,	0,	60),
		pumpkin					(Blocks.pumpkin		, 	0, 	10),
		netherrack				(Blocks.netherrack	, 	0, 	3),
		soul_sand				(Blocks.soul_sand 	,	0,	10),
		glowstone				(Blocks.glowstone	,	0,	30),
		jack_o_lantern			(Blocks.lit_pumpkin	,	0,	12),
		white_glass				(Blocks.stained_glass,  0,  5),
		orange_glass			(Blocks.stained_glass,  1,  7),
		magenta_glass			(Blocks.stained_glass,  2,  7),
		light_blue_glass		(Blocks.stained_glass,  3,  7),
		yellow_glass			(Blocks.stained_glass,  4,  6),
		lime_glass				(Blocks.stained_glass,  5,  7),
		pink_glass				(Blocks.stained_glass,  6,  7),
		gray_glass				(Blocks.stained_glass,  7,  7),
		light_gray_glass		(Blocks.stained_glass,	8,	7),
		cyan_glass				(Blocks.stained_glass,	9,	7),
		purple_glass			(Blocks.stained_glass,	10,	7),
		blue_glass				(Blocks.stained_glass,	11,	7),
		brown_glass				(Blocks.stained_glass,	12,	7),
		green_glass				(Blocks.stained_glass,	13,	7),
		red_glass				(Blocks.stained_glass,	14,	6),
		black_glass				(Blocks.stained_glass,	15,	5),
		stonebrick				(Blocks.stonebrick	,	0,	3),
		mossy_stonebrick		(Blocks.stonebrick	,	1,	8),
		cracked_stonebrick		(Blocks.stonebrick	,	2,	8),
		chiseled_stonebrick		(Blocks.stonebrick	,	3,	8),
		melon_block				(Blocks.melon_block	,	0,	10),
		vines					(Blocks.vine		,	0,	8),
		mycelium				(Blocks.mycelium	,	0,	24),
		lily_pad				(Blocks.waterlily	,	0,	10),
		nether_brick_block		(Blocks.nether_brick,	0,	20),
		cocoa					(Blocks.cocoa		,	0,	14),
		quartz_block			(Blocks.quartz_block,	0,	32),
		white_clay				(Blocks.stained_hardened_clay, 0, 6),
		orange_clay				(Blocks.stained_hardened_clay, 1, 6),
		magenta_clay			(Blocks.stained_hardened_clay, 2, 6),
		light_blue_clay			(Blocks.stained_hardened_clay, 3, 6),
		yellow_clay				(Blocks.stained_hardened_clay, 4, 6),
		lime_clay				(Blocks.stained_hardened_clay, 5, 6),
		pink_clay				(Blocks.stained_hardened_clay, 6, 6),
		gray_clau				(Blocks.stained_hardened_clay, 7, 6),
		light_gray_clay			(Blocks.stained_hardened_clay, 8, 6),
		cyan_clay				(Blocks.stained_hardened_clay, 9, 6),
		purple_clay				(Blocks.stained_hardened_clay, 10, 6),
		blue_clay				(Blocks.stained_hardened_clay, 11, 6),
		brown_clay				(Blocks.stained_hardened_clay, 12, 6),
		green_clay				(Blocks.stained_hardened_clay, 13, 6),
		red_clay				(Blocks.stained_hardened_clay, 14, 6),
		black_clay				(Blocks.stained_hardened_clay, 15, 6),
		accacia_leaves			(Blocks.leaves2		,	0,	3),
		dark_oak_leaves			(Blocks.leaves2		,	1,	3),
		accacia_wood			(Blocks.log2		,	0,	2),
		dark_oak_wood			(Blocks.log2		,	0,	2),
		hay_bale				(Blocks.hay_block	,	0,	16),
		hardened_clay			(Blocks.hardened_clay,	0,	5),
		packed_ice				(Blocks.packed_ice	,	0,	24),
		sunflower				(Blocks.double_plant,	0,	6),
		lilac					(Blocks.double_plant,	1,	6),
		double_tallgrass		(Blocks.double_plant,	2,	6),
		large_fern				(Blocks.double_plant,	3,	6),
		rose_bush				(Blocks.double_plant,	4,	6),
		peony					(Blocks.double_plant,	5,	6),
		iron_shovel				(Items.iron_shovel	,	0,	5),
		iron_pickaxe			(Items.iron_pickaxe	,	0,	10),
		iron_axe				(Items.iron_axe		,	0,	10),
		flint_and_steel			(Items.flint_and_steel,	0,	8),
		apple					(Items.apple		,	0,	6),
		bow						(Items.bow			,	0,	6),
		arrow					(Items.arrow		,	0,	1),
		coal					(Items.coal			,	0,	3),
		charcoal				(Items.coal			,	1,	3),
		diamond					(Items.diamond		,	0,	60),
		iron_ingot				(Items.iron_ingot	,	0,	4),
		gold_ingot				(Items.gold_ingot	,	0,	8),
		iron_sword				(Items.iron_sword	,	0,	9),
		wooden_sword			(Items.wooden_sword	,	0,	1),
		wooden_shovel			(Items.wooden_shovel,	0,	1),
		wooden_pickaxe			(Items.wooden_pickaxe,	0,	2),
		wooden_axe				(Items.wooden_axe	,	0,	2),
		stone_sword				(Items.stone_sword	,	0,	3),
		stone_shovel			(Items.stone_shovel	,	0,	2),
		stone_pickaxe			(Items.stone_pickaxe,	0,	4),
		stone_axe				(Items.stone_axe	,	0,	4),
		diamond_sword			(Items.diamond_sword,	0,	120),
		diamond_shovel			(Items.diamond_shovel,	0,	60),
		diamond_pickaxe			(Items.diamond_pickaxe,	0,	180),
		diamond_axe				(Items.diamond_axe	,	0,	180),
		stick					(Items.stick		,	0,	0.1f),
		bowl					(Items.bowl			,	0,	4),
		mushroom_stew			(Items.mushroom_stew,	0,	12),
		golden_sword			(Items.golden_sword	,	0,	16),
		golden_shovel			(Items.golden_shovel,	0,	8),
		golden_pickaxe			(Items.golden_pickaxe,	0,	24),
		golden_axe				(Items.golden_axe	,	0,	24),
		string					(Items.string		,	0,	0.9f),
		feather					(Items.feather		,	0,	1),
		gunpowder				(Items.gunpowder	,	0,	4),
		wooden_hoe				(Items.wooden_hoe	,	0,	1),
		stone_hoe				(Items.stone_hoe	,	0,	3),
		iron_hoe				(Items.iron_hoe		,	0,	9),
		diamond_hoe				(Items.diamond_hoe	,	0,	120),
		golden_hoe				(Items.golden_hoe	,	0,	16),
		wheat_seeds				(Items.wheat_seeds	,	0,	0.5f),
		wheat					(Items.wheat		,	0,	2),
		bread					(Items.bread		,	0,	6),
		leather_helmet			(Items.leather_helmet,	0,	15),
		leather_tunic			(Items.leather_chestplate, 0, 24),
		leather_pants			(Items.leather_leggings, 0, 21),
		leather_boots			(Items.leather_boots,	0,	12),
		chainmail_helmet		(Items.chainmail_helmet, 0, 30),
		chainmail_chestplate	(Items.chainmail_chestplate, 0, 48),
		chainmail_leggings		(Items.chainmail_leggings, 0, 42),
		chainmail_boots			(Items.chainmail_boots,	0,	24),
		iron_helmet				(Items.iron_helmet	,	0,	20),
		iron_chestplate			(Items.iron_chestplate,	0,	32),
		iron_leggings			(Items.iron_leggings,	0,	28),
		iron_boots				(Items.iron_boots	,	0,	16),
		diamond_helmet			(Items.diamond_helmet,	0,	300),
		diamond_chestplate		(Items.diamond_chestplate, 0, 480),
		diamond_leggings		(Items.diamond_leggings, 0,	420),
		diamond_boots			(Items.diamond_boots,	0,	240),
		golden_helmet			(Items.golden_helmet,	0,	40),
		golden_chesplate		(Items.golden_chestplate, 0, 64),
		golden_leggings			(Items.golden_leggings,	0,	56),
		golden_boots			(Items.golden_boots	,	0,	32),
		flint					(Items.flint		,	0,	4),
		raw_porkchop			(Items.porkchop		,	0,	3),
		cooked_porkchop			(Items.cooked_porkchop,	0,	5),
		painting				(Items.painting		,	0,	8),
		golden_apple			(Items.golden_apple	,	0,	70),
		enchanted_golden_apple	(Items.golden_apple	,	1,	582),
		sign					(Items.sign			,	0,	2),
		bucket					(Items.bucket		,	0,	12),
		minecart				(Items.minecart		,	0,	20),
		saddle					(Items.saddle		,	0,	32),
		redstone				(Items.redstone		,	0,	4),
		snowball				(Items.snowball		,	0,	0.1f),
		boat					(Items.boat			,	0,	10),
		leather					(Items.leather		,	0,	3),
		brick					(Items.brick		,	0,	4.5f),
		clay					(Items.clay_ball	,	0,	3),
		sugar_canes				(Items.reeds		,	0,	3),
		paper					(Items.paper		,	0,	3),
		book					(Items.book			,	0,	12),
		slimeball				(Items.slime_ball	,	0,	5),
		egg						(Items.egg			,	0,	3),
		compass					(Items.compass		,	0,	20),
		fishing_rod				(Items.fishing_rod	,	0,	1),
		clock					(Items.clock		,	0,	36),
		glowstone_dust			(Items.glowstone_dust,	0,	7.5f),
		raw_fish				(Items.fish			,	0,	5),
		raw_salmon				(Items.fish			,	1,	5),
		clownfish				(Items.fish			,	2,	5),
		pufferfish				(Items.fish			,	3,	5),
		cooked_fish				(Items.cooked_fished,	0,	7),
		cooked_salmon			(Items.cooked_fished,	1,	7),
		ink						(Items.dye			,	0,	3),
		rose_red				(Items.dye			,	1,	4),
		cactus_green			(Items.dye			,	2,	4),
		coco_beans				(Items.dye			,	3,	4),
		lapis_lazuli			(Items.dye			,	4,	4),
		purple_dye				(Items.dye			,	5,	4),
		cyan_dye				(Items.dye			,	6,	4),
		light_gray_dye			(Items.dye			,	7,	4),
		gray_dye				(Items.dye			,	8,	4),
		pink_dye				(Items.dye			,	9,	4),
		lime_dye				(Items.dye			,	10,	4),
		dandelion_yellow		(Items.dye			,	11,	4),
		light_blue_dye			(Items.dye			,	12,	4),
		magenta_dye				(Items.dye			,	13,	4),
		orange_dye				(Items.dye			,	14,	4),
		bone_meal				(Items.dye			,	15,	1),
		bone					(Items.bone			,	0,	3),
		sugar					(Items.sugar		,	0,	3),
		cake					(Items.cake			,	0,	15),
		cookie					(Items.cookie		,	0,	1),
		map						(Items.filled_map	,	0,	60),
		shears					(Items.shears		,	0,	8),
		melon					(Items.melon		,	0,	1.5f),
		pumpkin_seeds			(Items.pumpkin_seeds,	0,	2),
		melon_seeds				(Items.melon_seeds	,	0,	2),
		raw_beef				(Items.beef			,	0,	4),
		steak					(Items.cooked_beef	,	0,	6),
		raw_chicken				(Items.chicken		,	0,	4),
		cooked_chicken			(Items.cooked_chicken,	0,	6),
		rotten_flesh			(Items.rotten_flesh	,	0,	0.5f),
		ender_pearl				(Items.ender_pearl	,	0,	80),
		blaze_rod				(Items.blaze_rod	,	0,	30),
		ghast_tear				(Items.ghast_tear	,	0,	80),
		nether_wart				(Items.nether_wart	,	0,	32),
		potion_renegeration_I	(Items.potionitem	,	EnumPotion.regeneration.id_potion_I,	30),
		potion_renegeration_II	(Items.potionitem	,	EnumPotion.regeneration.id_potion_II,	45),
		potion_renegeration_I_EXT(Items.potionitem	,	EnumPotion.regeneration.id_potion_I_EXT,	80),
		potion_renegeration_II_EXT(Items.potionitem	,	EnumPotion.regeneration.id_potion_II_EXT,	120),
		potion_switfness_I		(Items.potionitem	,	EnumPotion.swiftness.id_potion_I,	30),
		potion_switfness_II		(Items.potionitem	,	EnumPotion.swiftness.id_potion_II,	45),
		potion_switfness_I_EXT	(Items.potionitem	,	EnumPotion.swiftness.id_potion_I_EXT,	80),
		potion_switfness_II_EXT	(Items.potionitem	,	EnumPotion.swiftness.id_potion_II_EXT,	120),
		potion_slowness			(Items.potionitem	,	2,	30),
		potion_haste			(Items.potionitem	,	3,	30),
		potion_mining_fatigue	(Items.potionitem	,	4,	30),
		potion_strength			(Items.potionitem	,	5,	30),
		potion_instant_health	(Items.potionitem	,	6,	30),
		potion_instant_damage	(Items.potionitem	,	7,	30),
		potion_jump_boost		(Items.potionitem	,	8,	30),
		potion_nausea			(Items.potionitem	,	9,	30),
		potion_resistance		(Items.potionitem	,	11,	30),
		potion_fire_resistance_I(Items.potionitem	,	EnumPotion.fireResistance.id_potion_I,	30),
		potion_water_breathing	(Items.potionitem	,	13,	30),
		potion_invisibility		(Items.potionitem	,	14,	30),
		potion_blindness		(Items.potionitem	,	15,	30),
		potion_night_vision		(Items.potionitem	,	16,	30),
		potion_hunger			(Items.potionitem	,	17,	30),
		potion_weakness			(Items.potionitem	,	18,	30),
		potion_poison			(Items.potionitem	,	19,	30),
		potion_wither			(Items.potionitem	,	20,	30),
		potion_health_boost		(Items.potionitem	,	21,	30),
		potion_absorption		(Items.potionitem	,	22,	30),
		potion_saturation		(Items.potionitem	,	23,	30),
		glass_bottle			(Items.glass_bottle	,	0,	9),
		spider_eye				(Items.spider_eye	,	0,	3),
		fermented_spider_eye	(Items.fermented_spider_eye, 0, 11),
		blaze_powder			(Items.blaze_powder	,	0,	30),
		magma_cream				(Items.magma_cream	,	0,	20),
		glistering_melon		(Items.speckled_melon,	0,	11),
		bottle_o_enchanting		(Items.experience_bottle, 0, 20),
		fire_charge				(Items.fire_charge	,	0,	13),
		book_and_quill			(Items.writable_book,	0,	15),
		written_book			(Items.written_book	,	0,	25),
		emerald					(Items.emerald		,	0,	72),
		item_frame				(Items.item_frame	,	0,	5),
		flower_pot				(Items.flower_pot	,	0,	13),
		carrot					(Items.carrot		,	0,	3),
		potato					(Items.potato		,	0,	3),
		baked_potato			(Items.baked_potato	,	0,	5),
		poisonous_potato		(Items.poisonous_potato, 0, 3),
		empty_map				(Items.map			,	0,	44),
		golden_carrot			(Items.golden_carrot,	0,	11),
		carrot_on_a_stick		(Items.carrot_on_a_stick, 0, 4),
		pumpkin_pie				(Items.pumpkin_pie	,	0,	8),
		enchanted_book_protection (Items.enchanted_book, 0,	80),
		enchanted_book_fire_protection (Items.enchanted_book, 1, 80),
		enchanted_book_feather_falling (Items.enchanted_book, 2, 80),
		enchanted_book_blast_protection (Items.enchanted_book, 3, 80),
		enchanted_book_projectile_protection (Items.enchanted_book, 4, 80),
		enchanted_book_respiration (Items.enchanted_book, 5, 80),
		enchanted_book_aqua_affinity (Items.enchanted_book, 6, 80),
		enchanted_book_thorns	(Items.enchanted_book, 	7, 	80),
		enchanted_book_depth_strider (Items.enchanted_book, 8, 80),
		enchanted_book_sharpness (Items.enchanted_book, 16,	80),
		enchanted_book_smite	(Items.enchanted_book, 	17,	80),
		enchanted_book_bane_of_arthropods (Items.enchanted_book, 18, 80),
		enchanted_book_knockback (Items.enchanted_book, 19, 80),
		enchanted_book_fire_aspect (Items.enchanted_book, 20, 80),
		enchanted_book_looting 	(Items.enchanted_book,	21,	80),
		enchanted_book_efficiency (Items.enchanted_book, 32, 80),
		enchanted_book_silk_touch (Items.enchanted_book, 33, 80),
		enchanted_book_unbreaking (Items.enchanted_book, 34, 80),
		enchanted_book_fortune 	(Items.enchanted_book,	35,	80),
		enchanted_book_power 	(Items.enchanted_book,	48, 80),
		enchanted_book_punch 	(Items.enchanted_book,	49,	80),
		enchanted_book_flame 	(Items.enchanted_book,	50,	80),
		enchanted_book_infinity	(Items.enchanted_book,	51,	80),
		enchanted_book_luck_of_the_sea (Items.enchanted_book, 61, 80),
		enchanted_book_lure 	(Items.enchanted_book,	62,	80),
		nether_brick			(Items.netherbrick	,	0,	5),
		quartz					(Items.quartz		,	0,	8),
		iron_horse_armor		(Items.iron_horse_armor, 0, 80),
		golden_horse_armor		(Items.golden_horse_armor, 0, 160),
		diamond_horse_armor		(Items.diamond_horse_armor, 0, 1200),
		lead					(Items.lead			,	0,	6),
		
		;
		
		private float price;
		
		private EPrice(Block block, int metadataId, float price)
		{
			this(Item.getItemFromBlock(block), metadataId, price);
		}
		
		private EPrice(Item item, int metadataId, float price)
		{
			this.price = price;
			priceMap.put(item, metadataId, this);
		}

		@Override
		public float getPrice() {
			return price;
		}

		@Override
		public void setPrice(float price) {
			this.price = price;
		}
	}
}
