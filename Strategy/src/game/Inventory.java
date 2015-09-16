package game;

import java.util.List;
import java.util.ArrayList;

import util.enums.*;

public class Inventory {
	
	private List<TerrainType> items = new ArrayList<TerrainType>();
	
	public int getCount(TerrainType type) {
		int count = 0;
		for(TerrainType item : items) {
			if(item.equals(type)) {
				count++;
			}
		}
		
		return count;
	}
	
	public void addItem(TerrainType type) {
		items.add(type);
	}
	
}
