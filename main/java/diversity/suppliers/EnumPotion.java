package diversity.suppliers;

import diversity.configurations.ConfigEconomy.EnumObject;
import diversity.configurations.ConfigEconomy.PPrice;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public enum EnumPotion
{
	regeneration (1, 30),
	swiftness (2, 30),
	fireResistance (3, 30),
	poison(4, 30),
	heal (5, 30),
	nightVision (6, 30),
	weakness (8, 30),
	strength (9, 30),
	slowness (10, 30),
	leaping (11, 30),
	harm (12, 30),
	waterBreathing (13, 30),
	invisibility (14, 30);	
	
	private static final int potion = 8192;
	private static final int splash = 16384;
	private static final int I = 0;
	private static final int II = 32;
	private static final int extended = 64;
	
	private static final int POTION_I = potion + I;
	private static final int POTION_II = potion + II;
	private static final int POTION_I_EXT = potion + I + extended;
	private static final int POTION_II_EXT = potion + II + extended;
	private static final int SPLASH_I = splash + I;
	private static final int SPLASH_II = splash + II;
	private static final int SPLASH_I_EXT = splash + I + extended;
	private static final int SPLASH_II_EXT = splash + II + extended;
	
	private final int id;
	public PPrice potion_I;
	public PPrice potion_II;
	public PPrice potion_I_ext;
	public PPrice potion_II_ext;
	public PPrice splash_I;
	public PPrice splash_II;
	public PPrice splash_I_ext;
	public PPrice splash_II_ext;
	
	private EnumPotion(int id, float price) {
		this.id = id;
		potion_I = new PPrice(getName(EnumPotion.POTION_I), getPotionID(EnumPotion.POTION_I), price);
		potion_II = new PPrice(getName(EnumPotion.POTION_II), getPotionID(EnumPotion.POTION_II), price*3/2);
		potion_I_ext = new PPrice(getName(EnumPotion.POTION_I_EXT), getPotionID(EnumPotion.POTION_I_EXT), price*8/3);
		potion_II_ext = new PPrice(getName(EnumPotion.POTION_II_EXT), getPotionID(EnumPotion.POTION_II_EXT), price*3/2*8/3);
		splash_I = new PPrice(getName(EnumPotion.SPLASH_I), getPotionID(EnumPotion.SPLASH_I), price);
		splash_II = new PPrice(getName(EnumPotion.SPLASH_II), getPotionID(EnumPotion.SPLASH_II), price*3/2);
		splash_I_ext = new PPrice(getName(EnumPotion.SPLASH_I_EXT), getPotionID(EnumPotion.SPLASH_I_EXT), price*8/3);
		splash_II_ext = new PPrice(getName(EnumPotion.SPLASH_II_EXT), getPotionID(EnumPotion.SPLASH_II_EXT), price*3/2*8/3);
	}
	
	private int getPotionID(int type) {
		switch (type)
		{
			case POTION_I:
			case POTION_II:
			case POTION_I_EXT:
			case POTION_II_EXT:
			case SPLASH_I:
			case SPLASH_II:
			case SPLASH_I_EXT:
			case SPLASH_II_EXT:
				return this.id + type;
		}
		return 0;
	}
	
	private String getName(int type) {
		String s = "potionitem_" + name();
		switch (type)
		{
			case POTION_I:
				return s + "_potion_I";
			case POTION_II:
				return s + "_potion_II";
			case POTION_I_EXT:
				return s + "_potion_I_ext";
			case POTION_II_EXT:
				return s + "_potion_II_ext";
			case SPLASH_I:
				return s + "_splash_I";
			case SPLASH_II:
				return s + "_splash_II";
			case SPLASH_I_EXT:
				return s + "_splash_I_ext";
			case SPLASH_II_EXT:
				return s + "_splash_II_ext";
		}
		return s;
	}
	
	public static enum EnumSpecialPotion {
		mundane (0, 20),
		clear (7, 20),
		thin (15, 20),
		awkward (16, 20),
		bungling (23, 20),
		debonair (31, 20),
		thick (32, 20),
		charming (39, 20),
		sparkling (47, 20),
		potent (48, 20),
		rank (55, 20),
		stinky (63, 20);
		
		private final int id;
		public final PPrice potion;
		
		private EnumSpecialPotion(int id, float price) {
			this.id = id;
			this.potion = new PPrice(name(), id, price);
		}
		
		private String getName(int type) {
			return "potionitem_" + name();
		}
	}
}