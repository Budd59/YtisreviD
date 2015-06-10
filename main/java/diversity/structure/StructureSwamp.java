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

public class StructureSwamp extends StructureTools
{
    public static class SwampHut extends GlobalFeature
    {
        public static final WeightedRandomChestContent[] itemsToGenerateInHut = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.rotten_flesh, 0, 4, 6, 10), new WeightedRandomChestContent(Items.bone, 0, 4, 5, 10), new WeightedRandomChestContent(Items.spider_eye, 0, 3, 7, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 4, 8), new WeightedRandomChestContent(Items.glass_bottle, 0, 2, 6, 20), new WeightedRandomChestContent(Items.potionitem, 0, 0, 2, 16), new WeightedRandomChestContent(Items.fermented_spider_eye, 0, 1, 5, 6), new WeightedRandomChestContent(Items.ender_pearl, 0, 1, 1, 1), new WeightedRandomChestContent(Items.gunpowder, 0, 1, 5, 5)};
        	
        public SwampHut() {}
        
        public SwampHut(Random random, int coordX, int coordZ)
        {
            super(random, coordX, coordZ, 7, 5, 9);
        }

		@Override
		protected boolean build(World world, Random random,	StructureBoundingBox structureBoundingBox) {
            if (!this.func_74935_a(world, structureBoundingBox, 0))
            {
                return false;
            }
            else
            {
                this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 1, 1, 5, 1, 7, Blocks.planks, 1, Blocks.planks, 1, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 4, 2, 5, 4, 7, Blocks.planks, 1, Blocks.planks, 1, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 1, 0, 4, 1, 0, Blocks.planks, 1, Blocks.planks, 1, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 2, 2, 3, 3, 2, Blocks.planks, 1, Blocks.planks, 1, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 2, 3, 1, 3, 6, Blocks.planks, 1, Blocks.planks, 1, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 5, 2, 3, 5, 3, 6, Blocks.planks, 1, Blocks.planks, 1, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 2, 7, 4, 3, 7, Blocks.planks, 1, Blocks.planks, 1, false);
                this.fillWithBlocks(world, structureBoundingBox, 1, 0, 2, 1, 3, 2, Blocks.log, Blocks.log, false);
                this.fillWithBlocks(world, structureBoundingBox, 5, 0, 2, 5, 3, 2, Blocks.log, Blocks.log, false);
                this.fillWithBlocks(world, structureBoundingBox, 1, 0, 7, 1, 3, 7, Blocks.log, Blocks.log, false);
                this.fillWithBlocks(world, structureBoundingBox, 5, 0, 7, 5, 3, 7, Blocks.log, Blocks.log, false);
                this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 2, 3, 2, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 3, 3, 7, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 3, 4, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 3, 4, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 3, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.flower_pot, 7, 1, 3, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 1, 2, 1, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 2, 1, structureBoundingBox);
                int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
                int j = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
                int k = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
                int l = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 4, 1, 6, 4, 1, Blocks.spruce_stairs, i, Blocks.spruce_stairs, i, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 4, 2, 0, 4, 7, Blocks.spruce_stairs, k, Blocks.spruce_stairs, k, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 6, 4, 2, 6, 4, 7, Blocks.spruce_stairs, j, Blocks.spruce_stairs, j, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 0, 4, 8, 6, 4, 8, Blocks.spruce_stairs, l, Blocks.spruce_stairs, l, false);
                int i1;
                int j1;

                for (i1 = 2; i1 <= 7; i1 += 5)
                {
                    for (j1 = 1; j1 <= 5; j1 += 4)
                    {
                        this.func_151554_b(world, Blocks.log, 0, j1, -1, i1, structureBoundingBox);
                    }
                }
                
                this.fillWithMetadataBlocks(world, structureBoundingBox, 1, 5, 2, 5, 5, 7, Blocks.wooden_slab, 1, Blocks.wooden_slab, 1, false);
                this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 5, 3, 4, 5, 6, Blocks.wooden_slab, 9, Blocks.wooden_slab, 9, false);
                this.fillWithAir(world, structureBoundingBox, 2, 4, 3, 4, 4, 6);
                
                this.fillWithMetadataBlocks(world, structureBoundingBox, 2, 2, 3, 4, 2, 6, Blocks.carpet, 15, Blocks.carpet, 15, false);
                this.placeBlockAtCurrentPosition(world, Blocks.crafting_table, 0, 4, 2, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.furnace, this.getMetadataWithOffset(Blocks.furnace, 0x3), 3, 2, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 3, 3, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 3, 4, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 3, 5, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 3, 6, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cauldron, 0, 2, 2, 3, structureBoundingBox);
                
                if (random.nextBoolean()) {
                    this.placeBlockAtCurrentPosition(world, Blocks.web, 0, 2, 4, 3, structureBoundingBox);
                }
                if (random.nextBoolean()) {
                    this.placeBlockAtCurrentPosition(world, Blocks.web, 0, 2, 4, 6, structureBoundingBox);
                    if (random.nextBoolean()) {
                        this.placeBlockAtCurrentPosition(world, Blocks.web, 0, 2, 3, 6, structureBoundingBox);
                    }
                }
                if (random.nextBoolean()) {
                    this.placeBlockAtCurrentPosition(world, Blocks.web, 0, 4, 4, 6, structureBoundingBox);
                    if (random.nextBoolean()) {
                        this.placeBlockAtCurrentPosition(world, Blocks.web, 0, 4, 3, 6, structureBoundingBox);
                    }
                }
                
                ChestGenHooks chest = ChestGenTools.getInfo(ChestGenTools.SWAMPHUT_CHEST);
                this.generateStructureChestContents(world, structureBoundingBox, random, 2, 2, 6, chest.getItems(random), chest.getCount(random));

                this.spawnEntity(world, structureBoundingBox, 2, 2, 5, 0);
                return true;
            }
		}

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityWitch(world);
		}
    }

	@Override
	public GlobalFeature getRandomComponent(Random random, int coordX, int coordZ) {
		return new SwampHut(random, coordX, coordZ);
	}
    
    
}
