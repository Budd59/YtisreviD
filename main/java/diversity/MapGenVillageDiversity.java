package diversity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import diversity.configurations.ConfigGenerationRate;
import diversity.suppliers.EnumVillage;
import diversity.village.AGlobalStructureVillage;
import diversity.village.AGlobalStructureVillage.AGlobalRoad;
import diversity.village.AGlobalStructureVillage.AGlobalStart;

public class MapGenVillageDiversity extends MapGenVillage
{
    /** World terrain type, 0 for normal, 1 for flat map */
    private int terrainType;
	
    public MapGenVillageDiversity()
    {
    }

    public MapGenVillageDiversity(Map p_i2093_1_)
    {
        this();
//        Iterator iterator = p_i2093_1_.entrySet().iterator();
//
//        while (iterator.hasNext())
//        {
//            Entry entry = (Entry)iterator.next();
//
//            if (((String)entry.getKey()).equals("size"))
//            {
//                this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.terrainType, 0);
//            }
//            else if (((String)entry.getKey()).equals("distance"))
//            {
//                this.maxDistanceBetweenVillages = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistanceBetweenVillages, this.minDistanceBetweenVillages + 1);
//            }
//        }
    }

    @Override
    public String func_143025_a()
    {
        return Diversity.NAME + ".Village";
    }

    @Override
    protected StructureStart getStructureStart(int coordX, int coordZ)
    {
        /** World terrain type, 0 for normal, 1 for flat map */
        return new Start(this.worldObj, this.rand, coordX, coordZ, terrainType);
    }
    
    @Override
    protected boolean canSpawnStructureAtCoords(int x, int z)
    {
    	int maxDistanceBetweenVillages = ConfigGenerationRate.MAXDISTANCEBETWEENVILLAGES.getIntegerConfig();
    	int minDistanceBetweenVillages = ConfigGenerationRate.MINDISTANCEBETWEENVILLAGES.getIntegerConfig();
        int coordX = x;
        int coordZ = z;

        if (x < 0)
        {
        	x -= maxDistanceBetweenVillages - 1;
        }

        if (z < 0)
        {
        	z -= maxDistanceBetweenVillages - 1;
        }

        int i1 = x / maxDistanceBetweenVillages;
        int j1 = z / maxDistanceBetweenVillages;
        Random random = this.worldObj.setRandomSeed(i1, j1, 10387312);
        i1 *= maxDistanceBetweenVillages;
        j1 *= maxDistanceBetweenVillages;
        i1 += random.nextInt(maxDistanceBetweenVillages - minDistanceBetweenVillages);
        j1 += random.nextInt(maxDistanceBetweenVillages - minDistanceBetweenVillages);

        if (coordX == i1 && coordZ == j1)
        {
            boolean flag = this.worldObj.getWorldChunkManager().areBiomesViable(coordX * 16 + 8, coordZ * 16 + 8, 0, villageSpawnBiomes);

            if (flag)
            {
            	BiomeGenBase biome = this.worldObj.getWorldChunkManager().getBiomeGenAt(x * 16 + 8, z * 16 + 8);
    	        return EnumVillage.canSpawnInBiome(biome);
            }
        }

        return false;
    }

    public static class Start extends StructureStart
    {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;
        
        public Start() {}

        public Start(World world, Random random, int coordX, int coordZ, int terrainType)
        {
            super(coordX, coordZ);
            BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(coordX * 16 + 8, coordZ * 16 + 8);
            AGlobalStructureVillage villageInstance = EnumVillage.getRandomVillage(biome, random);
            if (villageInstance == null) {
            	this.hasMoreThanTwoComponents = false;
            	this.updateBoundingBox();
            	return;
            }
            List list = villageInstance.getStructureVillageWeightedPieceList(random);
            AGlobalStart start = villageInstance.getStart(world.getWorldChunkManager(), 0, random, (coordX << 4) + 2, (coordZ << 4) + 2, list, terrainType);
            this.components.add(start);
            start.buildComponent(start, this.components, random);
            List basicComponents = start.field_74930_j;
            List pieceComponents = start.field_74932_i;

            int l;

            while (!basicComponents.isEmpty() || !pieceComponents.isEmpty())
            {
                StructureComponent structurecomponent;

                if (basicComponents.isEmpty())
                {
                    l = random.nextInt(pieceComponents.size());
                    structurecomponent = (StructureComponent)pieceComponents.remove(l);
                    structurecomponent.buildComponent(start, this.components, random);
                }
                else
                {
                    l = random.nextInt(basicComponents.size());
                    structurecomponent = (StructureComponent)basicComponents.remove(l);
                    structurecomponent.buildComponent(start, this.components, random);
                }
            }

            l = 0;
            Iterator iterator = this.components.iterator();

            while (iterator.hasNext())
            {
            	StructureComponent structurecomponent1 = (StructureComponent)iterator.next();

                if (!(structurecomponent1 instanceof AGlobalRoad))
                {
                    ++l;
                }
            }

            this.hasMoreThanTwoComponents = l > 2;
            
            this.updateBoundingBox();
        }

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        @Override
        public boolean isSizeableStructure()
        {
            return this.hasMoreThanTwoComponents;
        }

        @Override
        public void func_143022_a(NBTTagCompound p_143022_1_)
        {
            super.func_143022_a(p_143022_1_);
            p_143022_1_.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        @Override
        public void func_143017_b(NBTTagCompound p_143017_1_)
        {
            super.func_143017_b(p_143017_1_);
            this.hasMoreThanTwoComponents = p_143017_1_.getBoolean("Valid");
        }
    }
}