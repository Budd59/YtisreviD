package diversity;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureStart;
import diversity.configurations.ConfigStructureRate;
import diversity.configurations.ConfigVillageRate;
import diversity.structure.StructureTools;
import diversity.structure.StructureTools.GlobalFeature;
import diversity.suppliers.EnumStructure;

public class MapGenStructureDiversity extends MapGenScatteredFeature
{
	/** the maximum distance between scattered features */
    private int maxDistanceBetweenScatteredFeatures;
    /** the minimum distance between scattered features */
    private int minDistanceBetweenScatteredFeatures;

    public MapGenStructureDiversity()
    {
        this.maxDistanceBetweenScatteredFeatures = ConfigStructureRate.maxDistanceBetweenStructures.getIntegerConfig();
        this.minDistanceBetweenScatteredFeatures = ConfigStructureRate.minDistanceBetweenStructures.getIntegerConfig();
    }

    public MapGenStructureDiversity(Map p_i2061_1_)
    {
        this();
        Iterator iterator = p_i2061_1_.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
            }
        }
    }

    @Override
    public String func_143025_a()
    {
        return Diversity.NAME + ".Structure";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int x, int z)
    {
        int coordX = x;
        int coordZ = z;

        if (x < 0)
        {
            x -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (z < 0)
        {
            z -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int x1 = x / this.maxDistanceBetweenScatteredFeatures;
        int z1 = z / this.maxDistanceBetweenScatteredFeatures;
        Random random = this.worldObj.setRandomSeed(x1, z1, 14357617);
        x1 *= this.maxDistanceBetweenScatteredFeatures;
        z1 *= this.maxDistanceBetweenScatteredFeatures;
        x1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        z1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

        if (coordX == x1 && coordZ == z1)
        {
    	    BiomeGenBase biome = worldObj.getWorldChunkManager().getBiomeGenAt(coordX * 16 + 8, coordZ * 16 + 8);
    	    return EnumStructure.canSpawnInBiome(biome);
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int coordX, int coordZ)
    {
        return new MapGenStructureDiversity.Start(this.worldObj, this.rand, coordX, coordZ);
    }

    @Override
    public boolean func_143030_a(int p_143030_1_, int p_143030_2_, int p_143030_3_)
    {
    	return false;
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random random, int coordX, int coordZ)
        {
            super(coordX, coordZ);
            BiomeGenBase biome = world.getBiomeGenForCoords(coordX * 16 + 8, coordZ * 16 + 8);
            StructureTools structure = EnumStructure.getRandomStructure(biome, random);
            if (structure != null)
            {
            	GlobalFeature feature = structure.getRandomComponent(random, coordX * 16, coordZ * 16);
            	if (feature != null) {
            			this.components.add(feature);
            	}
            }
            this.updateBoundingBox();
        }
    }
}
