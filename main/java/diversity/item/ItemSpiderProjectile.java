package diversity.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import diversity.entity.EntitySpiderProjectile;

public class ItemSpiderProjectile  extends Item
{
	
    protected EntitySpiderProjectile entityBall;
    
	public ItemSpiderProjectile()
	{
    super();
	}

@Override
public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, 
      EntityPlayer par3EntityPlayer)
{
    if (!par3EntityPlayer.capabilities.isCreativeMode)
    {
        --par1ItemStack.stackSize;
    }

    par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / 
          (itemRand.nextFloat() * 0.4F + 0.8F));

    if (!par2World.isRemote)
    {
        entityBall = new EntitySpiderProjectile(par2World, par3EntityPlayer);
        par2World.spawnEntityInWorld(entityBall);
    }

    return par1ItemStack;
}

}
