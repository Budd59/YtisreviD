package diversity.suppliers;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import diversity.structure.StructureDesert;
import diversity.structure.StructureForest;
import diversity.structure.StructureJungle;
import diversity.structure.StructurePlain;
import diversity.structure.StructureSwamp;

public enum EnumStructurePiece
{
	EGYPTIAN_PYRAMID (EnumStructure.DESERT, 25, StructureDesert.Pyramid.class),
	AZTEC_PYRAMID (EnumStructure.JUNGLE, 25, StructureJungle.Pyramid.class),
	CATACOMB (EnumStructure.FOREST, 25, StructureForest.Catacomb.class),
	SWAMPHUT (EnumStructure.SWAMP, 25, StructureSwamp.SwampHut.class),
	INN (EnumStructure.PLAIN, 25, StructurePlain.Inn.class);
	
	public final EnumStructure structure;
	public final int weight;
	public final Class pieceClass;
	
	private EnumStructurePiece(EnumStructure structure, int weight, Class pieceClass)
	{
		this(structure, weight, pieceClass, null);
	}
	
	private EnumStructurePiece(EnumStructure structure, int weight, Class pieceClass, EnumEntity monster)
	{
		structure.components.add(this);
		structure.totalWeight += weight;
		this.structure = structure;
		this.weight = weight;
		this.pieceClass = pieceClass;
	}
	
	public static void register() {
    	for (EnumStructurePiece structure : EnumStructurePiece.values())
    	{
            MapGenStructureIO.func_143031_a(structure.pieceClass, structure.name());
    	}
	}
}