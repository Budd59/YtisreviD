package diversity.suppliers;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import diversity.village.StructureVillageApache;
import diversity.village.StructureVillageAztec;
import diversity.village.StructureVillageEgyptian;
import diversity.village.StructureVillageInuit;
import diversity.village.StructureVillageLakeside;
import diversity.village.StructureVillageSettled;
import diversity.village.StructureVillageTibetan;
import diversity.village.StructureVillageZulu;

public enum EnumVillageBasicPiece implements IEnumPiece
{
	APACHE_START (EnumVillage.APACHE, StructureVillageApache.Start.class),
	APACHE_PATH (EnumVillage.APACHE, StructureVillageApache.Path.class),
	APACHE_TORCH (EnumVillage.APACHE, StructureVillageApache.Torch.class),
	
	AZTEC_START (EnumVillage.AZTEC, StructureVillageAztec.Start.class),
	AZTEC_PATH (EnumVillage.AZTEC, StructureVillageAztec.Path.class),
	AZTEC_TORCH (EnumVillage.AZTEC, StructureVillageAztec.Torch.class),
	
	ESKIMO_START (EnumVillage.INUIT, StructureVillageInuit.Start.class),
	ESKIMO_PATH (EnumVillage.INUIT, StructureVillageInuit.Path.class),
	ESKIMO_TORCH (EnumVillage.INUIT, StructureVillageInuit.Torch.class),

	SETTLED_START (EnumVillage.SETTLED, StructureVillageSettled.Start.class),
	SETTLED_PATH (EnumVillage.SETTLED, StructureVillageSettled.Path.class),
	SETTLED_TORCH (EnumVillage.SETTLED, StructureVillageSettled.Torch.class),
	
	ZOULOU_START (EnumVillage.ZULU, StructureVillageZulu.Start.class),
	ZOULOU_PATH (EnumVillage.ZULU, StructureVillageZulu.Path.class),
	ZOULOU_TORCH (EnumVillage.ZULU, StructureVillageZulu.Torch.class),
	
	TIBETAN_START (EnumVillage.TIBETAN, StructureVillageTibetan.Start.class),
	TIBETAN_PATH (EnumVillage.TIBETAN, StructureVillageTibetan.Path.class),
	TIBETAN_TORCH (EnumVillage.TIBETAN, StructureVillageTibetan.Torch.class),
	
	EGYPTIAN_START (EnumVillage.EGYPTIAN, StructureVillageEgyptian.Start.class),
	EGYPTIAN_PATH (EnumVillage.EGYPTIAN, StructureVillageEgyptian.Path.class),
	EGYPTIAN_TORCH (EnumVillage.EGYPTIAN, StructureVillageEgyptian.Torch.class),
	
	LAKE_START (EnumVillage.LAKESIDE, StructureVillageLakeside.Start.class),
	LAKE_PATH (EnumVillage.LAKESIDE, StructureVillageLakeside.Path.class),
	LAKE_TORCH (EnumVillage.LAKESIDE, StructureVillageLakeside.Torch.class);
	
	public EnumVillage village;
	public Class pieceClass;

	private EnumVillageBasicPiece(EnumVillage village, Class pieceClass)
	{
		village.defaultPieces.add(this);
		this.village = village;
		this.pieceClass = pieceClass;
	}
	
	@Override
	public EnumVillage getVillage() {
		return village;
	}
	
	public static void register() {
		for (EnumVillageBasicPiece defaultPiece : EnumVillageBasicPiece.values()) {
            MapGenStructureIO.func_143031_a(defaultPiece.pieceClass, defaultPiece.name());
		}
	}
}