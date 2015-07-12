package diversity.suppliers;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import diversity.utils.VillagerRegistry.IVillageCreationHandler;
import diversity.village.AGlobalStructureVillage.AGlobalStart;
import diversity.village.AGlobalStructureVillage.PieceWeight;
import diversity.village.StructureVillageApache;
import diversity.village.StructureVillageAztec;
import diversity.village.StructureVillageEgyptian;
import diversity.village.StructureVillageInuit;
import diversity.village.StructureVillageLakeside;
import diversity.village.StructureVillageSettled;
import diversity.village.StructureVillageTibetan;
import diversity.village.AGlobalStructureVillage.AGlobalVillage;
import diversity.village.StructureVillageZulu;

public enum EnumVillagePiece implements IEnumPiece, IVillageCreationHandler
{
	APACHE_HOUSE1 (EnumVillage.APACHE, 10, 3, StructureVillageApache.House1.class),
	APACHE_HOUSE2 (EnumVillage.APACHE, 10, 3, StructureVillageApache.House2.class),
	APACHE_CHIEFTENT (EnumVillage.APACHE, 6, 1, StructureVillageApache.ChiefTent.class),
	APACHE_LIVESTOCK (EnumVillage.APACHE, 6, 1, StructureVillageApache.Livestock.class),
	APACHE_SHAMANTENT (EnumVillage.APACHE, 6, 1, StructureVillageApache.ShamanTent.class),
	
	AZTEC_HOUSE1 (EnumVillage.AZTEC, 15, 3, StructureVillageAztec.House1.class),
	AZTEC_HOUSE2 (EnumVillage.AZTEC, 15, 3, StructureVillageAztec.House2.class),
	AZTEC_TEMPLE (EnumVillage.AZTEC, 10, 1, StructureVillageAztec.Temple.class),
	AZTEC_PALACE (EnumVillage.AZTEC, 10, 1, StructureVillageAztec.Palace.class),
	AZTEC_LIVESTOCK (EnumVillage.AZTEC, 5, 2, StructureVillageAztec.Livestock.class),
	AZTEC_FIELD (EnumVillage.AZTEC, 5, 2, StructureVillageAztec.Field.class),

	INUIT_IGLOO1 (EnumVillage.INUIT, 25, 3, StructureVillageInuit.Igloo1.class),
	INUIT_IGLOO2 (EnumVillage.INUIT, 15, 3, StructureVillageInuit.Igloo2.class),
	INUIT_CHIEFIGLOO (EnumVillage.INUIT, 6, 1, StructureVillageInuit.ChiefIgloo.class),
	INUIT_KENNEL (EnumVillage.INUIT, 10, 1, StructureVillageInuit.Kennel.class),

	SETTLED_CHURCH (EnumVillage.SETTLED, 51, 1, StructureVillageSettled.Church.class),
	SETTLED_FIELD1 (EnumVillage.SETTLED, 3, 2, StructureVillageSettled.Field1.class),
	SETTLED_FIELD2 (EnumVillage.SETTLED, 3, 2, StructureVillageSettled.Field2.class),
	SETTLED_BUTCHERY (EnumVillage.SETTLED, 15, 2, StructureVillageSettled.Butchery.class),
	SETTLED_LIBRARY (EnumVillage.SETTLED, 20, 1, StructureVillageSettled.Library.class),
	SETTLED_FORGE (EnumVillage.SETTLED, 15, 1, StructureVillageSettled.Forge.class),
	SETTLED_HOUSE3 (EnumVillage.SETTLED, 8, 4, StructureVillageSettled.House3.class),
	SETTLED_HOUSE4GARDEN (EnumVillage.SETTLED, 4, 3, StructureVillageSettled.House4Garden.class),		

	ZULU_HOUSE1 (EnumVillage.ZULU, 15, 3, StructureVillageZulu.House1.class),
	ZULU_HOUSE2 (EnumVillage.ZULU, 15, 3, StructureVillageZulu.House2.class),
	ZULU_HOUSE3 (EnumVillage.ZULU, 10, 1, StructureVillageZulu.House3.class),
	ZULU_HOUSE4 (EnumVillage.ZULU, 10, 1, StructureVillageZulu.House4.class),
	ZULU_LIVESTOCK (EnumVillage.ZULU, 12, 2, StructureVillageZulu.Livestock.class),
	
	TIBETAN_MAINHOUSE (EnumVillage.TIBETAN, 15, 1, StructureVillageTibetan.MainHouse.class),
	TIBETAN_HOUSE1 (EnumVillage.TIBETAN, 25, 3, StructureVillageTibetan.House1.class),
	TIBETAN_HOUSE2 (EnumVillage.TIBETAN, 20, 2, StructureVillageTibetan.House2.class),
	TIBETAN_FIELD (EnumVillage.TIBETAN, 10, 2, StructureVillageTibetan.Field.class),
	
	EGYPTIAN_HOUSE1 (EnumVillage.EGYPTIAN, 12, 3, StructureVillageEgyptian.House1.class),
	EGYPTIAN_HOUSE2 (EnumVillage.EGYPTIAN, 17, 2, StructureVillageEgyptian.House2.class),
	EGYPTIAN_HOUSE3 (EnumVillage.EGYPTIAN, 12, 2, StructureVillageEgyptian.House3.class),
	EGYPTIAN_TOWER (EnumVillage.EGYPTIAN, 20, 4, StructureVillageEgyptian.Tower.class), 
	EGYPTIAN_FIELD (EnumVillage.EGYPTIAN, 4, 2, StructureVillageEgyptian.Field.class),
	EGYPTIAN_TEMPLE (EnumVillage.EGYPTIAN, 18, 1, StructureVillageEgyptian.Temple.class),
	EGYPTIAN_PALACE (EnumVillage.EGYPTIAN, 51, 1, StructureVillageEgyptian.Palace.class),
	EGYPTIAN_FIELD2 (EnumVillage.EGYPTIAN, 4, 2, StructureVillageEgyptian.Field2.class),
	
	LAKESIDE_HOUSE1 (EnumVillage.LAKESIDE, 20, 4, StructureVillageLakeside.House1.class),
	LAKESIDE_HOUSE2 (EnumVillage.LAKESIDE, 20, 1, StructureVillageLakeside.House2.class),
	LAKESIDE_TOWER (EnumVillage.LAKESIDE, 6, 2, StructureVillageLakeside.Tower.class),
	LAKESIDE_BREEDING (EnumVillage.LAKESIDE, 4, 1, StructureVillageLakeside.Breeding.class),
	LAKESIDE_FIELD (EnumVillage.LAKESIDE, 3, 2, StructureVillageLakeside.Field.class);

	
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
	
	public static AGlobalVillage getVillageComponent(PieceWeight pieceWeight, AGlobalStart startPiece, @SuppressWarnings("rawtypes") List pieces, Random random,
            int p1, int p2, int p3, int p4, int p5)
	{
		for (EnumVillagePiece piece : EnumVillagePiece.values())
		{
			if (pieceWeight.villagePieceClass.equals(piece.pieceClass)) {
				return (AGlobalVillage)piece.buildComponent(pieceWeight, startPiece, pieces, random, p1, p2, p3, p4, p5);
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
    public Object buildComponent (PieceWeight villagePiece, AGlobalStart startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
    {
    	try {
    		Class[] parametersType = new Class[] {AGlobalStart.class, List.class, Random.class, int.class, int.class, int.class, int.class, int.class};
			Method method =  pieceClass.getDeclaredMethod("buildComponent", AGlobalStart.class, List.class, Random.class, int.class, int.class, int.class, int.class, int.class);
			return method.invoke(pieceClass.newInstance(), startPiece, pieces, random, p1, p2, p3, p4, p5);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}