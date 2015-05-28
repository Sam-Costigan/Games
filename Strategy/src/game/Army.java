package game;

import java.util.List;
import java.util.ArrayList;

public class Army {
	
	private List<Unit> units;
	
	public Army(List<Unit> units) {
		if(units.size() > 0) {
			this.units = units;
		}
		units = new ArrayList<Unit>();
	}
	
	public List<Unit> getUnits() {
		return units;
	}
	
	public void addUnit(Unit unit) {
		units.add(unit);
	}
	
}
