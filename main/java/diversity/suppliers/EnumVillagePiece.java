package diversity.suppliers;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import diversity.village.VillageApache;
import diversity.village.VillageAztec;
import diversity.village.VillageEgyptian;
import diversity.village.VillageInuit;
import diversity.village.VillageLakeside;
import diversity.village.VillageSettled;
import diversity.village.VillageTibetan;
import diversity.village.VillageTools.GlobalVillage;
import diversity.village.VillageZulu;

public enum EnumVillagePiece implements IEnumPiece, IVillageCreationHandler
{
	APACHE_HOUSE1 (EnumVillage.APACHE, 10, 3, VillageApache.House1.class),
	APACHE_HOUSE2 (EnumVillage.APACHE, 10, 3, VillageApache.House2.class),
	APACHE_CHIEFTENT (EnumVillage.APACHE, 6, 1, VillageApache.ChiefTent.class),
	APACHE_LIVESTOCK (EnumVillage.APACHE, 6, 1, VillageApache.Livestock.class),
	APACHE_SHAMANTENT (EnumVillage.APACHE, 6, 1, VillageApache.ShamanTent.class),
	
	AZTEC_HOUSE1 (EnumVillage.AZTEC, 15, 3, VillageAztec.House1.class),
	AZTEC_HOUSE2 (EnumVillage.AZTEC, 15, 3, VillageAztec.House2.class),
	AZTEC_TEMPLE (EnumVillage.AZTEC, 10, 1, VillageAztec.Temple.class),
	AZTEC_PALACE (EnumVillage.AZTEC, 10, 1, VillageAztec.Palace.class),
	AZTEC_LIVESTOCK (EnumVillage.AZTEC, 5, 2, VillageAztec.Livestock.class),
	AZTEC_FIELD (EnumVillage.AZTEC, 5, 2, VillageAztec.Field.class),

	INUIT_IGLOO1 (EnumVillage.INUIT, 25, 3, VillageInuit.Igloo1.class),
	INUIT_IGLOO2 (EnumVillage.INUIT, 15, 3, VillageInuit.Igloo2.class),
	INUIT_CHIEFIGLOO (EnumVillage.INUIT, 6, 1, VillageInuit.ChiefIgloo.class),
	INUIT_KENNEL (EnumVillage.INUIT, 10, 1, VillageInuit.Kennel.class),

	SETTLED_CHURCH (EnumVillage.SETTLED, 51, 1, VillageSettled.Church.class),
	SETTLED_FIELD1 (EnumVillage.SETTLED, 3, 2, VillageSettled.Field1.class),
	SETTLED_FIELD2 (EnumVillage.SETTLED, 3, 2, VillageSettled.Field2.class),
	SETTLED_BUTCHERY (EnumVillage.SETTLED, 15, 2, VillageSettled.Butchery.class),
	SETTLED_LIBRARY (EnumVillage.SETTLED, 20, 1, VillageSettled.Library.class),
	SETTLED_FORGE (EnumVillage.SETTLED, 15, 1, VillageSettled.Forge.class),
	SETTLED_HOUSE3 (EnumVillage.SETTLED, 8, 4, VillageSettled.House3.class),
	SETTLED_HOUSE4GARDEN (EnumVillage.SETTLED, 4, 3, VillageSettled.House4Garden.class),		

	ZULU_HOUSE1 (EnumVillage.ZULU, 15, 3, VillageZulu.House1.class),
	ZULU_HOUSE2 (EnumVillage.ZULU, 15, 3, VillageZulu.House2.class),
	ZULU_HOUSE3 (EnumVillage.ZULU, 10, 1, VillageZulu.House3.class),
	ZULU_HOUSE4 (EnumVillage.ZULU, 10, 1, VillageZulu.House4.class),
	ZULU_LIVESTOCK (EnumVillage.ZULU, 12, 2, VillageZulu.Livestock.class),
	
	TIBETAN_MAINHOUSE (EnumVillage.TIBETAN, 15, 1, VillageTibetan.MainHouse.class),
	TIBETAN_HOUSE1 (EnumVillage.TIBETAN, 25, 3, VillageTibetan.House1.class),
	TIBETAN_HOUSE2 (EnumVillage.TIBETAN, 20, 2, VillageTibetan.House2.class),
	TIBETAN_FIELD (EnumVillage.TIBETAN, 10, 2, VillageTibetan.Field.class),
	
	EGYPTIAN_HOUSE1 (EnumVillage.EGYPTIAN, 12, 3, VillageEgyptian.House1.class),
	EGYPTIAN_HOUSE2 (EnumVillage.EGYPTIAN, 17, 2, VillageEgyptian.House2.class),
	EGYPTIAN_HOUSE3 (EnumVillage.EGYPTIAN, 12, 2, VillageEgyptian.House3.class),
	EGYPTIAN_TOWER (EnumVillage.EGYPTIAN, 20, 4, VillageEgyptian.Tower.class), 
	EGYPTIAN_FIELD (EnumVillage.EGYPTIAN, 4, 2, VillageEgyptian.Field.class),
	EGYPTIAN_TEMPLE (EnumVillage.EGYPTIAN, 18, 1, VillageEgyptian.Temple.class),
	EGYPTIAN_PALACE (EnumVillage.EGYPTIAN, 51, 1, VillageEgyptian.Palace.class),
	EGYPTIAN_FIELD2 (EnumVillage.EGYPTIAN, 4, 2, VillageEgyptian.Field2.class),
	
	LAKESIDE_HOUSE1 (EnumVillage.LAKESIDE, 20, 4, VillageLakeside.House1.class),
	LAKESIDE_HOUSE2 (EnumVillage.LAKESIDE, 20, 1, VillageLakeside.House2.class),
	LAKESIDE_TOWER (EnumVillage.LAKESIDE, 6, 2, VillageLakeside.Tower.class),
	LAKESIDE_BREEDING (EnumVillage.LAKESIDE, 4, 1, VillageLakeside.Breeding.class),
	LAKESIDE_FIELD (EnumVillage.LAKESIDE, 3, 2, VillageLakeside.Field.class);

	
	public final EnumVillage village;
	public final Class pieceClass;
	public final int weight;
	public final int limit;
	
	private EnumVillagePiece(EnumVillage village, int weight, int limit, Class pieceClass)
	{
		village.pieces.add(this);
		this.village = village;
		this.pieceClass = pieceClass;
		this.weight = weight;
		this.limit = limit;
	}

	@Override
	public EnumVillage getVillage() {
		return village;
	}
	
	public static GlobalVillage getVillageComponent(StructureVillagePieces.PieceWeight pieceWeight, StructureVillagePieces.Start startPiece, @SuppressWarnings("rawtypes") List pieces, Random random,
            int p1, int p2, int p3, int p4, int p5)
	{
		for (EnumVillagePiece piece : EnumVillagePiece.values())
		{
			if (pieceWeight.villagePieceClass.equals(piece.pieceClass)) {
				return (GlobalVillage)piece.buildComponent(pieceWeight, startPiece, pieces, random, p1, p2, p3, p4, p5);
			}
		}
		return null;
	}
	
	public static void register() {
	  	for (EnumVillagePiece piece : EnumVillagePiece.values())
    	{
            MapGenStructureIO.func_143031_a(piece.pieceClass, piece.name());
    		//VillagerRegistry.instance().registerVillageCreationHandler((IVillageCreationHandler)piece);
    	}
	}
		
    @Override
    public PieceWeight getVillagePieceWeight (Random random, int i)
    {
        return new PieceWeight(pieceClass, weight, i + random.nextInt(limit));
    }

    @Override
    public Class<?> getComponentClass()
    {
        return pieceClass;
    }
    
    @Override
    public Object buildComponent (PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
    {
    	try {
    		Class[] parametersType = new Class[] {StructureVillagePieces.Start.class, List.class, Random.class, int.class, int.class, int.class, int.class, int.class};
			Method method =  pieceClass.getDeclaredMethod("buildComponent", StructureVillagePieces.Start.class, List.class, Random.class, int.class, int.class, int.class, int.class, int.class);
			return method.invoke(pieceClass.newInstance(), startPiece, pieces, random, p1, p2, p3, p4, p5);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}