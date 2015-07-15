package diversity.suppliers;

import net.minecraft.potion.Potion;

public enum EnumPotion
{
	regeneration (1),
	swiftness (2),
	fireResistance (3),
	poison(4),
	heal (5),
	nightVision (6),
	clear (7),
	weakness (8),
	strength (9),
	slowness (10),
	leaping (11),
	harm (12),
	waterBreathing (13),
	invisibility (14);

	public static final int POTION = 8192;
	public static final int SPLASH = 16384;
	public static final int TIER_I = 0;
	public static final int TIER_II = 32;
	public static final int EXTENDED = 64;
	
	public final int id_potion_I;
	public final int id_potion_II;
	public final int id_potion_I_EXT;
	public final int id_potion_II_EXT;
	public final int id_splash_I;
	public final int id_splash_II;
	public final int id_splash_I_EXT;
	public final int id_splash_II_EXT;
	
	EnumPotion(int id) {
		this.id_potion_I = id + POTION + TIER_I;
		this.id_potion_II = id + POTION + TIER_II;
		this.id_potion_I_EXT = id + POTION + TIER_I + EXTENDED;
		this.id_potion_II_EXT = id + POTION + TIER_II + EXTENDED;
		this.id_splash_I = id + SPLASH + TIER_I;
		this.id_splash_II = id + SPLASH + TIER_II;
		this.id_splash_I_EXT = id + SPLASH + TIER_I + EXTENDED;
		this.id_splash_II_EXT = id + SPLASH + TIER_II + EXTENDED;
	}
	
	public static enum EnumSpecialPotion {
		mundane (0),
		clear (7),
		thin (15),
		awkward (16),
		bungling (23),
		debonair (31),
		thick (32),
		charming (39),
		sparkling (47),
		potent (48),
		rank (55),
		stinky (63);
		
		public final int id;
		
		EnumSpecialPotion(int id) {
			this.id = id + POTION;
		}
	}
}