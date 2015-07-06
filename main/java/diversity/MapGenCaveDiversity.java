package diversity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.logging.Level;

import com.google.common.collect.Table;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import diversity.configurations.ConfigGenerationRate;
import diversity.structure.DwarvenCave;
import diversity.structure.GlobalFeature;
import diversity.suppliers.EnumCave;
import diversity.suppliers.EnumStructure;
import diversity.utils.Point;

public class MapGenCaveDiversity extends MapGenScatteredFeature
{
	static Map<Class, Class> caveStructureMap = new HashMap<Class, Class>();
	
	/** the maximum distance between scattered features */
    private int maxDistanceBetweenScatteredFeatures;
    /** the minimum distance between scattered features */
    private int minDistanceBetweenScatteredFeatures;

    public MapGenCaveDiversity()
    {
        this.maxDistanceBetweenScatteredFeatures = ConfigGenerationRate.MAXDISTANCEBETWEENCAVES.getIntegerConfig();
        this.minDistanceBetweenScatteredFeatures = ConfigGenerationRate.MINDISTANCEBETWEENCAVES.getIntegerConfig();
    }

    public MapGenCaveDiversity(Map p_i2061_1_)
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
        return Diversity.NAME + ".CaveStructure";
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
        Random random = this.worldObj.setRandomSeed(x1, z1, 9707617);
        x1 *= this.maxDistanceBetweenScatteredFeatures;
        z1 *= this.maxDistanceBetweenScatteredFeatures;
        x1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        z1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

        if (coordX == x1 && coordZ == z1)
        {
    	    BiomeGenBase biome = worldObj.getWorldChunkManager().getBiomeGenAt(coordX * 16 + 8, coordZ * 16 + 8);
    	    return EnumCave.canSpawnInBiome(biome);
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int coordX, int coordZ)
    {
        return new MapGenCaveDiversity.Start(this.worldObj, this.rand, coordX, coordZ);
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
            EnumCave cave = EnumCave.getRandomCave(biome, random);
        	GlobalFeature feature = cave.getCaveComponent(random, coordX * 16, coordZ * 16);
        	if (feature != null) {
        		this.components.add(feature);

        		GlobalFeature structureFeature = cave.getStructureComponent(0, random, coordX*16, coordZ*16);
        		if (structureFeature != null) {
        			this.components.add(structureFeature);
        		}
        		
    			if (feature instanceof DwarvenCave) {
    				for (Point point : ((DwarvenCave)feature).scaffoldingPoint) {
    					structureFeature = cave.getStructureComponent(1, random, point.x, point.z);
    	        		if (feature != null) {
    	            		this.components.add(structureFeature);
    	        		}
    				}
    				
    			}
        	}
            this.updateBoundingBox();
        }
    }
}
