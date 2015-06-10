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
}
