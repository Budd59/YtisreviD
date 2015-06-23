package diversity.suppliers;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import diversity.structure.AztecPyramid;
import diversity.structure.Catacomb;
import diversity.structure.DwarvesCave;
import diversity.structure.EgyptianPyramid;
import diversity.structure.Inn;
import diversity.structure.WitchHutt;

public enum EnumStructurePiece
{
	EGYPTIAN_PYRAMID (EnumStructure.EGYPTIAN_PYRAMID, 25, EgyptianPyramid.class),
	AZTEC_PYRAMID (EnumStructure.JUNGLE, 25, AztecPyramid.class),
	CATACOMB (EnumStructure.FOREST, 25, Catacomb.class),
	SWAMPHUT (EnumStructure.SWAMP, 25, WitchHutt.class),
	INN (EnumStructure.PLAIN, 25, Inn.class),
	DWARVES_CAVE (EnumStructure.SNOW_MOUNTAINS, 25, DwarvesCave.class);
	
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