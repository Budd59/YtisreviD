package diversity.suppliers;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import diversity.structure.DwarvesCave;
import diversity.structure.LostCave;
import diversity.structure.MushroomCave;

public enum EnumCavePiece
{
	DWARVES_CAVE (EnumCave.DWARVES_CAVE, 25, DwarvesCave.class),
	LOST_CAVE (EnumCave.LOST_CAVE, 25, LostCave.class),
	MUSHROOM_CAVE (EnumCave.MUSHROOM_CAVE, 25, MushroomCave.class);
	
	public final EnumCave structure;
	public final int weight;
	public final Class pieceClass;
	
	private EnumCavePiece(EnumCave structure, int weight, Class pieceClass)
	{
		this(structure, weight, pieceClass, null);
	}
	
	private EnumCavePiece(EnumCave structure, int weight, Class pieceClass, EnumEntity monster)
	{
		structure.components.add(this);
		structure.totalWeight += weight;
		this.structure = structure;
		this.weight = weight;
		this.pieceClass = pieceClass;
	}
	
	public static void register() {
    	for (EnumCavePiece structure : EnumCavePiece.values())
    	{
            MapGenStructureIO.func_143031_a(structure.pieceClass, structure.name());
    	}
	}
}
