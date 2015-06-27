package diversity.structure;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import diversity.entity.EntityTzitzimime;

public class DwarvenScaffolding extends GlobalFeature
{
	private int randomX;
	private int randomZ;
	
	
    public DwarvenScaffolding() {}
    
    public DwarvenScaffolding(Random random, int coordX, int coordZ)
    {
        super(random, coordX, coordZ, 6, 15 , 6);
        switch (this.coordBaseMode)
        {
            case 0:
            case 2:
                this.boundingBox = new StructureBoundingBox(coordX - 3, 26, coordZ - 3, coordX + scatteredFeatureSizeX - 1 - 3, 26 + scatteredFeatureSizeY - 1, coordZ + scatteredFeatureSizeZ - 1 - 3);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(coordX - 3, 26, coordZ - 3, coordX + scatteredFeatureSizeZ - 1 - 3, 26 + scatteredFeatureSizeY - 1, coordZ + scatteredFeatureSizeX - 1 - 3);
        }
        randomX = random.nextInt(3);
        randomZ = random.nextInt(3);
    }

    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.setInteger("randX", randomX);
        p_143012_1_.setInteger("randZ", randomZ);
    }

    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        super.func_143011_b(p_143011_1_);
        randomX = p_143011_1_.getInteger("randX");
        randomZ = p_143011_1_.getInteger("randZ");
    }

    @Override
	public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
	{
		this.func_151554_b(world, Blocks.fence, 0, 0, 5, 0, structureBoundingBox);
		this.func_151554_b(world, Blocks.fence, 0, 4, 5, 0, structureBoundingBox);
		this.func_151554_b(world, Blocks.fence, 0, 0, 5, 4, structureBoundingBox);
		this.func_151554_b(world, Blocks.fence, 0, 4, 5, 4, structureBoundingBox);

		this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 6, 0, 4, 6, 4, Blocks.wooden_slab, 0, Blocks.wooden_slab, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, randomX, 11, randomZ, 2+randomX, 11, 2+randomZ, Blocks.wooden_slab, 0, Blocks.wooden_slab, 0, false);
		
		this.func_151554_b(world, Blocks.fence, 0, randomX, 10, randomZ, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, randomX, 6, randomZ, structureBoundingBox);
		this.func_151554_b(world, Blocks.fence, 0, 2+randomX, 10, randomZ, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, 2+randomX, 6, randomZ, structureBoundingBox);
		this.func_151554_b(world, Blocks.fence, 0, randomX, 10, 2+randomZ, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, randomX, 6, 2+randomZ, structureBoundingBox);
		this.func_151554_b(world, Blocks.fence, 0, 2+randomX, 10, 2+randomZ, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, 2+randomX, 6, 2+randomZ, structureBoundingBox);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 1+randomX, 5, randomZ, 1+randomX, 12, randomZ, Blocks.planks, 0, Blocks.planks, 0, false);
		this.func_151554_b(world, Blocks.planks, 0, 1+randomX, 4, randomZ, structureBoundingBox);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 1+randomX, 5, 1+randomZ, 1+randomX, 12, 1+randomZ, Blocks.ladder, getMetadataWithOffset(Blocks.ladder, 2), Blocks.ladder, getMetadataWithOffset(Blocks.ladder, 2), false);
		this.func_151554_b(world, Blocks.ladder, getMetadataWithOffset(Blocks.ladder, 2), 1+randomX, 4, 1+randomZ, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1+randomX, 13, randomZ, structureBoundingBox);
		
    	return true;
    }

	@Override
	protected EntityLiving getNewEntity(World world, int choice) {
		return new EntityTzitzimime(world);
	}
}
