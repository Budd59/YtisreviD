package diversity;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import diversity.configurations.ConfigGenerationRate;
import diversity.configurations.ConfigGlobal;
import diversity.structure.GlobalFeature;
import diversity.suppliers.EnumStructure;

public class MapGenStructureDiversity extends MapGenScatteredFeature
{
    public MapGenStructureDiversity()
    {
    }

    public MapGenStructureDiversity(Map p_i2061_1_)
    {
        this();
//        Iterator iterator = p_i2061_1_.entrySet().iterator();
//
//        while (iterator.hasNext())
//        {
//            Entry entry = (Entry)iterator.next();
//
//            if (((String)entry.getKey()).equals("distance"))
//            {
//                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
//            }
//        }
    }

    @Override
    public String func_143025_a()
    {
        return Diversity.NAME + ".Structure";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int x, int z)
    {
        int maxDistanceBetweenScatteredFeatures = ConfigGenerationRate.MAXDISTANCEBETWEENSTRUCTURES.getIntegerConfig();
        int minDistanceBetweenScatteredFeatures = ConfigGenerationRate.MINDISTANCEBETWEENSTRUCTURES.getIntegerConfig();
    	
    	int coordX = x;
        int coordZ = z;

        if (x < 0)
        {
            x -= maxDistanceBetweenScatteredFeatures - 1;
        }

        if (z < 0)
        {
            z -= maxDistanceBetweenScatteredFeatures - 1;
        }

        int x1 = x / maxDistanceBetweenScatteredFeatures;
        int z1 = z / maxDistanceBetweenScatteredFeatures;
        Random random = this.worldObj.setRandomSeed(x1, z1, 14357617);
        x1 *= maxDistanceBetweenScatteredFeatures;
        z1 *= maxDistanceBetweenScatteredFeatures;
        x1 += random.nextInt(maxDistanceBetweenScatteredFeatures - minDistanceBetweenScatteredFeatures);
        z1 += random.nextInt(maxDistanceBetweenScatteredFeatures - minDistanceBetweenScatteredFeatures);

        if (coordX == x1 && coordZ == z1)
        {
    	    BiomeGenBase biome = this.worldObj.getWorldChunkManager().getBiomeGenAt(coordX * 16 + 8, coordZ * 16 + 8);
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
            EnumStructure structure = EnumStructure.getRandomStructure(biome, random);
        	GlobalFeature feature = structure.getStructureComponent(random, coordX * 16, coordZ * 16);
        	if (feature != null) {
        		this.components.add(feature);
        	}
            this.updateBoundingBox();
        }
    }
    
    @Override
    public void func_151539_a(IChunkProvider chunkProvider, World world, int chunkX, int chunkZ, Block[] blocks)
    {
    	if (chunkProvider.getClass().equals(ChunkProviderGenerate.class)) {
	    	if (ConfigGlobal.CAN_SPAWN_MOD_CAVES.getBooleanConfig()) {
	    		Diversity.proxy.handler.mapGenCaveStructureDiversity.func_151539_a(chunkProvider, world, chunkX, chunkZ, blocks);
	    	}
	    	if (ConfigGlobal.CAN_SPAWN_MOD_STRUCTURES.getBooleanConfig()) {
	        	super.func_151539_a(chunkProvider, world, chunkX, chunkZ, blocks);
	    	}
    	}
    	if (ConfigGlobal.CAN_SPAWN_VANILLA_STRUCTURES.getBooleanConfig()) {
    		Diversity.proxy.handler.mapGenScatteredFeature.func_151539_a(chunkProvider, world, chunkX, chunkZ, blocks);
    	}
    }
    
    
    /**
     * Generates structures in specified chunk next to existing structures. Does *not* generate StructureStarts.
     */
    @Override
    public boolean generateStructuresInChunk(World world, Random random, int p_75051_3_, int p_75051_4_)
    {
    	boolean bool1 = true;
    	boolean bool2 = true;
    	boolean bool3 = true;
    	if (ConfigGlobal.CAN_SPAWN_MOD_CAVES.getBooleanConfig()) {
    		bool1 = Diversity.proxy.handler.mapGenCaveStructureDiversity.generateStructuresInChunk(world, random, p_75051_3_, p_75051_4_);
    	}
    	if (ConfigGlobal.CAN_SPAWN_MOD_STRUCTURES.getBooleanConfig()) {
    		bool2 = super.generateStructuresInChunk(world, random, p_75051_3_, p_75051_4_);
    	}
    	if (ConfigGlobal.CAN_SPAWN_VANILLA_STRUCTURES.getBooleanConfig()) {
    		bool3 = Diversity.proxy.handler.mapGenScatteredFeature.generateStructuresInChunk(world, random, p_75051_3_, p_75051_4_);
    	}
        return bool1 && bool2 && bool3;
    }
    
}
