package diversity.suppliers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.village.MerchantRecipeList;

import org.apache.commons.lang3.ArrayUtils;

import diversity.Diversity;
import diversity.configurations.ConfigEconomy.EnumObject;
import diversity.configurations.ConfigEconomy.EnumGroupObject;
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
			new IItem[]{EnumObject.wheat, EnumObject.wheat_seeds, EnumObject.leather},
			new IItem[]{EnumObject.iron_horse_armor, EnumObject.saddle, EnumObject.lead}
	),
	APACHE_HUNTER (EnumTribe.APACHE, "hunter",
			new IItem[]{EnumObject.string, EnumObject.stick, EnumObject.flint, EnumObject.feather},
			new IItem[]{EnumObject.bow, EnumObject.arrow, EnumObject.lead}
	),
	APACHE_SHAMAN (EnumTribe.APACHE, "shaman",
			new IItem[]{EnumObject.bone, EnumObject.gray_dye, EnumObject.orange_dye, EnumObject.light_blue_dye, EnumObject.bowl},
			new IItem[]{EnumObject.bottle_o_enchanting, EnumPotion.regeneration.potion_I_ext}
	),
	APACHE_CHIEF (EnumTribe.APACHE, "chief",
			new IItem[]{EnumObject.feather},
			new IItem[]{EnumObject.stone_axe, EnumObject.golden_horse_armor, EnumObject.emerald},
	true),
	APACHE_WARRIOR (EnumTribe.APACHE, "warrior",
			new IItem[]{EnumObject.iron_axe, EnumObject.iron_sword, EnumObject.leather},
			new IItem[]{EnumObject.stone_axe, EnumObject.stone_sword, EnumObject.lead, EnumObject.leather_helmet, EnumObject.leather_tunic, EnumObject.leather_pants, EnumObject.leather_boots}
	),
	
	AZTEC_HUNTER (EnumTribe.AZTEC, "hunter",
			new IItem[]{},
			new IItem[]{}
			//new IItem[]{EPrice.string, EPrice.stick, EPrice.flint, EPrice.feather},
			//new IItem[]{EPrice.arrow, EPrice.bow}
	),
	AZTEC_DYER (EnumTribe.AZTEC, "dyer",
			new IItem[]{EnumObject.red_wool, EnumObject.dandelion_yellow, EnumObject.cactus_green},
			new IItem[]{EnumObject.yellow_wool, EnumObject.lime_wool}
	),
	AZTEC_CHIEF (EnumTribe.AZTEC, "chief",
			new IItem[]{EnumObject.diamond},
			new IItem[]{EnumObject.emerald},
	true),
	AZTEC_HIGHPRIEST (EnumTribe.AZTEC, "highpriest",
			new IItem[]{EnumObject.ender_pearl, EnumObject.rose_red},
			new IItem[]{EnumObject.emerald, EnumPotion.regeneration.potion_II, EnumPotion.leaping.potion_II_ext}
	),
	AZTEC_BREEDER (EnumTribe.AZTEC, "breeder",
			new IItem[]{EnumObject.wheat_seeds, EnumObject.pumpkin_seeds, EnumObject.melon_seeds},
			new IItem[]{EnumObject.raw_chicken, EnumObject.cooked_chicken, EnumObject.egg}
	),
	AZTEC_FARMER (EnumTribe.AZTEC, "farmer",
			new IItem[]{EnumObject.wheat_seeds, EnumObject.pumpkin_seeds, EnumObject.melon_seeds},
			new IItem[]{EnumObject.raw_chicken, EnumObject.cooked_chicken, EnumObject.egg}
	),

	INUIT_FISHERMAN (EnumTribe.INUIT, "fisherman",
			new IItem[]{EnumObject.stick, EnumObject.string, EnumObject.leather, EnumObject.white_wool},
			new IItem[]{EnumObject.raw_fish, EnumObject.cooked_fish, EnumObject.raw_salmon, EnumObject.cooked_salmon, EnumObject.fishing_rod}
	),
	INUIT_HUNTER (EnumTribe.INUIT, "hunter",
			new IItem[]{EnumObject.string, EnumObject.stick, EnumObject.flint, EnumObject.feather, EnumObject.leather},
			new IItem[]{EnumObject.bow, EnumObject.arrow, EnumObject.leather_helmet, EnumObject.leather_tunic, EnumObject.leather_pants, EnumObject.leather_boots}
	),
	INUIT_KENNELMASTER (EnumTribe.INUIT, "kennelmaster",
			new IItem[]{EnumObject.bone, EnumObject.raw_porkchop, EnumObject.raw_beef, EnumObject.raw_fish, EnumObject.raw_salmon},
			new IItem[]{EnumObject.lead}
	),
	INUIT_CHIEF (EnumTribe.INUIT, "chief",
			new IItem[]{EnumObject.diamond, EnumObject.diamond_sword},
			new IItem[]{EnumObject.emerald, EnumObject.enchanted_book_fortune, EnumObject.enchanted_book_silk_touch},
	true),
	
	ZULU_FARMER (EnumTribe.ZULU, "farmer",
			new IItem[]{EnumObject.wheat, EnumObject.wheat_seeds},
			new IItem[]{EnumObject.wheat, EnumObject.bread, EnumObject.stone_hoe}
	),
	ZULU_WARRIOR (EnumTribe.ZULU, "warrior",
			new IItem[]{EnumObject.iron_axe, EnumObject.iron_sword, EnumObject.leather},
			new IItem[]{EnumObject.stone_axe, EnumObject.stone_sword, EnumObject.iron_axe, EnumObject.leather_helmet, EnumObject.leather_tunic, EnumObject.leather_pants, EnumObject.leather_boots}
	),
	ZULU_BREEDER (EnumTribe.ZULU, "breeder",
			new IItem[]{EnumObject.wheat, EnumObject.wheat_seeds},
			new IItem[]{EnumObject.raw_beef, EnumObject.steak, EnumObject.leather}
	),
	ZULU_CHIEF (EnumTribe.ZULU, "chief",
			new IItem[]{EnumObject.golden_sword, EnumObject.golden_helmet, EnumObject.golden_chesplate, EnumObject.golden_leggings, EnumObject.golden_boots},
			new IItem[]{EnumObject.emerald, EnumObject.diamond},
	true),
	ZULU_GURU (EnumTribe.ZULU, "guru",
			new IItem[]{EnumObject.bone, EnumObject.ink, EnumObject.light_gray_dye, EnumObject.gray_dye, EnumObject.bone_meal},
			new IItem[]{EnumObject.bowl, EnumObject.bottle_o_enchanting, EnumPotion.swiftness.potion_II, EnumPotion.harm.potion_II_ext, EnumPotion.weakness.splash_II}
	),
	
	TIBETAN_MONK (EnumTribe.TIBETAN, "monk",
			new IItem[]{EnumObject.pumpkin, EnumObject.pumpkin_seeds, EnumObject.wheat_seeds},
			new IItem[]{EnumObject.wheat, EnumObject.bread, EnumObject.pumpkin_pie}
	),
	TIBETAN_MASTER (EnumTribe.TIBETAN, "master",
			new IItem[]{EnumObject.rose_red, EnumObject.dandelion_yellow, EnumObject.orange_dye, EnumObject.bone_meal},
			new IItem[]{EnumObject.bottle_o_enchanting, EnumPotion.invisibility.potion_II, EnumObject.enchanted_book_infinity}
	),
	TIBETAN_GREATWISE (EnumTribe.TIBETAN, "greatwise",
			new IItem[]{EnumObject.clock, EnumObject.enchanted_book_protection},
			new IItem[]{EnumObject.emerald, EnumObject.compass, EnumObject.diamond, EnumObject.ender_pearl},
	true),
	
	EGYPTIAN_FARMER (EnumTribe.EGYPTIAN, "farmer",
			new IItem[]{EnumObject.bone_meal, EnumObject.wheat_seeds, EnumObject.sugar_canes},
			new IItem[]{EnumObject.wheat, EnumObject.sugar}
	),
	EGYPTIAN_SCULPTOR (EnumTribe.EGYPTIAN, "sculptor",
			new IItem[]{EnumObject.sandstone},
			new IItem[]{EnumObject.chiseled_sandstone, EnumObject.smooth_sandstone}
	),
	EGYPTIAN_SCRIBE (EnumTribe.EGYPTIAN, "scribe",
			new IItem[]{EnumObject.ink, EnumObject.lapis_lazuli, EnumObject.purple_dye, EnumObject.paper, EnumObject.sugar},
			new IItem[]{EnumObject.map, EnumObject.empty_map}
	),
	EGYPTIAN_PRIEST (EnumTribe.EGYPTIAN, "priest",
			new IItem[]{EnumObject.ink, EnumObject.lapis_lazuli, EnumObject.purple_dye, EnumObject.bone_meal},
			new IItem[]{EnumObject.bottle_o_enchanting, EnumObject.bowl, EnumObject.enchanted_book_protection, EnumObject.enchanted_book_blast_protection, EnumObject.enchanted_book_flame}
	),
	EGYPTIAN_PAINTER (EnumTribe.EGYPTIAN, "painter",
			ArrayUtils.addAll(new IItem[]{EnumObject.paper, EnumObject.white_wool, EnumObject.stick}, EnumGroupObject.dye.getIPrices()),
			new IItem[]{EnumObject.painting}
	),
	EGYPTIAN_GUARD (EnumTribe.EGYPTIAN, "guard",
			new IItem[]{EnumObject.iron_axe, EnumObject.iron_sword, EnumObject.leather},
			new IItem[]{EnumObject.stone_axe, EnumObject.stone_sword, EnumObject.lead, EnumObject.iron_helmet, EnumObject.iron_chestplate, EnumObject.iron_leggings, EnumObject.iron_boots}
	),
	EGYPTIAN_PHARAOH (EnumTribe.EGYPTIAN, "pharaoh",
			new IItem[]{EnumObject.diamond, EnumObject.compass},
			new IItem[]{EnumObject.emerald, EnumObject.clock},
	true),
	
	LAKESIDE_FISHERMAN (EnumTribe.LAKESIDE, "fisherman",
			new IItem[]{EnumObject.stick, EnumObject.string},
			new IItem[]{EnumObject.raw_fish, EnumObject.cooked_fish, EnumObject.fishing_rod}
	),
	LAKESIDE_FARMER (EnumTribe.LAKESIDE, "farmer",
			new IItem[]{EnumObject.iron_hoe},
			new IItem[]{EnumObject.carrot, EnumObject.potato, EnumObject.baked_potato, EnumObject.lily_pad, EnumObject.vines}
	),	
	LAKESIDE_BREEDER (EnumTribe.LAKESIDE, "breeder",
			new IItem[]{EnumObject.potato, EnumObject.poisonous_potato, EnumObject.carrot},
			new IItem[]{EnumObject.raw_porkchop, EnumObject.cooked_porkchop, EnumObject.bone}
	),
	LAKESIDE_GUARD (EnumTribe.LAKESIDE, "guard",
			new IItem[]{EnumObject.iron_axe, EnumObject.iron_sword, EnumObject.leather},
			new IItem[]{EnumObject.stone_axe, EnumObject.stone_sword, EnumObject.lead, EnumObject.iron_helmet, EnumObject.iron_chestplate, EnumObject.iron_leggings, EnumObject.iron_boots}
	),
	LAKESIDE_CHIEF (EnumTribe.LAKESIDE, "chief",
			new IItem[]{EnumObject.compass},
			new IItem[]{EnumObject.emerald, EnumObject.enchanted_book_punch, EnumObject.enchanted_book_aqua_affinity},
	true),
	
	SETTLED_BUTCHER (EnumTribe.SETTLED, "butcher",
			new IItem[]{EnumObject.coal, EnumObject.raw_porkchop},
			new IItem[]{EnumObject.leather_boots, EnumObject.leather_tunic, EnumObject.leather_pants, EnumObject.leather_boots, EnumObject.saddle, EnumObject.cooked_porkchop, EnumObject.steak, EnumObject.carrot_on_a_stick}
	),		
	SETTLED_FARMER (EnumTribe.SETTLED, "farmer",
			new IItem[]{EnumObject.wheat_seeds, EnumObject.carrot, EnumObject.potato},
			new IItem[]{EnumObject.wheat, EnumObject.carrot, EnumObject.baked_potato}
	),
	SETTLED_LIBRARIAN (EnumTribe.SETTLED, "librarian",
			new IItem[]{EnumObject.paper, EnumObject.book},
			new IItem[]{EnumObject.compass, EnumObject.clock, EnumObject.bookshelf, EnumObject.enchanted_book_looting, EnumObject.enchanted_book_projectile_protection, EnumObject.enchanted_book_sharpness}
	),
	SETTLED_PRIEST (EnumTribe.SETTLED, "priest",
			new IItem[]{EnumObject.redstone, EnumObject.glowstone_dust},
			new IItem[]{EnumObject.ender_pearl, EnumObject.bottle_o_enchanting, EnumPotion.invisibility.potion_II, EnumPotion.heal.potion_II}
	),
	SETTLED_SMITH (EnumTribe.SETTLED, "smith",
			new IItem[]{EnumObject.iron_ingot},
			new IItem[]{EnumObject.chainmail_helmet, EnumObject.chainmail_chestplate, EnumObject.chainmail_leggings, EnumObject.chainmail_boots, EnumObject.iron_helmet, EnumObject.iron_chestplate, EnumObject.iron_leggings, EnumObject.iron_boots}
	),
	SETTLED_VILLAGER (EnumTribe.SETTLED, "villager",
			new IItem[]{EnumObject.wheat, EnumObject.white_wool},
			new IItem[]{EnumObject.apple, EnumObject.cookie, EnumObject.shears, EnumObject.bread}
	),
	SETTLED_GUARD (EnumTribe.SETTLED, "guard"),
	SETTLED_INNKEEPER (EnumTribe.SETTLED, "innkeeper",
			new IItem[]{},
			new IItem[]{EnumObject.apple, EnumObject.cooked_porkchop, EnumObject.cooked_chicken, EnumObject.bread, EnumObject.mushroom_stew}
	),
	DWARF_SMITH (EnumTribe.DWARF, "smith",
			new IItem[]{EnumObject.coal, EnumObject.cobblestone, EnumObject.oak_planks, EnumObject.iron_ingot},
			new IItem[]{EnumObject.stonebrick, EnumObject.iron_pickaxe, EnumObject.iron_axe, EnumObject.chainmail_helmet, EnumObject.chainmail_chestplate, 
			EnumObject.chainmail_leggings, EnumObject.chainmail_boots, EnumObject.iron_helmet, EnumObject.iron_chestplate, EnumObject.iron_leggings, EnumObject.iron_boots, EnumObject.iron_sword}
	),
	DWARF_WARRIOR (EnumTribe.DWARF, "warrior",
			new IItem[]{},
			new IItem[]{}
	),
	DWARF_KING (EnumTribe.DWARF, "king",
			new IItem[]{EnumObject.gold_ingot, EnumObject.rose_red, EnumObject.golden_apple, EnumObject.golden_sword, EnumObject.golden_helmet},
			new IItem[]{EnumObject.bottle_o_enchanting, EnumObject.diamond, EnumPotion.nightVision.potion_II_ext, EnumPotion.strength.potion_II_ext, EnumPotion.poison.splash_I},
	true),
	DWARF_HEALER (EnumTribe.DWARF, "healer",
			new IItem[]{EnumObject.melon, EnumObject.glass_bottle, EnumObject.nether_wart},
			new IItem[]{EnumPotion.regeneration.splash_II, EnumPotion.regeneration.potion_I_ext}
    ),
	DWARF_MINER (EnumTribe.DWARF, "miner",
			new IItem[]{EnumObject.iron_pickaxe, EnumObject.iron_shovel, EnumObject.torch},
			new IItem[]{EnumObject.coal, EnumObject.redstone, EnumObject.stone}
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