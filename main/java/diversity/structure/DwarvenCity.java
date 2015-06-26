package diversity.structure;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.common.ChestGenHooks;
import diversity.utils.ChestGenTools;

public class DwarvenCity extends GlobalFeature
{    	
    public DwarvenCity() {}
    
    public DwarvenCity(Random random, int coordX, int coordZ)
    {
        super(random, coordX, coordZ, 39, 19, 17);
        switch (this.coordBaseMode)
        {
            case 0:
            case 2:
                this.boundingBox = new StructureBoundingBox(coordX - 8, 30, coordZ - 8, coordX + scatteredFeatureSizeX - 1 - 8, 30 + scatteredFeatureSizeY - 1, coordZ + scatteredFeatureSizeZ - 1 - 8);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(coordX - 8, 30, coordZ - 8, coordX + scatteredFeatureSizeZ - 1 - 8, 30 + scatteredFeatureSizeY - 1, coordZ + scatteredFeatureSizeX - 1 - 8);
        }
    }

	@Override
	protected boolean build(World world, Random random,	StructureBoundingBox structureBoundingBox)
	{
		
		// extern wall
		this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 0, 0, 11, 6, 0, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 0, 16, 11, 6, 16, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 0, 5, 0, 6, 11, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 16, 0, 5, 16, 6, 11, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 3, 0, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 3, 0, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 3, 16, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 3, 16, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 0, 3, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 0, 3, 10, structureBoundingBox);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 0, 1, 4, 6, 1, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 0, 15, 4, 6, 15, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 12, 0, 1, 13, 6, 1, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 12, 0, 15, 13, 6, 15, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 0, 3, 1, 6, 4, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 15, 0, 3, 15, 6, 4, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 0, 12, 1, 6, 13, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 15, 0, 12, 15, 6, 13, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 0, 2, 2, 6, 2, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 14, 0, 2, 14, 6, 2, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 0, 14, 2, 6, 14, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 14, 0, 14, 14, 6, 14, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		// entrance
		this.fillWithMetadataBlocks(world, structureBoundingBox, 16, 6, 6, 16, 6, 10, Blocks.air, 0, Blocks.air, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 16, 1, 7, 16, 3, 9, Blocks.air, 0, Blocks.air, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 17, -2, 6, 18, 6, 6, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 17, -2, 10, 18, 6, 10, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 18, 5, 7, 18, 6, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 17, 6, 7, 17, 6, 9, Blocks.stone_slab, 5, Blocks.stone_slab, 5, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 17, 5, 7, 17, 5, 9, Blocks.log, getMetadataWithOffset(Blocks.log, 4), Blocks.log, getMetadataWithOffset(Blocks.log, 4), false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 17, 4, 7, 17, 4, 9, Blocks.iron_bars, 0, Blocks.iron_bars, 0, false);
		
		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 16, 4, 8, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 6), 16, 4, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 7), 16, 4, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 6), 18, 4, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 7), 18, 4, 9, structureBoundingBox);

		// first roof
		this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 5, 2, 14, 5, 14, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 5, 1, 11, 5, 1, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 5, 15, 11, 5, 15, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 5, 5, 1, 5, 11, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 15, 5, 5, 15, 5, 11, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		//torch and protection of the first roof
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 5, 7, 0, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 7, 7, 0, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 9, 7, 0, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 11, 7, 0, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 13, 7, 1, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 15, 7, 3, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 16, 7, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 18, 7, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 18, 7, 8, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 18, 7, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 16, 7, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 15, 7, 13, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 13, 7, 15, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 11, 7, 16, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 9, 7, 16, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 7, 7, 16, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 5, 7, 16, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 3, 7, 15, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 1, 7, 13, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 0, 7, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 0, 7, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 0, 7, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 0, 7, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 1, 7, 3, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 3, 7, 1, structureBoundingBox);

		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 3, 6, 3, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 3, 6, 13, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 13, 6, 3, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 13, 6, 13, structureBoundingBox);

		//intern wall
		this.fillWithMetadataBlocks(world, structureBoundingBox, 6, 6, 2, 10, 11, 2, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 6, 6, 14, 10, 11, 14, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 6, 6, 2, 11, 10, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 14, 6, 6, 14, 11, 10, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		// windows and doors on the main walls
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 7, 8, 2, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 7, 9, 2, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 9, 8, 2, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 9, 9, 2, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 7, 8, 14, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 7, 9, 14, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 9, 8, 14, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 9, 9, 14, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 2, 8, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 2, 9, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 2, 8, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 2, 9, 9, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 14, 9, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 14, 9, 9, structureBoundingBox);
		this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 14, 6, 7, this.getMetadataWithOffset(Blocks.wooden_door, 1));
		this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 14, 6, 9, this.getMetadataWithOffset(Blocks.wooden_door, 1));

		this.fillWithMetadataBlocks(world, structureBoundingBox, 7, 12, 2, 9, 12, 2, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 7, 12, 14, 9, 12, 14, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 12, 7, 2, 12, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 14, 12, 7, 14, 12, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);

		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 0), 6, 12, 2, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 0), 6, 12, 14, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), 10, 12, 2, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), 10, 12, 14, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 3), 2, 12, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 3), 14, 12, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 2), 2, 12, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 2), 14, 12, 10, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 8, 13, 2, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 8, 13, 14, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 2, 13, 8, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 14, 13, 8, structureBoundingBox);

		// little wall of the intern wall
		this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 6, 3, 5, 11, 3, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 6, 13, 5, 11, 13, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 11, 6, 3, 12, 11, 3, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 11, 6, 13, 12, 11, 13, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 6, 4, 3, 11, 5, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 13, 6, 4, 13, 11, 5, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 6, 11, 3, 11, 12, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 13, 6, 11, 13, 11, 12, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		// second roof
		this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 11, 4, 12, 11, 12, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 6, 11, 3, 10, 11, 3, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 6, 11, 13, 10, 11, 13, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 11, 6, 3, 11, 10, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 13, 11, 6, 13, 11, 10, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		//towers wall
		this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 12, 3, 6, 14, 3, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 12, 13, 6, 14, 13, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 10, 12, 3, 12, 14, 3, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 10, 12, 13, 12, 14, 13, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 12, 4, 3, 14, 6, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 13, 12, 4, 13, 14, 6, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 3, 12, 10, 3, 14, 12, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 13, 12, 10, 13, 14, 12, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		// towers windows
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 5, 13, 3, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 5, 13, 13, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 11, 13, 3, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 11, 13, 13, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 3, 13, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 13, 13, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 3, 13, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.cobblestone_wall, 0, 13, 13, 11, structureBoundingBox);
		
		//center wall
		this.fillWithMetadataBlocks(world, structureBoundingBox, 7, 12, 4, 9, 14, 4, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 7, 12, 12, 9, 14, 12, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 4, 12, 7, 4, 14, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 12, 12, 7, 12, 14, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		//center wall door and torches
		this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 8, 12, 4, this.getMetadataWithOffset(Blocks.wooden_door, 1));
		this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 8, 12, 12, this.getMetadataWithOffset(Blocks.wooden_door, 1));
		this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 4, 12, 8, this.getMetadataWithOffset(Blocks.wooden_door, 1));
		this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 12, 12, 8, this.getMetadataWithOffset(Blocks.wooden_door, 1));

		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 8, 12, 3, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 8, 12, 13, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 3, 12, 8, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 13, 12, 8, structureBoundingBox);
		
		// castle roof
		this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 15, 5, 11, 15, 11, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 7, 16, 7, 9, 16, 9, Blocks.stone_slab, 5, Blocks.stone_slab, 5, false);

		// towers
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 15, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 16, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 15, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 16, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 15, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 16, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 5, 16, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 16, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 15, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 5, 15, 4, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 15, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 16, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 15, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 16, 4, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 15, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 16, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 11, 16, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 16, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 15, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 11, 15, 4, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 15, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 16, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 15, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 16, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 15, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 16, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 5, 16, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 16, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4, 15, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 5, 15, 12, structureBoundingBox);
		
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 15, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 16, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 15, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 16, 12, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 15, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 16, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 11, 16, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 10, 16, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 12, 15, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 11, 15, 12, structureBoundingBox);
		
		//tower roof
		for (int k = 0; k < 2; k++) {
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 3), 5+k, 17, 4, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 3), 5+k, 17, 10, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 3), 11+k, 17, 4, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 3), 11+k, 17, 10, structureBoundingBox);
			
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 2), 4+k, 17, 6, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 2), 4+k, 17, 12, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 2), 10+k, 17, 6, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 2), 10+k, 17, 12, structureBoundingBox);
			
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), 6, 17, 5+k, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), 6, 17, 11+k, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), 12, 17, 5+k, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), 12, 17, 11+k, structureBoundingBox);
			
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 0), 4, 17, 4+k, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 0), 4, 17, 10+k, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 0), 10, 17, 4+k, structureBoundingBox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 0), 10, 17, 10+k, structureBoundingBox);
		}
		
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5, 17, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 11, 17, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5, 17, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 11, 17, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.gold_block, 0, 5, 18, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.gold_block, 0, 11, 18, 5, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.gold_block, 0, 5, 18, 11, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.gold_block, 0, 11, 18, 11, structureBoundingBox);

		
		// bridge
		this.func_151554_b(world, Blocks.stonebrick, 0, 17, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 18, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 17, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 18, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 17, 0, 9, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 18, 0, 9, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 19, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 19, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 19, 0, 9, structureBoundingBox);

		this.fillWithMetadataBlocks(world, structureBoundingBox, 20, -5, 7, 20, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 20, -6, 7, 20, -6, 9, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 5), Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 5), false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 21, -3, 7, 21, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 21, -4, 7, 21, -4, 9, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 5), Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 5), false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 22, -2, 7, 22, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 22, -3, 7, 22, -3, 9, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 5), Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 5), false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 23, -2, 7, 23, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 24, -1, 7, 25, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 24, -2, 7, 25, -2, 9, Blocks.stone_slab, 13, Blocks.stone_slab, 13, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 26, -1, 7, 27, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 28, -1, 7, 29, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 28, -2, 7, 29, -2, 9, Blocks.stone_slab, 13, Blocks.stone_slab, 13, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 30, -2, 7, 30, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 31, -2, 7, 31, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 31, -3, 7, 31, -3, 9, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 4), Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 4), false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 32, -3, 7, 32, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 32, -4, 7, 32, -4, 9, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 4), Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 4), false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 33, -5, 7, 33, 0, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 33, -6, 7, 33, -6, 9, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 4), Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 4), false);
				
		this.func_151554_b(world, Blocks.stonebrick, 0, 34, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 35, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 34, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 35, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 34, 0, 9, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 35, 0, 9, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 36, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 36, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 36, 0, 9, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 37, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 37, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 37, 0, 9, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 38, 0, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 38, 0, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 38, 0, 9, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 39, -1, 7, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 39, -1, 8, structureBoundingBox);
		this.func_151554_b(world, Blocks.stonebrick, 0, 39, -1, 9, structureBoundingBox);
		
		for (int k = 0; k <= 15; k++) {
			if (k%3==0) {
				this.placeBlockAtCurrentPosition(world, Blocks.iron_bars, 0, 19+k, -1, 7, structureBoundingBox);
				this.placeBlockAtCurrentPosition(world, Blocks.torch, 5, 19+k, -1, 8, structureBoundingBox);
				this.placeBlockAtCurrentPosition(world, Blocks.iron_bars, 0, 19+k, -1, 9, structureBoundingBox);
			}
		}
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 39, 0, 7, 39, 0, 9, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 1), false);

		
		// entrance little tower
		this.fillWithMetadataBlocks(world, structureBoundingBox, 35, -1, 6, 39, 6, 6, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 35, -1, 10, 39, 6, 10, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 35, 5, 7, 39, 6, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 35, 5, 7, 39, 6, 9, Blocks.stonebrick, 0, Blocks.stonebrick, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 36, 4, 6, 36, 5, 6, Blocks.cobblestone_wall, 0, Blocks.cobblestone_wall, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 36, 4, 10, 36, 5, 10, Blocks.cobblestone_wall, 0, Blocks.cobblestone_wall, 0, false);
		
		this.fillWithMetadataBlocks(world, structureBoundingBox, 38, 4, 6, 38, 5, 6, Blocks.cobblestone_wall, 0, Blocks.cobblestone_wall, 0, false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 38, 4, 10, 38, 5, 10, Blocks.cobblestone_wall, 0, Blocks.cobblestone_wall, 0, false);
		
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 36, 6, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 36, 6, 8, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 36, 6, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 37, 6, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 37, 6, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 38, 6, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 38, 6, 8, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 38, 6, 9, structureBoundingBox);		
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 37, 7, 8, structureBoundingBox);

		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 6), 35, 4, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 6), 36, 4, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 6), 38, 4, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 6), 39, 4, 7, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 7), 35, 4, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 7), 36, 4, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 7), 38, 4, 9, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, getMetadataWithOffset(Blocks.stone_brick_stairs, 7), 39, 4, 9, structureBoundingBox);

		this.fillWithMetadataBlocks(world, structureBoundingBox, 37, 5, 7, 37, 5, 9, Blocks.log, getMetadataWithOffset(Blocks.log, 4), Blocks.log, getMetadataWithOffset(Blocks.log, 4), false);
		this.fillWithMetadataBlocks(world, structureBoundingBox, 37, 4, 7, 37, 4, 9, Blocks.iron_bars, 0, Blocks.iron_bars, 0, false);
		
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 35, 7, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 37, 7, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 39, 7, 6, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 39, 7, 8, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 39, 7, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 37, 7, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 35, 7, 10, structureBoundingBox);
		this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 35, 7, 8, structureBoundingBox);

		return true;
	}

	@Override
	protected EntityLiving getNewEntity(World world, int choice) {
		return new EntityWitch(world);
	}

}