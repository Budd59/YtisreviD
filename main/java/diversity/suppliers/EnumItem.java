package diversity.suppliers;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import diversity.Diversity;
import diversity.entity.EntityDart;
import diversity.item.ItemBlowgun;
import diversity.item.ItemSpear;

public enum EnumItem
{
	blowgun (new ItemBlowgun().setUnlocalizedName("blowgun").setCreativeTab(CreativeTabs.tabCombat).setTextureName(Diversity.MODID+":blowgun")),
	dart (new Item().setUnlocalizedName("dart").setCreativeTab(CreativeTabs.tabCombat).setTextureName(Diversity.MODID+":dart"), EntityDart.class),
	iron_spear (new ItemSpear(Item.ToolMaterial.IRON).setUnlocalizedName("iron_spear").setTextureName(Diversity.MODID+":iron_spear")),
	wooden_spear (new ItemSpear(Item.ToolMaterial.WOOD).setUnlocalizedName("wooden_spear").setTextureName(Diversity.MODID+":wooden_spear")),
	stone_spear (new ItemSpear(Item.ToolMaterial.STONE).setUnlocalizedName("stone_spear").setTextureName(Diversity.MODID+":stone_spear")),
	diamond_spear (new ItemSpear(Item.ToolMaterial.EMERALD).setUnlocalizedName("diamond_spear").setTextureName(Diversity.MODID+":diamond_spear")),
	golden_spear (new ItemSpear(Item.ToolMaterial.GOLD).setUnlocalizedName("golden_spear").setTextureName(Diversity.MODID+":golden_spear")),
	phos_water_bucket (new ItemBucket(EnumBlock.phos_water.block).setUnlocalizedName("phos_water_bucket").setContainerItem(Items.bucket).setTextureName(Diversity.MODID+":phos_water_bucket")),
	poison_water_bucket (new ItemBucket(EnumBlock.poison_water.block).setUnlocalizedName("poison_water_bucket").setContainerItem(Items.bucket).setTextureName(Diversity.MODID+":poison_water_bucket"));

	public Item item;
	public final Class entityClass;
	
	private EnumItem(Item item) {
		this(item, null);
	}
	
	private EnumItem(Item item, Class entityClass) {
		this.item = item;
		this.entityClass = entityClass;
	}
	
	public static void register() {
		for (EnumItem item : EnumItem.values()) {
			GameRegistry.registerItem(item.item, item.name());
			if (item.entityClass != null) {
				int id = EntityRegistry.findGlobalUniqueEntityId();
				EntityRegistry.registerGlobalEntityID(item.entityClass, Diversity.MODID + "." + item.name(), id);
				EntityRegistry.registerModEntity(item.entityClass, Diversity.MODID + "." + item.name(), id, Diversity.instance, 64, 1, true);
			}
			Diversity.proxy.registerItemRenderer(item);
		}
	}
}