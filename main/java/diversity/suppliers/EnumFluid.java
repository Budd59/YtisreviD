package diversity.suppliers;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import diversity.block.FluidPhosWater;
import diversity.block.FluidPoisonWater;

public enum EnumFluid
{
	phos_water (new FluidPhosWater().setUnlocalizedName("phos_water").setLuminosity(6).setRarity(EnumRarity.rare)),
	poison_water (new FluidPoisonWater().setUnlocalizedName("poison_water").setRarity(EnumRarity.rare));
	
	public final Fluid fluid;

	private EnumFluid (Fluid fluid) {
		this.fluid = fluid;
		FluidRegistry.registerFluid(this.fluid);
	}
	
	public static void register() {
		for (EnumFluid enumFluid : EnumFluid.values()) {
			FluidRegistry.registerFluid(enumFluid.fluid);
		}
	}
}