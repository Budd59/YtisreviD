package diversity.structure;

import static net.minecraftforge.common.ChestGenHooks.PYRAMID_JUNGLE_CHEST;
import static net.minecraftforge.common.ChestGenHooks.PYRAMID_JUNGLE_DISPENSER;

import java.util.Random;

import net.minecraft.block.BlockLever;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import diversity.entity.EntityTzitzimime;
import diversity.utils.DirectionTools;

public class StructureJungle extends StructureTools
{
	public static class Pyramid extends GlobalFeature
	{
        private boolean field_74947_h;
        private boolean field_74948_i;
        private boolean field_74945_j;
        private boolean field_74946_k;
        		
        private static Pyramid.Stones junglePyramidsRandomScatteredStones = new Pyramid.Stones(null);

        public Pyramid() {}
        
        public Pyramid(Random random, int coordX, int coordZ)
        {
            super(random, coordX, coordZ, 21, 15 , 21);
        }
        
        protected void func_143012_a(NBTTagCompound p_143012_1_)
        {
            super.func_143012_a(p_143012_1_);
            p_143012_1_.setBoolean("placedMainChest", this.field_74947_h);
            p_143012_1_.setBoolean("placedHiddenChest", this.field_74948_i);
            p_143012_1_.setBoolean("placedTrap1", this.field_74945_j);
            p_143012_1_.setBoolean("placedTrap2", this.field_74946_k);
        }

        protected void func_143011_b(NBTTagCompound p_143011_1_)
        {
            super.func_143011_b(p_143011_1_);
            this.field_74947_h = p_143011_1_.getBoolean("placedMainChest");
            this.field_74948_i = p_143011_1_.getBoolean("placedHiddenChest");
            this.field_74945_j = p_143011_1_.getBoolean("placedTrap1");
            this.field_74946_k = p_143011_1_.getBoolean("placedTrap2");
        }
        
        
        @Override
		public boolean build(World world, Random random, StructureBoundingBox structureBoundingBox)
		{
            if (!this.func_74935_a(world, structureBoundingBox, 0))
            {
                return false;
            }
            else
            {
                int i = this.getMetadataWithOffset(Blocks.stone_stairs, 3);
                int j = this.getMetadataWithOffset(Blocks.stone_stairs, 2);
                int k = this.getMetadataWithOffset(Blocks.stone_stairs, 0);
                int l = this.getMetadataWithOffset(Blocks.stone_stairs, 1);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 1, 2, 9, 2, 2, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 1, 12, 9, 2, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 1, 3, 2, 2, 11, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 9, 1, 3, 9, 2, 11, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 3, 1, 10, 6, 1, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 3, 13, 10, 6, 13, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 3, 2, 1, 6, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 10, 3, 2, 10, 6, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 3, 2, 9, 3, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 6, 2, 9, 6, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 3, 7, 3, 8, 7, 11, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 8, 4, 7, 8, 10, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithAir(world, structureBoundingBox, 3, 1, 3, 8, 2, 11);
                this.fillWithAir(world, structureBoundingBox, 4, 3, 6, 7, 3, 9);
                this.fillWithAir(world, structureBoundingBox, 2, 4, 2, 9, 5, 12);
                this.fillWithAir(world, structureBoundingBox, 4, 6, 5, 7, 6, 9);
                this.fillWithAir(world, structureBoundingBox, 5, 7, 6, 6, 7, 8);
                this.fillWithAir(world, structureBoundingBox, 5, 1, 2, 6, 2, 2);
                this.fillWithAir(world, structureBoundingBox, 5, 2, 12, 6, 2, 12);
                this.fillWithAir(world, structureBoundingBox, 5, 5, 1, 6, 5, 1);
                this.fillWithAir(world, structureBoundingBox, 5, 5, 13, 6, 5, 13);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 5, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 10, 5, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 5, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 10, 5, 9, structureBoundingBox);
                int i1;

                for (i1 = 0; i1 <= 14; i1 += 14)
                {
                    this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 4, i1, 2, 5, i1, false, random, junglePyramidsRandomScatteredStones);
                    this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 4, i1, 4, 5, i1, false, random, junglePyramidsRandomScatteredStones);
                    this.fillWithRandomizedBlocks(world, structureBoundingBox, 7, 4, i1, 7, 5, i1, false, random, junglePyramidsRandomScatteredStones);
                    this.fillWithRandomizedBlocks(world, structureBoundingBox, 9, 4, i1, 9, 5, i1, false, random, junglePyramidsRandomScatteredStones);
                }

                this.fillWithRandomizedBlocks(world, structureBoundingBox, 5, 6, 0, 6, 6, 0, false, random, junglePyramidsRandomScatteredStones);

                for (i1 = 0; i1 <= 11; i1 += 11)
                {
                    for (int j1 = 2; j1 <= 12; j1 += 2)
                    {
                        this.fillWithRandomizedBlocks(world, structureBoundingBox, i1, 4, j1, i1, 5, j1, false, random, junglePyramidsRandomScatteredStones);
                    }

                    this.fillWithRandomizedBlocks(world, structureBoundingBox, i1, 6, 5, i1, 6, 5, false, random, junglePyramidsRandomScatteredStones);
                    this.fillWithRandomizedBlocks(world, structureBoundingBox, i1, 6, 9, i1, 6, 9, false, random, junglePyramidsRandomScatteredStones);
                }

                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 7, 2, 2, 9, 2, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 9, 7, 2, 9, 9, 2, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, 7, 12, 2, 9, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 9, 7, 12, 9, 9, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 9, 4, 4, 9, 4, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 7, 9, 4, 7, 9, 4, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 9, 10, 4, 9, 10, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 7, 9, 10, 7, 9, 10, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 5, 9, 7, 6, 9, 7, false, random, junglePyramidsRandomScatteredStones);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 5, 9, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 6, 9, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, j, 5, 9, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, j, 6, 9, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 4, 0, 0, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 5, 0, 0, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 6, 0, 0, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 7, 0, 0, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 4, 1, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 4, 2, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 4, 3, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 7, 1, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 7, 2, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, i, 7, 3, 10, structureBoundingBox);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 9, 4, 1, 9, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 7, 1, 9, 7, 1, 9, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 10, 7, 2, 10, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 5, 4, 5, 6, 4, 5, false, random, junglePyramidsRandomScatteredStones);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, k, 4, 4, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, l, 7, 4, 5, structureBoundingBox);

                for (i1 = 0; i1 < 4; ++i1)
                {
                    this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, j, 5, 0 - i1, 6 + i1, structureBoundingBox);
                    this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, j, 6, 0 - i1, 6 + i1, structureBoundingBox);
                    this.fillWithAir(world, structureBoundingBox, 5, 0 - i1, 7 + i1, 6, 0 - i1, 9 + i1);
                }

                this.fillWithAir(world, structureBoundingBox, 1, -3, 12, 10, -1, 13);
                this.fillWithAir(world, structureBoundingBox, 1, -3, 1, 3, -1, 13);
                this.fillWithAir(world, structureBoundingBox, 1, -3, 1, 9, -1, 5);

                for (i1 = 1; i1 <= 13; i1 += 2)
                {
                    this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, -3, i1, 1, -2, i1, false, random, junglePyramidsRandomScatteredStones);
                }

                for (i1 = 2; i1 <= 12; i1 += 2)
                {
                    this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, -1, i1, 3, -1, i1, false, random, junglePyramidsRandomScatteredStones);
                }

                this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, -2, 1, 5, -2, 1, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 7, -2, 1, 9, -2, 1, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 6, -3, 1, 6, -3, 1, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 6, -1, 1, 6, -1, 1, false, random, junglePyramidsRandomScatteredStones);
                
                this.fillWithBlocks(world, structureBoundingBox, -2, -9, 5, 4, -5, 9, Blocks.cobblestone, Blocks.cobblestone, false);
                this.fillWithAir(world, structureBoundingBox, 2, -8, 6, 3, -4, 8);
                
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 0, -4, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 5), 0, -5, 7, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, -5, 7, structureBoundingBox);
                
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 5, -4, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 4), 5, -5, 7, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, -5, 7, structureBoundingBox);

                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 3, -5, 7, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 2, -5, 7, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.torch, DirectionTools.torch[coordBaseMode][2], 2, -5, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.torch, DirectionTools.torch[coordBaseMode][3], 2, -5, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.torch, DirectionTools.torch[coordBaseMode][2], 3, -5, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.torch, DirectionTools.torch[coordBaseMode][3], 3, -5, 6, structureBoundingBox);
                
                this.fillWithBlocks(world, structureBoundingBox, 2, -4, 6, 3, -4, 8, Blocks.gravel, Blocks.gravel, false);
                this.fillWithBlocks(world, structureBoundingBox, 1, -8, 5, 4, -8, 9, Blocks.gravel, Blocks.gravel, false);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, -7, 4, 5, -6, 10, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithAir(world, structureBoundingBox, 1, -7, 5, 4, -6, 9);
                this.fillWithAir(world, structureBoundingBox, 2, -8, 6, 3, -8, 8);
                
                this.spawnEntity(world, structureBoundingBox, 3, -6, 5, 0);
                this.spawnEntity(world, structureBoundingBox, 2, -6, 5, 0);
                
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 3) | 4, 1, -3, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 1) | 4, 4, -3, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire, 4, 2, -3, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire, 4, 3, -3, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 3, -3, 1, structureBoundingBox);

                ChestGenHooks dispenser = ChestGenHooks.getInfo(PYRAMID_JUNGLE_DISPENSER);
                ChestGenHooks chest = ChestGenHooks.getInfo(PYRAMID_JUNGLE_CHEST);

                if (!this.field_74945_j)
                {
                    this.field_74945_j = this.generateStructureDispenserContents(world, structureBoundingBox, random, 3, -2, 1, 2, dispenser.getItems(random), dispenser.getCount(random));
                }

                this.placeBlockAtCurrentPosition(world, Blocks.vine, 15, 3, -2, 2, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 2) | 4, 7, -3, 1, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 0) | 4, 7, -3, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire, 4, 7, -3, 2, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire, 4, 7, -3, 3, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.tripwire, 4, 7, -3, 4, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 8, -3, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, -3, 6, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, -3, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 9, -3, 4, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, -2, 4, structureBoundingBox);

                if (!this.field_74946_k)
                {
                    this.field_74946_k = this.generateStructureDispenserContents(world, structureBoundingBox, random, 9, -2, 3, 4, dispenser.getItems(random), dispenser.getCount(random));
                }

                this.placeBlockAtCurrentPosition(world, Blocks.vine, 15, 8, -1, 3, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.vine, 15, 8, -2, 3, structureBoundingBox);

                if (!this.field_74947_h)
                {
                    this.field_74947_h = this.generateStructureChestContents(world, structureBoundingBox, random, 8, -3, 3, chest.getItems(random), chest.getCount(random));
                }

                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 9, -3, 2, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, -3, 1, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 4, -3, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 5, -2, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 5, -1, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 6, -3, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 7, -2, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 7, -1, 5, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, -3, 5, structureBoundingBox);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 9, -1, 1, 9, -1, 5, false, random, junglePyramidsRandomScatteredStones);
                
                this.fillWithAir(world, structureBoundingBox, 8, -2, 8, 10, 0, 10);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 10, 1, 11, 10, 2, 12, false, random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 8, 1, 13, 9, 2, 13, false, random, junglePyramidsRandomScatteredStones);

                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, -2, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 9, -2, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 10, -2, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 10, -1, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 10, -1, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 9, -1, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 9, -1, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, 0, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, 0, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, 0, 10, structureBoundingBox); 
                
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 8, -2, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, -2, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 10, -2, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 8, -1, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, -1, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 10, 0, 8, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 10, 0, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 10, 0, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 10, -1, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, 0, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, 0, 10, structureBoundingBox);

                boolean hasTorched = false;
                if (random.nextBoolean()) {
                    this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, DirectionTools.torch[coordBaseMode][3], 8, -2, 10, structureBoundingBox);
                    hasTorched = true;
                } else {
                    this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 8, -2, 10, structureBoundingBox);
                }
                if (random.nextBoolean()) {
                    this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, DirectionTools.torch[coordBaseMode][3], 9, -2, 10, structureBoundingBox);
                    hasTorched = true;
                } else {
                    this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, -2, 10, structureBoundingBox);
                }
                if (random.nextBoolean()) {
                    this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, DirectionTools.torch[coordBaseMode][3], 10, -2, 10, structureBoundingBox);  
                    hasTorched = true;
                } else {
                    this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 10, -2, 10, structureBoundingBox);
                }
                
                if (!hasTorched) {
                	this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, DirectionTools.torch[coordBaseMode][3], 8+random.nextInt(3), -2, 10, structureBoundingBox);
                }
                
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, 5, 9, 1, 11, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, DirectionTools.torch[coordBaseMode][2], 9, 2, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 3), 8, 2, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 3), 8, 1, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, 2, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 8, 1, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 7, 2, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 7, 1, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 6, 2, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 5, 2, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 2, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 4, 1, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 3, 2, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.mossy_cobblestone, 0, 3, 1, 10, structureBoundingBox);
                
                this.spawnEntity(world, structureBoundingBox, 4, 1, 11, 0);
                this.spawnEntity(world, structureBoundingBox, 4, 1, 11, 0);
 
                this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 8, -2, 11, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 9, -2, 11, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 3, 10, -2, 11, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.lever, BlockLever.invertMetadata(this.getMetadataWithOffset(Blocks.lever, 2)), 8, -2, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.lever, BlockLever.invertMetadata(this.getMetadataWithOffset(Blocks.lever, 2)), 9, -2, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.lever, BlockLever.invertMetadata(this.getMetadataWithOffset(Blocks.lever, 2)), 10, -2, 12, structureBoundingBox);

                if (!this.field_74948_i)
                {
                    this.field_74948_i = this.generateStructureChestContents(world, structureBoundingBox, random, 3, 1, 11, chest.getItems(random), chest.getCount(random));
                }
                
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 11, -2, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 11, -3, 9, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 11, -3, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 11, -4, 10, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 11, -4, 11, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 11, -4, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 11, -4, 13, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 11, -4, 14, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, 5, 11, -3, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 10, -3, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 10, -2, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 9, -2, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 8, -2, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 7, -2, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, DirectionTools.torch[coordBaseMode][0], 5, -2, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 3), 4, -2, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 3), 4, -3, 15, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 2, 4, -2, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 2, 4, -3, 12, structureBoundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 2, 4, -1, 13, structureBoundingBox);

                this.spawnEntity(world, structureBoundingBox, 7, 4, 3, 0);
                this.spawnEntity(world, structureBoundingBox, 7, 4, 3, 0);

                return true;
            }
        }

        static class Stones extends StructureComponent.BlockSelector
        {
            private Stones() {}

            /**
             * picks Block Ids and Metadata (Silverfish)
             */
            public void selectBlocks(Random p_75062_1_, int p_75062_2_, int p_75062_3_, int p_75062_4_, boolean p_75062_5_)
            {
                if (p_75062_1_.nextFloat() < 0.4F)
                {
                    this.field_151562_a = Blocks.cobblestone;
                }
                else
                {
                    this.field_151562_a = Blocks.mossy_cobblestone;
                }
            }

            Stones(Object p_i2063_1_)
            {
                this();
            }
        }

		@Override
		protected EntityLiving getNewEntity(World world, int choice) {
			return new EntityTzitzimime(world);
		}
	}

	@Override
	public GlobalFeature getRandomComponent(Random random, int coordX, int coordZ) {
		return new Pyramid(random, coordX, coordZ);
	}
}
