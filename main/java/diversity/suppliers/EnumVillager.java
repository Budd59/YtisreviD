package diversity.suppliers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.village.MerchantRecipeList;

import org.apache.commons.lang3.ArrayUtils;

import diversity.Diversity;
import diversity.configurations.ConfigEconomy.EPrice;
import diversity.configurations.ConfigEconomy.GPrice;
import diversity.configurations.ConfigEconomy.IItem;
import diversity.configurations.ConfigEconomy.IPrice;
import diversity.entity.AGlobalEntityVillager;
import diversity.utils.PathTool;
import diversity.utils.TradeTools;
import diversity.utils.VillagerRegistry;
import diversity.utils.VillagerRegistry.IVillageTradeHandler;

public enum EnumVillager implements IVillageTradeHandler
{
	APACHE_BREEDER (EnumTribe.APACHE, "breeder",
			new IItem[]{EPrice.wheat, EPrice.wheat_seeds, EPrice.leather},
			new IItem[]{EPrice.iron_horse_armor, EPrice.saddle, EPrice.lead}
	),
	APACHE_HUNTER (EnumTribe.APACHE, "hunter",
			new IItem[]{EPrice.string, EPrice.stick, EPrice.flint, EPrice.feather},
			new IItem[]{EPrice.bow, EPrice.arrow, EPrice.lead}
	),
	APACHE_SHAMAN (EnumTribe.APACHE, "shaman",
			new IItem[]{EPrice.bone, EPrice.gray_dye, EPrice.orange_dye, EPrice.light_blue_dye, EPrice.bowl},
			new IItem[]{EPrice.bottle_o_enchanting, EPrice.potion_nausea, EPrice.potion_speed, EPrice.potion_fire_resistance}
	),
	APACHE_CHIEF (EnumTribe.APACHE, "chief",
			new IItem[]{EPrice.feather},
			new IItem[]{EPrice.stone_axe, EPrice.golden_horse_armor, EPrice.emerald},
	true),
	APACHE_WARRIOR (EnumTribe.APACHE, "warrior",
			new IItem[]{EPrice.iron_axe, EPrice.iron_sword, EPrice.leather},
			new IItem[]{EPrice.stone_axe, EPrice.stone_sword, EPrice.lead, EPrice.leather_helmet, EPrice.leather_tunic, EPrice.leather_pants, EPrice.leather_boots}
	),
	
	AZTEC_HUNTER (EnumTribe.AZTEC, "hunter",
			new IItem[]{},
			new IItem[]{}
			//new IItem[]{EPrice.string, EPrice.stick, EPrice.flint, EPrice.feather},
			//new IItem[]{EPrice.arrow, EPrice.bow}
	),
	AZTEC_DYER (EnumTribe.AZTEC, "dyer",
			new IItem[]{EPrice.red_wool, EPrice.dandelion_yellow, EPrice.cactus_green},
			new IItem[]{EPrice.yellow_wool, EPrice.lime_wool}
	),
	AZTEC_CHIEF (EnumTribe.AZTEC, "chief",
			new IItem[]{EPrice.diamond},
			new IItem[]{EPrice.emerald},
	true),
	AZTEC_HIGHPRIEST (EnumTribe.AZTEC, "highpriest",
			new IItem[]{EPrice.ender_pearl, EPrice.rose_red},
			new IItem[]{EPrice.emerald, EPrice.potion_poison, EPrice.potion_night_vision, EPrice.potion_weakness, EPrice.potion_slowness}
	),
	AZTEC_BREEDER (EnumTribe.AZTEC, "breeder",
			new IItem[]{EPrice.wheat_seeds, EPrice.pumpkin_seeds, EPrice.melon_seeds},
			new IItem[]{EPrice.raw_chicken, EPrice.cooked_chicken, EPrice.egg}
	),
	AZTEC_FARMER (EnumTribe.AZTEC, "farmer",
			new IItem[]{EPrice.wheat_seeds, EPrice.pumpkin_seeds, EPrice.melon_seeds},
			new IItem[]{EPrice.raw_chicken, EPrice.cooked_chicken, EPrice.egg}
	),

	INUIT_FISHERMAN (EnumTribe.INUIT, "fisherman",
			new IItem[]{EPrice.stick, EPrice.string, EPrice.leather, EPrice.white_wool},
			new IItem[]{EPrice.raw_fish, EPrice.cooked_fish, EPrice.raw_salmon, EPrice.cooked_salmon, EPrice.fishing_rod}
	),
	INUIT_HUNTER (EnumTribe.INUIT, "hunter",
			new IItem[]{EPrice.string, EPrice.stick, EPrice.flint, EPrice.feather, EPrice.leather},
			new IItem[]{EPrice.bow, EPrice.arrow, EPrice.leather_helmet, EPrice.leather_tunic, EPrice.leather_pants, EPrice.leather_boots}
	),
	INUIT_KENNELMASTER (EnumTribe.INUIT, "kennelmaster",
			new IItem[]{EPrice.bone, EPrice.raw_porkchop, EPrice.raw_beef, EPrice.raw_fish, EPrice.raw_salmon},
			new IItem[]{EPrice.lead}
	),
	INUIT_CHIEF (EnumTribe.INUIT, "chief",
			new IItem[]{EPrice.diamond, EPrice.diamond_sword},
			new IItem[]{EPrice.emerald, EPrice.enchanted_book_fortune, EPrice.enchanted_book_silk_touch},
	true),
	
	ZULU_FARMER (EnumTribe.ZULU, "farmer",
			new IItem[]{EPrice.wheat, EPrice.wheat_seeds},
			new IItem[]{EPrice.wheat, EPrice.bread, EPrice.stone_hoe}
	),
	ZULU_WARRIOR (EnumTribe.ZULU, "warrior",
			new IItem[]{EPrice.iron_axe, EPrice.iron_sword, EPrice.leather},
			new IItem[]{EPrice.stone_axe, EPrice.stone_sword, EPrice.iron_axe, EPrice.leather_helmet, EPrice.leather_tunic, EPrice.leather_pants, EPrice.leather_boots}
	),
	ZULU_BREEDER (EnumTribe.ZULU, "breeder",
			new IItem[]{EPrice.wheat, EPrice.wheat_seeds},
			new IItem[]{EPrice.raw_beef, EPrice.steak, EPrice.leather}
	),
	ZULU_CHIEF (EnumTribe.ZULU, "chief",
			new IItem[]{EPrice.golden_sword, EPrice.golden_helmet, EPrice.golden_chesplate, EPrice.golden_leggings, EPrice.golden_boots},
			new IItem[]{EPrice.emerald, EPrice.diamond},
	true),
	ZULU_GURU (EnumTribe.ZULU, "guru",
			new IItem[]{EPrice.bone, EPrice.ink, EPrice.light_gray_dye, EPrice.gray_dye, EPrice.bone_meal},
			new IItem[]{EPrice.bowl, EPrice.bottle_o_enchanting, EPrice.potion_nausea, EPrice.potion_jump_boost, EPrice.potion_saturation, EPrice.potion_instant_damage}
	),
	
	TIBETAN_MONK (EnumTribe.TIBETAN, "monk",
			new IItem[]{EPrice.pumpkin, EPrice.pumpkin_seeds, EPrice.wheat_seeds},
			new IItem[]{EPrice.wheat, EPrice.bread, EPrice.pumpkin_pie}
	),
	TIBETAN_MASTER (EnumTribe.TIBETAN, "master",
			new IItem[]{EPrice.rose_red, EPrice.dandelion_yellow, EPrice.orange_dye, EPrice.bone_meal},
			new IItem[]{EPrice.bottle_o_enchanting, EPrice.potion_invisibility, EPrice.enchanted_book_infinity}
	),
	TIBETAN_GREATWISE (EnumTribe.TIBETAN, "greatwise",
			new IItem[]{EPrice.clock, EPrice.enchanted_book_protection},
			new IItem[]{EPrice.emerald, EPrice.compass, EPrice.diamond, EPrice.ender_pearl},
	true),
	
	EGYPTIAN_FARMER (EnumTribe.EGYPTIAN, "farmer",
			new IItem[]{EPrice.bone_meal, EPrice.wheat_seeds, EPrice.sugar_canes},
			new IItem[]{EPrice.wheat, EPrice.sugar}
	),
	EGYPTIAN_SCULPTOR (EnumTribe.EGYPTIAN, "sculptor",
			new IItem[]{EPrice.sandstone},
			new IItem[]{EPrice.chiseled_sandstone, EPrice.smooth_sandstone}
	),
	EGYPTIAN_SCRIBE (EnumTribe.EGYPTIAN, "scribe",
			new IItem[]{EPrice.ink, EPrice.lapis_lazuli, EPrice.purple_dye, EPrice.paper, EPrice.sugar},
			new IItem[]{EPrice.map, EPrice.empty_map}
	),
	EGYPTIAN_PRIEST (EnumTribe.EGYPTIAN, "priest",
			new IItem[]{EPrice.ink, EPrice.lapis_lazuli, EPrice.purple_dye, EPrice.bone_meal},
			new IItem[]{EPrice.bottle_o_enchanting, EPrice.bowl, EPrice.enchanted_book_protection, EPrice.enchanted_book_blast_protection, EPrice.enchanted_book_flame}
	),
	EGYPTIAN_PAINTER (EnumTribe.EGYPTIAN, "painter",
			ArrayUtils.addAll(new IItem[]{EPrice.paper, EPrice.white_wool, EPrice.stick}, GPrice.dye.getIPrices()),
			new IItem[]{EPrice.painting}
	),
	EGYPTIAN_GUARD (EnumTribe.EGYPTIAN, "guard",
			new IItem[]{EPrice.iron_axe, EPrice.iron_sword, EPrice.leather},
			new IItem[]{EPrice.stone_axe, EPrice.stone_sword, EPrice.lead, EPrice.iron_helmet, EPrice.iron_chestplate, EPrice.iron_leggings, EPrice.iron_boots}
	),
	EGYPTIAN_PHARAOH (EnumTribe.EGYPTIAN, "pharaoh",
			new IItem[]{EPrice.diamond, EPrice.compass},
			new IItem[]{EPrice.emerald, EPrice.clock},
	true),
	
	LAKESIDE_FISHERMAN (EnumTribe.LAKESIDE, "fisherman",
			new IItem[]{EPrice.stick, EPrice.string},
			new IItem[]{EPrice.raw_fish, EPrice.cooked_fish, EPrice.fishing_rod}
	),
	LAKESIDE_FARMER (EnumTribe.LAKESIDE, "farmer",
			new IItem[]{EPrice.iron_hoe},
			new IItem[]{EPrice.carrot, EPrice.potato, EPrice.baked_potato, EPrice.lily_pad, EPrice.vines}
	),	
	LAKESIDE_BREEDER (EnumTribe.LAKESIDE, "breeder",
			new IItem[]{EPrice.potato, EPrice.poisonous_potato, EPrice.carrot},
			new IItem[]{EPrice.raw_porkchop, EPrice.cooked_porkchop, EPrice.bone}
	),
	LAKESIDE_GUARD (EnumTribe.LAKESIDE, "guard",
			new IItem[]{EPrice.iron_axe, EPrice.iron_sword, EPrice.leather},
			new IItem[]{EPrice.stone_axe, EPrice.stone_sword, EPrice.lead, EPrice.iron_helmet, EPrice.iron_chestplate, EPrice.iron_leggings, EPrice.iron_boots}
	),
	LAKESIDE_CHIEF (EnumTribe.LAKESIDE, "chief",
			new IItem[]{EPrice.compass},
			new IItem[]{EPrice.emerald, EPrice.enchanted_book_punch, EPrice.enchanted_book_aqua_affinity},
	true),
	
	SETTLED_BUTCHER (EnumTribe.SETTLED, "butcher",
			new IItem[]{EPrice.coal, EPrice.raw_porkchop},
			new IItem[]{EPrice.leather_boots, EPrice.leather_tunic, EPrice.leather_pants, EPrice.leather_boots, EPrice.saddle, EPrice.cooked_porkchop, EPrice.steak, EPrice.carrot_on_a_stick}
	),		
	SETTLED_FARMER (EnumTribe.SETTLED, "farmer",
			new IItem[]{EPrice.wheat_seeds, EPrice.carrot, EPrice.potato},
			new IItem[]{EPrice.wheat, EPrice.carrot, EPrice.baked_potato}
	),
	SETTLED_LIBRARIAN (EnumTribe.SETTLED, "librarian",
			new IItem[]{EPrice.paper, EPrice.book},
			new IItem[]{EPrice.compass, EPrice.clock, EPrice.bookshelf, EPrice.enchanted_book_looting, EPrice.enchanted_book_projectile_protection, EPrice.enchanted_book_sharpness}
	),
	SETTLED_PRIEST (EnumTribe.SETTLED, "priest",
			new IItem[]{EPrice.redstone, EPrice.glowstone_dust},
			new IItem[]{EPrice.ender_pearl, EPrice.bottle_o_enchanting, EPrice.glowstone, EPrice.potion_renegeration, EPrice.potion_health_boost}
	),
	SETTLED_SMITH (EnumTribe.SETTLED, "smith",
			new IItem[]{EPrice.iron_ingot},
			new IItem[]{EPrice.chainmail_helmet, EPrice.chainmail_chestplate, EPrice.chainmail_leggings, EPrice.chainmail_boots, EPrice.iron_helmet, EPrice.iron_chestplate, EPrice.iron_leggings, EPrice.iron_boots}
	),
	SETTLED_VILLAGER (EnumTribe.SETTLED, "villager",
			new IItem[]{EPrice.wheat, EPrice.white_wool},
			new IItem[]{EPrice.apple, EPrice.cookie, EPrice.shears, EPrice.bread}
	),
	SETTLED_GUARD (EnumTribe.SETTLED, "guard"),
	SETTLED_INNKEEPER (EnumTribe.SETTLED, "innkeeper",
			new IItem[]{},
			new IItem[]{EPrice.apple, EPrice.cooked_porkchop, EPrice.cooked_chicken, EPrice.bread, EPrice.mushroom_stew}
	),
	DWARF_SMITH (EnumTribe.DWARF, "smith",
			new IItem[]{EPrice.coal, EPrice.cobblestone, EPrice.oak_planks, EPrice.iron_ingot},
			new IItem[]{EPrice.stonebrick, EPrice.iron_pickaxe, EPrice.iron_axe, EPrice.chainmail_helmet, EPrice.chainmail_chestplate, 
			EPrice.chainmail_leggings, EPrice.chainmail_boots, EPrice.iron_helmet, EPrice.iron_chestplate, EPrice.iron_leggings, EPrice.iron_boots, EPrice.iron_sword}
	),
	DWARF_WARRIOR (EnumTribe.DWARF, "warrior",
			new IItem[]{},
			new IItem[]{}
	),
	DWARF_KING (EnumTribe.DWARF, "king",
			new IItem[]{EPrice.gold_ingot, EPrice.rose_red, EPrice.golden_apple, EPrice.golden_sword, EPrice.golden_helmet},
			new IItem[]{EPrice.bottle_o_enchanting, EPrice.potion_haste, EPrice.potion_speed, EPrice.diamond},
	true),
	DWARF_HEALER (EnumTribe.DWARF, "healer",
			new IItem[]{EPrice.melon, EPrice.glass_bottle, EPrice.nether_wart},
			new IItem[]{EPrice.potion_instant_health, EPrice.potion_health_boost}
    ),
	DWARF_MINER (EnumTribe.DWARF, "miner",
			new IItem[]{EPrice.iron_pickaxe, EPrice.iron_shovel, EPrice.torch},
			new IItem[]{EPrice.coal, EPrice.redstone, EPrice.stone}
	);
	

	
	
	public final String resourcePath;
	public final String villagerName;
	public final int profession;
	public final EnumTribe tribe;
	public final boolean isChief;
	
	private final List<IItem> buyList = new ArrayList<IItem>();
	private final List<IItem> sellList = new ArrayList<IItem>();
	
	private EnumVillager(EnumTribe tribe, String name, IItem[] buyList, IItem[] sellList, boolean isChief) {
		tribe.villagers.add(this);
		this.tribe = tribe;
		this.villagerName = name;
		this.profession = tribe.id + tribe.villagers.size();
		this.isChief = isChief;
		this.resourcePath = PathTool.entityVillagerTexturePath + tribe.path + name + PathTool.ext;
		
		for (IItem item : buyList) {
			this.buyList.add(item);
		}
		for (IItem item : sellList) {
			this.sellList.add(item);
		}
	}
	
	private EnumVillager(EnumTribe tribe, String name, IItem[] buyList, IItem[] sellList) {
		this(tribe, name, buyList, sellList, false);
	}
	
	private EnumVillager(EnumTribe tribe, String name) {
		this(tribe, name, new IItem[]{}, new IItem[]{}, false);
	}

	public static EnumVillager findEnum(int profession) {
		for (EnumVillager villager : EnumVillager.values()) {
			if (profession == villager.profession) {
				return villager;
			}
		}
		return null;
	}
	
	public static void register() {
    	for (EnumVillager villager : EnumVillager.values())
    	{
			VillagerRegistry.instance().registerVillageTradeHandler(villager.profession, (IVillageTradeHandler)villager);
			Diversity.proxy.registerVillagerSkin(villager);
    	}
	}
	
	@Override
	public void manipulateTradesForVillager(AGlobalEntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		if (villager instanceof AGlobalEntityVillager)
		{
			List<IItem> totalList = new ArrayList<IItem>();
			totalList.addAll(buyList);
			totalList.addAll(sellList);
			Collections.shuffle(totalList);
			for (IItem item : totalList)
			{
				if (item instanceof IPrice)
				{
					if (buyList.contains(item))
					{
						recipeList.addToListWithCheck(TradeTools.getBuyTrade((IPrice)item, ((AGlobalEntityVillager) villager).tribe, random));
					}
					if (sellList.contains(item))
					{
						recipeList.addToListWithCheck(TradeTools.getSellTrade((IPrice)item, ((AGlobalEntityVillager) villager).tribe, random));
					}
				}
			}
		}
	}
}