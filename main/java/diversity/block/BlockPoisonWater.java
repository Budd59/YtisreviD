package diversity.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import diversity.suppliers.EnumFluid;

public class BlockPoisonWater extends BlockFluidClassic
{	
	public BlockPoisonWater() {
		super(EnumFluid.poison_water.fluid, Material.water);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		getFluid().setStillIcon(iconRegister.registerIcon(getTextureName()+"_still"));
		getFluid().setFlowingIcon(iconRegister.registerIcon(getTextureName()+"_flow"));
	}
	
    @Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? getFluid().getStillIcon() : getFluid().getFlowingIcon() ;
    }
    
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityPlayer) {
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 4));
		}
		else if (entity instanceof EntityLiving) {
			((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 4));
		}
	}
}