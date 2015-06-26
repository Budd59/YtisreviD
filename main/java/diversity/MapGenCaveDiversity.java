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
import diversity.structure.GlobalFeature;
import diversity.suppliers.EnumCave;
import diversity.suppliers.EnumStructure;
import diversity.suppliers.EnumVillage;
import diversity.utils.EnumCubeType;
import diversity.village.VillageDwarf;
import diversity.village.VillageTools;
import diversity.village.VillageTools.GlobalStart;

public class MapGenCaveDiversity extends MapGenScatteredFeature
{
	Map<Integer, Integer> caveStructureMap = new HashMap<Integer, Integer>();
	
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
        Random random = this.worldObj.setRandomSeed(x1, z1, 14357617);
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
        	GlobalFeature feature = EnumCave.getRandomComponent(biome, random, coordX * 16, coordZ * 16);
        	if (feature != null) {
        		Diversity.Divlogger.log(Level.INFO, coordX*16 + " " + coordZ*16);

        		this.components.add(feature);
        		
//        		LinkedList structureComponents = new LinkedList();
//        		VillageTools villageInstance = new VillageDwarf(EnumVillage.AZTEC);
//                List list = villageInstance.getStructureVillageWeightedPieceList(random);
//                GlobalStart start = villageInstance.getStart(world.getWorldChunkManager(), 0, random, (coordX << 4) + 2, (coordZ << 4) + 2, list, 1);
//                structureComponents.add(start);
//                start.buildComponent(start, structureComponents, random);
//                List basicComponents = start.field_74930_j;
//                List pieceComponents = start.field_74932_i;
//
//                int l;
//
//                while (!basicComponents.isEmpty() || !pieceComponents.isEmpty())
//                {
//                    StructureComponent structurecomponent;
//
//                    if (basicComponents.isEmpty())
//                    {
//                        l = random.nextInt(pieceComponents.size());
//                        structurecomponent = (StructureComponent)pieceComponents.remove(l);
//                        structurecomponent.buildComponent(start, structureComponents, random);
//                    }
//                    else
//                    {
//                        l = random.nextInt(basicComponents.size());
//                        structurecomponent = (StructureComponent)basicComponents.remove(l);
//                        structurecomponent.buildComponent(start, structureComponents, random);
//                    }
//                }
//
//                l = 0;
//                                
//                this.components.addAll(structureComponents);
//                Iterator iterator = this.components.iterator();
//
//                while (iterator.hasNext())
//                {
//                	StructureComponent structurecomponent1 = (StructureComponent)iterator.next();
//
//                    if (!(structurecomponent1 instanceof StructureVillagePieces.Road))
//                    {
//                        ++l;
//                    }
//                }

                
        	}
            this.updateBoundingBox();
        }
    }

//    
//    @Override
//    public void func_151539_a(IChunkProvider chunkProvider, World world, int chunkX, int chunkZ, Block[] blocks)
//    {
//        int range = this.range;
//        this.worldObj = world;
//        this.rand.setSeed(world.getSeed());
//        long l = this.rand.nextLong();
//        long i1 = this.rand.nextLong();
//        
//        for (Integer caveX : caveStructureMap.keySet()) {
//        	if (caveX >= (chunkX - range) && caveX <= (chunkX + range)) {
//        		Integer caveZ = caveStructureMap.get(caveX);
//        		if (caveZ >= (chunkZ - range) && caveZ <= (chunkZ + range)) {
//	                long l1 = (long)caveX * l;
//	                long i2 = (long)caveZ * i1;
//	                this.rand.setSeed(l1 ^ i2 ^ world.getSeed());
//	                this.func_151538_a(world, caveX, caveZ, chunkX, chunkZ, blocks);
//        		}
//        	}
//        }
//    }
    
    public void addStructure(int chunkX, int chunkZ) {
    	this.caveStructureMap.put(chunkX, chunkZ);
    }
}
