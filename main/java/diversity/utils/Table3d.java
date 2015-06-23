package diversity.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public class Table3d extends TreeMap<Integer, Table<Integer, Integer, EnumCubeType>>
{
	public Table<Integer, Integer, TreeMap<Integer, EnumCubeType>> mutation = HashBasedTable.create();
	
	@Override
	public void clear() {
		for (Integer y : keySet()) {
			get(y).clear();
		}
		super.clear();
	}

	public boolean containsKey(int x, int y, int z) {
		return containsKey(y) && get(y).contains(x, z);
	}

	public EnumCubeType get(int x, int y, int z) {
		return containsKey(x, y, z) ? get(y).get(x, z) : null;
	}
	
	public Set<Integer> rowKeySet(int y) {
		return containsKey(y) ? get(y).rowKeySet() : null;
	}
	
	public Set<Integer> columnKeySet(int y) {
		return containsKey(y) ? get(y).columnKeySet() : null;
	}

	public EnumCubeType put(int x, int y, int z, EnumCubeType cubeType) {
		if (!containsKey(y)) {
			Table<Integer, Integer, EnumCubeType> table = HashBasedTable.create();
			put(y, table);
		}
		get(y).put(x, z, cubeType);
		return cubeType;
	}

	public boolean remove(int x, int y, int z) {
		if (containsKey(x, y, z)) {
			get(y).remove(x, z);
//			if (get(y).isEmpty()) {
//				remove(y);
//			}
			return true;
		}
		return false;
	}
	
	public void mutateTable() {
		for (Integer y : keySet())
		for (Integer x : rowKeySet(y))
		for (Integer z : columnKeySet(y))
		{
			if (mutation.get(x, z) == null) {
				TreeMap map = new TreeMap<Integer, EnumCubeType>();
				map.put(y, get(x, y, z));
	        	mutation.put(x, z, map);
			} else {
				mutation.get(x, z).put(y, get(x, y, z));
			}
		}
	}
	
//	public int[] toIntArray() {
//		List<Integer> arrayList = new ArrayList<Integer>(); 
//		for (Integer y : keySet())
//		for (Integer x : rowKeySet(y))
//		for (Integer z : columnKeySet(y))
//		{
//			if (containsKey(x, y, z)) {
//				arrayList.add(x);
//				arrayList.add(y);
//				arrayList.add(z);
//				arrayList.add(get(x, y, z).ordinal());
//			}
//		}
//		int[] array = new int[arrayList.size()];
//		for (int index = 0; index < arrayList.size(); index++) {
//			array[index] = Integer.valueOf(arrayList.get(index));
//		}
//		return array;
//	}
//	
//	public void regenerate(int[] array) {
//		for (int x = 0, y = 1, z = 2, ordinal = 3; x < array.length && y < array.length && z < array.length && ordinal < array.length; x=x+4, y=y+4, z=z+4, ordinal=ordinal+4)
//		{
//			this.put(x, y, z, EnumCubeType.values()[ordinal]);
//		}
//	}
}
