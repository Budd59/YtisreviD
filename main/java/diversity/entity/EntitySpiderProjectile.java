package diversity.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySpiderProjectile extends EntityThrowable
{
    public EntitySpiderProjectile(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntitySpiderProjectile(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntitySpiderProjectile(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition p_70184_1_)
    {
        for (int x = -1; x <= 1; x++)
        for (int y = -2; y <= 0; y++)
        for (int z = -1; z <= 1; z++)
        {
        	if (this.worldObj.getBlock((int)this.posX+x, (int)this.posY+y, (int)this.posZ+z).equals(Blocks.air) && rand.nextBoolean()) {
        		boolean flag = false;
        		
        		for (int temp = -1; temp <= 1; temp=temp+2)
        		{
        			if (!flag)
        				flag = isBlockGoodNeighbour(this.worldObj.getBlock((int)this.posX+x+temp, (int)this.posY+y, (int)this.posZ+z));
    	        	if (!flag)
        	        	flag = isBlockGoodNeighbour(this.worldObj.getBlock((int)this.posX+x, (int)this.posY+y+temp, (int)this.posZ+z));
    	        	if (!flag)
        	        	flag = isBlockGoodNeighbour(this.worldObj.getBlock((int)this.posX+x, (int)this.posY+y, (int)this.posZ+z+temp));
        		}
        		if (flag) {
        			this.worldObj.setBlock((int)this.posX+x, (int)this.posY+y, (int)this.posZ+z, Blocks.web);
        			for (int i=0; i<8; i++)
        				this.worldObj.spawnParticle("cloud", (int)this.posX+x, (int)this.posY+y, (int)this.posZ+z, 0.0D, 0.0D, 0.0D);
        		}
        	}
        }
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    private boolean isBlockGoodNeighbour(Block block) {
    	Material material = block.getMaterial();
    	return !material.equals(Material.air)
    			&& !material.equals(Material.grass)
    			&& !material.equals(Material.vine)
    			&& !material.equals(Material.plants)
    			&& !material.equals(Material.web)
				&& !material.equals(Material.water)
				&& !material.equals(Material.lava);
    }
    
    private boolean couldSpawn(int x, int y, int z) {
    	Block block = this.worldObj.getBlock((int)this.posX+x, (int)this.posY+y, (int)this.posZ+z);
    	Material material = block.getMaterial();
    	return (material.equals(Material.air)
    			|| material.equals(Material.grass)
    			|| material.equals(Material.vine)
    			|| material.equals(Material.plants))
    			&& rand.nextInt(3)==0;
    }
}
