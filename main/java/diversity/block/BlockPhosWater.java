package diversity.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diversity.Diversity;
import diversity.suppliers.EnumFluid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStaticLiquid;
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
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockPhosWater extends BlockFluidClassic
{	
	public BlockPhosWater() {
		super(EnumFluid.phos_water.fluid, Material.water);
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
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.nightVision.id, 200, 4));
		}
		else if (entity instanceof EntityWitch) {
			((EntityWitch)entity).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20, 1));
		}
	}
	
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        super.randomDisplayTick(world, x, y, z, random);

        if (random.nextInt(10) == 0)
        {
            world.spawnParticle("smoke", (double)((float)x + random.nextFloat()), (double)((float)y + 1.1F), (double)((float)z + random.nextFloat()), 0.0D, 0.0D, 0.0D);
        }
    }
}
