package diversity.structure;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public abstract class GlobalFeature extends StructureComponent
{
    /** The size of the bounding box for this feature in the X axis */
    protected int scatteredFeatureSizeX;
    /** The size of the bounding box for this feature in the Y axis */
    protected int scatteredFeatureSizeY;
    /** The size of the bounding box for this feature in the Z axis */
    protected int scatteredFeatureSizeZ;
    protected int field_74936_d = -1;
    	
    public GlobalFeature() {}

    public GlobalFeature(Random random, int coordX, int coordZ, int sizeX, int sizeY, int sizeZ)
    {
        super(0);
        this.scatteredFeatureSizeX = sizeX;
        this.scatteredFeatureSizeY = sizeY;
        this.scatteredFeatureSizeZ = sizeZ;
        this.coordBaseMode = random.nextInt(4);

        switch (this.coordBaseMode)
        {
            case 0:
            case 2:
                this.boundingBox = new StructureBoundingBox(coordX, 64, coordZ, coordX + sizeX - 1, 64 + sizeY - 1, coordZ + sizeZ - 1);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(coordX, 64, coordZ, coordX + sizeZ - 1, 64 + sizeY - 1, coordZ + sizeX - 1);
        }
    }

    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        p_143012_1_.setInteger("Width", this.scatteredFeatureSizeX);
        p_143012_1_.setInteger("Height", this.scatteredFeatureSizeY);
        p_143012_1_.setInteger("Depth", this.scatteredFeatureSizeZ);
        p_143012_1_.setInteger("HPos", this.field_74936_d);
    }

    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        this.scatteredFeatureSizeX = p_143011_1_.getInteger("Width");
        this.scatteredFeatureSizeY = p_143011_1_.getInteger("Height");
        this.scatteredFeatureSizeZ = p_143011_1_.getInteger("Depth");
        this.field_74936_d = p_143011_1_.getInteger("HPos");
    }

    protected boolean func_74935_a(World p_74935_1_, StructureBoundingBox p_74935_2_, int p_74935_3_)
    {
        if (this.field_74936_d >= 0)
        {
            return true;
        }
        else
        {
            int j = 0;
            int k = 0;

            for (int l = this.boundingBox.minZ; l <= this.boundingBox.maxZ; ++l)
            {
                for (int i1 = this.boundingBox.minX; i1 <= this.boundingBox.maxX; ++i1)
                {
                    if (p_74935_2_.isVecInside(i1, 64, l))
                    {
                        j += Math.max(p_74935_1_.getTopSolidOrLiquidBlock(i1, l), p_74935_1_.provider.getAverageGroundLevel());
                        ++k;
                    }
                }
            }

            if (k == 0)
            {
                return false;
            }
            else
            {
                this.field_74936_d = j / k;
                this.boundingBox.offset(0, this.field_74936_d - this.boundingBox.minY + p_74935_3_, 0);
                return true;
            }
        }
    }
    
    @Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
	{
        //if (!this.func_74935_a(world, structureBoundingBox, 0))
        //{
        ///	return false
    	//}
        
        build(world, random, structureBoundingBox);
        
		return true;
	}

	protected abstract boolean build(World world, Random random, StructureBoundingBox structureBoundingBox);
    
    protected void spawnEntity(World world, StructureBoundingBox structureBoundingBox, int spawnX, int spawnY, int spawnZ, int choice)
    {
    	int x = this.getXWithOffset(spawnX, spawnZ);
        int y = this.getYWithOffset(spawnY);
        int z = this.getZWithOffset(spawnX, spawnZ);
    	
        if (!structureBoundingBox.isVecInside(x, y, z))
        {
            return;
        }
        
        EntityLiving entity = getNewEntity(world, choice);
        entity.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
        world.spawnEntityInWorld(entity);
    }
    
    protected abstract EntityLiving getNewEntity(World world, int choice);
    
    /**
     * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of
     * all the levels in the BB's horizontal rectangle).
     */
    protected int getAverageGroundLevel(World p_74889_1_, StructureBoundingBox p_74889_2_)
    {
        int i = 0;
        int j = 0;

        for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
        {
            for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
            {
                if (p_74889_2_.isVecInside(l, 64, k))
                {
                    i += Math.max(p_74889_1_.getTopSolidOrLiquidBlock(l, k), p_74889_1_.provider.getAverageGroundLevel());
                    ++j;
                }
            }
        }

        if (j == 0)
        {
            return -1;
        }
        else
        {
            return i / j;
        }
    }
    
    /**
     * Used to generate chests with items in it. ex: Temple Chests, Village Blacksmith Chests, Mineshaft Chests.
     */
    protected boolean generateStructureTrappedChestContents(World p_74879_1_, StructureBoundingBox p_74879_2_, Random p_74879_3_, int p_74879_4_, int p_74879_5_, int p_74879_6_, WeightedRandomChestContent[] p_74879_7_, int p_74879_8_)
    {
        int i1 = this.getXWithOffset(p_74879_4_, p_74879_6_);
        int j1 = this.getYWithOffset(p_74879_5_);
        int k1 = this.getZWithOffset(p_74879_4_, p_74879_6_);

        if (p_74879_2_.isVecInside(i1, j1, k1) && p_74879_1_.getBlock(i1, j1, k1) != Blocks.trapped_chest)
        {
            p_74879_1_.setBlock(i1, j1, k1, Blocks.trapped_chest, 0, 2);
            TileEntityChest tileentitychest = (TileEntityChest)p_74879_1_.getTileEntity(i1, j1, k1);

            if (tileentitychest != null)
            {
                WeightedRandomChestContent.generateChestContents(p_74879_3_, p_74879_7_, tileentitychest, p_74879_8_);
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}