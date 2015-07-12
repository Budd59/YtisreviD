package diversity.suppliers;

import net.minecraft.potion.Potion;

public enum EnumPotion
{
	/* POTION DATA VALUES from http://www.minecraftwiki.net/wiki/Potions
	* EXT means "Extended" version of potion
	* REV means "Reverted" version of potion
	*/
	POTION_AWKWARD(16),
	POTION_THICK(32),
	POTION_MUNDANE(128),
	POTION_MUNDANE_EXT(64),
	
	/*
	* HELPFUL POTIONS
	*/
	POTION_REGEN(8192 + Potion.regeneration.id),
	POTION_REGEN_II(8192 + 32 + Potion.regeneration.id),
	POTION_REGEN_EXT(8192 + 64 + Potion.regeneration.id),
	POTION_REGEN_II_EXT(8192 + 64 + 32 + Potion.regeneration.id),
	POTION_REGEN_SPLASH(16384 + Potion.regeneration.id),
	POTION_REGEN_SPLASH_II(16384 + 32 + Potion.regeneration.id),
	POTION_REGEN_SPLASH_EXT(16384 + 64 + Potion.regeneration.id),
	
	POTION_SWIFTNESS(8192 + Potion.moveSpeed.id),
	POTION_SWIFTNESS_II(8192 + 32 + Potion.moveSpeed.id),
	POTION_SWIFTNESS_EXT(8192 + 64 + Potion.moveSpeed.id),
	POTION_SWIFTNESS_II_EXT(8192 + 64 + 32 + Potion.moveSpeed.id),
	POTION_SWIFTNESS_SPLASH(16384 + Potion.moveSpeed.id),
	POTION_SWIFTNESS_SPLASH_II(16384 + 32 + Potion.moveSpeed.id),
	POTION_SWIFTNESS_SPLASH_EXT(16384 + 64 + Potion.moveSpeed.id),
	
	POTION_FIRERESIST(8192 + Potion.fireResistance.id),
	POTION_FIRERESIST_REV(8192 + 32 + Potion.fireResistance.id),
	POTION_FIRERESIST_EXT(8192 + 64 + Potion.fireResistance.id),
	POTION_FIRERESIST_SPLASH(16384 + Potion.fireResistance.id),
	POTION_FIRERESIST_SPLASH_REV(16384 + 32 + Potion.fireResistance.id),
	POTION_FIRERESIST_SPLASH_EXT(16384 + 64 + Potion.fireResistance.id),
	
	POTION_HEALING(8192 + Potion.heal.id),
	POTION_HEALING_II(8192 + 32 + Potion.heal.id),
	POTION_HEALING_REV(8192 + 64 + Potion.heal.id),
	POTION_HEALING_SPLASH(16384 + Potion.heal.id),
	POTION_HEALING_SPLASH_II(16384 + 32 + Potion.heal.id),
	POTION_HEALING_SPLASH_REV(16384 + 64 + Potion.heal.id),
	
	POTION_NIGHTVISION(8192 + Potion.nightVision.id),
	POTION_NIGHTVISION_REV(8192 + 32 + Potion.nightVision.id),
	POTION_NIGHTVISION_EXT(8192 + 64 + Potion.nightVision.id),
	POTION_NIGHTVISION_SPLASH(16384 + Potion.nightVision.id),
	POTION_NIGHTVISION_SPLASH_REV(16384 + 32 + Potion.nightVision.id),
	POTION_NIGHTVISION_SPLASH_EXT(16384 + 64 + Potion.nightVision.id),
	
	POTION_STRENGTH(8192 + Potion.damageBoost.id),
	POTION_STRENGTH_II(8192 + 32 + Potion.damageBoost.id),
	POTION_STRENGTH_EXT(8192 + 64 + Potion.damageBoost.id),
	POTION_STRENGTH_II_EXT(8192 + 64 + 32 + Potion.damageBoost.id),
	POTION_STRENGTH_SPLASH(16384 + Potion.damageBoost.id),
	POTION_STRENGTH_SPLASH_II(16384 + 32 + Potion.damageBoost.id),
	POTION_STRENGTH_SPLASH_EXT(16384 + 64 + Potion.damageBoost.id),
	
	POTION_INVISIBILITY(8192 + Potion.invisibility.id),
	POTION_INVISIBILITY_REV(8192 + 32 + Potion.invisibility.id),
	POTION_INVISIBILITY_EXT(8192 + 64 + Potion.invisibility.id),
	POTION_INVISIBILITY_SPLASH(16384 + Potion.invisibility.id),
	POTION_INVISIBILITY_SPLASH_REV(16384 + 32 + Potion.invisibility.id),
	POTION_INVISIBILITY_SPLASH_EXT(16384 + 64 + 32 + Potion.invisibility.id),
	
	/*
	* HARMFUL POTIONS
	*/
	POTION_POISON(8192 + Potion.poison.id),
	POTION_POISON_II(8192 + 32 + Potion.poison.id),
	POTION_POISON_EXT(8192 + 64 + Potion.poison.id),
	POTION_POISON_SPLASH(16384 + Potion.poison.id),
	POTION_POISON_SPLASH_II(16384 + 32 + Potion.poison.id),
	POTION_POISON_SPLASH_EXT(16384 + 64 + Potion.poison.id),
	
	POTION_WEAKNESS(8192 + Potion.weakness.id),
	POTION_WEAKNESS_REV(8192 + 32 + Potion.weakness.id),
	POTION_WEAKNESS_EXT(8192 + 64 + Potion.weakness.id),
	POTION_WEAKNESS_SPLASH(16384 + Potion.weakness.id),
	POTION_WEAKNESS_SPLASH_REV(16384 + 32 + Potion.weakness.id),
	POTION_WEAKNESS_SPLASH_EXT(16384 + 64 + Potion.weakness.id),
	
	POTION_SLOWNESS(8192 + Potion.moveSlowdown.id),
	POTION_SLOWNESS_REV(8192 + 32 + Potion.moveSlowdown.id),
	POTION_SLOWNESS_EXT(8192 + 64 + Potion.moveSlowdown.id),
	POTION_SLOWNESS_SPLASH(16384 + Potion.moveSlowdown.id),
	POTION_SLOWNESS_SPLASH_REV(16384 + 32 + Potion.moveSlowdown.id),
	POTION_SLOWNESS_SPLASH_EXT(16384 + 64 + Potion.moveSlowdown.id),
	
	POTION_HARM(8192 + Potion.harm.id),
	POTION_HARM_II(8192 + 32 + Potion.harm.id),
	POTION_HARM_REV(8192 + 64 + Potion.harm.id),
	POTION_HARM_SPLASH(16384 + Potion.harm.id),
	POTION_HARM_SPLASH_II(16384 + 32 + Potion.harm.id),
	POTION_HARM_SPLASH_REV(16384 + 64 + Potion.harm.id);
	
	
	public final int potionID;
	
	private EnumPotion(int par1) {
		this.potionID = par1;
	}
}